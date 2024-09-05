package com.harsh.JWTLearnings.Security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    DataSource dataSource;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                        .requestMatchers("/admin/signin/**","/admin/login/**","emp/login/**","emp/signin/**").permitAll()  // Allow unauthenticated access to /signin endpoint
                        .anyRequest().authenticated());  // All other requests require authentication
        
        http.exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler));  // Handle unauthorized access
        http.headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions
                        .sameOrigin()  // Allow frames from the same origin
                )
        );
        http.csrf(csrf -> csrf.disable());  // Disable CSRF protection
        http.addFilterBefore(authenticationJwtTokenFilter(),
                UsernamePasswordAuthenticationFilter.class);  // Add JWT filter before username/password authentication

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);  // Use JdbcUserDetailsManager for user details service
    }

    // @Bean
    // public CommandLineRunner initData(UserDetailsService userDetailsService) {
    //     return args -> {
    //         JdbcUserDetailsManager manager = (JdbcUserDetailsManager) userDetailsService;
    //         UserDetails user1 = User.withUsername("user1")
    //                 .password(passwordEncoder().encode("password1"))
    //                 .roles("USER")
    //                 .build();
    //         UserDetails admin = User.withUsername("admin")
    //                 .password(passwordEncoder().encode("adminPass"))
    //                 .roles("ADMIN")
    //                 .build();

    //         JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
    //         userDetailsManager.createUser(user1);  // Create user1 in the database
    //         userDetailsManager.createUser(admin);  // Create admin in the database
    //     };
    // }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();  // Use BCryptPasswordEncoder for password hashing
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();  // Provide AuthenticationManager bean
    }
}
