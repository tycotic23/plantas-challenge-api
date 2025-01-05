package com.plantasapi.plantas.configurations;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.WebAttributes;

@EnableWebSecurity
@Configuration
public class WebAuthorization {

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws  Exception{
        return http.authorizeHttpRequests((authorize)->
                authorize.requestMatchers("/login").permitAll()
                        .requestMatchers("/h2-console","/h2-console/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/factory").permitAll()//.hasAuthority("USER")
                        .anyRequest().permitAll()
                )
                .formLogin(form->
                                form.usernameParameter("email")
                                        .passwordParameter("password")
                                        .loginPage("/login")
                                        .permitAll()
                        /*httpSecurityFormLoginConfigurer -> {
                    httpSecurityFormLoginConfigurer.loginPage("/login")
                            .usernameParameter("email")
                            .passwordParameter("password");}*/
                )
                .build();
    }

    private void clearAuthenticationAttributes(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session!=null){
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
}
