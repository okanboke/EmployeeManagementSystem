package com.employeemanagementsystem.projectx.config;

import com.employeemanagementsystem.projectx.security.JwtAuthenticationEntryPoint;
import com.employeemanagementsystem.projectx.security.JwtAuthenticationFilter;
import com.employeemanagementsystem.projectx.service.impl.UserDetailsServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableScheduling
public class SecurityConfig {

    private UserDetailsServiceImpl userDetailsService;

    private JwtAuthenticationEntryPoint handler;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtAuthenticationEntryPoint handler) {
        this.userDetailsService = userDetailsService;
        this.handler = handler;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOriginPatterns(Collections.singletonList("*"));
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors(withDefaults())
                .csrf((csrf) -> csrf.disable())
                .exceptionHandling().authenticationEntryPoint(handler).and() //sonradan eklendi.
                .authorizeHttpRequests((authorize) -> authorize
                        .antMatchers("/api/auth/admin/**").hasAuthority("admin") //sadece admin erişebilir
                        .antMatchers(HttpMethod.GET, "/api/admin/list-user").hasAuthority("admin") //sadece admin erişebilir
                        .antMatchers(HttpMethod.GET, "/api/admin/list-justification").hasAuthority("admin")  //sadece admin erişebilir
                        .antMatchers(HttpMethod.POST, "/api/permissions/type/admin/create-type").hasAuthority("admin")
                        .antMatchers(HttpMethod.POST, "/api/permissions/admin/**").hasAuthority("admin")
                        .antMatchers(HttpMethod.PUT, "/api/permissions/admin/update-status").hasAuthority("admin")
                        .antMatchers(HttpMethod.GET, "/api/annual/permissions/admin/list-permissions").hasAuthority("admin")
                        .antMatchers(HttpMethod.PUT, "/api/annual/permissions/admin/update-status").hasAuthority("admin")

                        .antMatchers(HttpMethod.POST, "/api/auth/login").permitAll() //sadece admin erişebilir

                        /****/.antMatchers(HttpMethod.POST, "/api/annual/permissions/user/calculate").hasAuthority("user")
                        .antMatchers(HttpMethod.POST, "/api/employee/user-home/userInfo").hasAuthority("user")
                        .antMatchers(HttpMethod.POST, "/api/annual/permissions/user/list-permissions").hasAuthority("user")
                        .antMatchers(HttpMethod.POST, "/api/annual/permissions/user/create").hasAuthority("user")
                        .antMatchers(HttpMethod.POST, "/api/employee/**").hasAuthority("user")
                        .antMatchers(HttpMethod.GET, "/api/employee/**").hasAuthority("user")
                        .antMatchers(HttpMethod.GET, "/api/permissions/user/**").hasAuthority("user") //sadece user erişebilir
                        .antMatchers(HttpMethod.PUT, "/api/employee/**").hasAuthority("user") //sadece user erişebilir
                        .antMatchers(HttpMethod.GET, "/api/permissions/type//user/list-types").permitAll()
                        .antMatchers(HttpMethod.POST, "/api/auth/user/login").permitAll()

                        .anyRequest().authenticated())
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*");
            }
        };
    }
}
