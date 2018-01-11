package edu.wat.pl.blog.auth;

import edu.wat.pl.blog.auth.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Work around https://jira.spring.io/browse/SEC-2855
    @Bean
    public SessionRegistry sessionRegistry() {
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        return sessionRegistry;
    }

    // Register HttpSessionEventPublisher
    @Bean
    public static ServletListenerRegistrationBean httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/templates/**", "/static/**", "/js/**", "/images/**", "/", "/post/{postId}", "/add_post", "/posts", "/registration", "/post/").permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();

        http.csrf().disable();

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/resources/**").anyRequest();
//    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}