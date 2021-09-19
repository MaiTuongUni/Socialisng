package com.socialising.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "userCustomService")
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/api/authentication").permitAll()
//                .antMatchers("/api/orders/statistic-by-day").permitAll()
//                .antMatchers("/api/orders/statistic-by-week").permitAll()
//                .antMatchers("/api/orders/statistic-by-month").permitAll()
//                .antMatchers("/api/orders/statistic-by-year").permitAll()
                .antMatchers("/api/product/**").permitAll()
                .antMatchers("/api/home/**").permitAll()
                .antMatchers("/api/product/**").permitAll()
                .antMatchers("/api/user/**").permitAll()
                .antMatchers("/api/register/**").permitAll()
                .antMatchers("/api/cart/**").permitAll()
                .antMatchers("/api/admin/product/**").hasRole("ADMIN")
                .antMatchers("/api/admin/orders/**").hasRole("ADMIN")
                .antMatchers("/api/admin/brand/**").hasRole("ADMIN")
                .antMatchers("/api/admin/report/**").hasRole("ADMIN")
                .antMatchers("/api/user/profile/**").authenticated()
                .antMatchers("/api/orders/**").authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
