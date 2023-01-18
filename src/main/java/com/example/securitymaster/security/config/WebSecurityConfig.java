package com.example.securitymaster.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import static com.example.securitymaster.security.SecurityRoles.*;

@Configuration
public class WebSecurityConfig {
    @Autowired
    private RoleHierarchy roleHierarchy;

    @Bean
    public UserDetailsService userDetailsService(){
        var uds = new InMemoryUserDetailsManager();

        var mary = User.withUsername("mary")
                .password("mary")
                .roles(ROLES_ADMIN)
                .build();

        var james = User.withUsername("james")
                .password("james")
                .roles(EMPLOYEES_ADMIN)
                .build();

        var lucifer = User.withUsername("lucifer")
                .password("lucifer")
                .roles(DEPARTMENTS_CREATE,DEPARTMENTS_READ,DEPARTMENTS_PAGE_VIEW)
                .build();

        var star = User.withUsername("star")
                .password("star")
                .roles(CUSTOMERS_READ,CUSTOMERS_PAGE_VIEW)
                .build();

        var chloe = User.withUsername("chloe")
                .password("chloe")
                .roles()
                .build();

        uds.createUser(mary);
        uds.createUser(james);
        uds.createUser(lucifer);
        uds.createUser(star);
        uds.createUser(chloe);

        return uds;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Throwable{
        http.
                authorizeRequests()
                .expressionHandler(expressionHandler())
                .requestMatchers("/","/home","/bootstrap/**","/error/**")
                .permitAll()
/*                .requestMatchers("/customer/**").hasRole(CUSTOMERS_PAGE_VIEW)
                .requestMatchers("/employee/**").hasRole(EMPLOYEES_PAGE_VIEW)
                .requestMatchers("/department/**").hasRole(DEPARTMENTS_PAGE_VIEW)*/
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login-error")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll();

        http.csrf().disable();

        return  http.build();
    }

    @Bean
    public DefaultWebSecurityExpressionHandler expressionHandler(){
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setRoleHierarchy(roleHierarchy);
        return handler;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
