package com.junior.modules.config;


//@EnableWebSecurity
//@Configuration
/*
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/**").hasRole("USER");

    }

    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.inMemoryAuthentication().withUser("junior")
                .password("{noop}123").roles("USER");
    }

}
*/