package com.douzone.lis_back.config;

import com.douzone.lis_back.Filter.TokenFilter;
import com.douzone.lis_back.service.user.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
     @Bean
     CORSFilter corsFilter(){
         return new CORSFilter();
     }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                 .addFilterBefore(corsFilter(), SessionManagementFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/user-service/login").permitAll()
                .antMatchers("/inspection-service").hasRole("inspector")
                .antMatchers("/inspection-service/result").hasRole("doctor")
                .antMatchers("/collecting-service").hasRole("nurse")
                .antMatchers("/collecting-service/unsuitable").hasRole("inspector")
                .and()
                // .formLogin().loginProcessingUrl("/user-service/login")
                // .usernameParameter("username")
                // .passwordParameter("password")
                // .and()
                .httpBasic().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // @Bean
    // public CorsConfigurationSource configurationSource(){
    //     CorsConfiguration configuration = new CorsConfiguration();
    //
    //     configuration.setAllowedOrigins(List.of("http://dpiezv2v8sa5.cloudfront.net/"));
    //     // configuration.addAllowedOrigin("http://localhost:3000");
    //     configuration.setAllowCredentials(true);
    //     configuration.setAllowedMethods(List.of("GET,POST,PUT,OPTIONS,DELETE"));
    //     configuration.setAllowedHeaders(List.of("X-Requested-With, Content-Type, "
    //             + "Authorization, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers" +
    //             ", Access-Control-Allow-Methods, Access-Control-Allow-Origin, Access-Control-Allow-Credentials" +
    //             ",Access-Control-Allow-Headers"));
    //     // configuration.addAllowedHeader();
    //     // configuration.addAllowedMethod("GET,POST,PUT,OPTIONS,DELETE");
    //     // configuration.setAllowCredentials(true);
    //
    //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //     source.registerCorsConfiguration("http://localhost:8080/user-service/login", configuration);
    //     return source;
    // }
}

