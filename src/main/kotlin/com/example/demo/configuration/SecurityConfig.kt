package com.example.demo.configuration

import com.example.demo.invariants.UserPermission.ADMIN_GET_DATA
import com.example.demo.invariants.UserPermission.USER_GET_DATA
import com.example.demo.invariants.UserRoles
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
@EnableWebSecurity
class SecurityConfig(@Autowired val passwordEncoder: PasswordEncoder): WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/", "index", "/css/*","/js/*" ).permitAll()
            .antMatchers("/first-app/1").hasRole(UserRoles.USER.mnemo)
//            .antMatchers("/first-app/**").hasRole(UserRoles.ADMIN.mnemo)
            .antMatchers(HttpMethod.POST,"/first-app/test").hasAuthority(ADMIN_GET_DATA.permission)
            .antMatchers(HttpMethod.DELETE,"/first-app/test").hasAuthority(USER_GET_DATA.permission)
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic()
    }

    @Bean
    override fun userDetailsService(): UserDetailsService {
       val admin: UserDetails = User.builder()
           .username("admin")
           .password(passwordEncoder.encode("password"))
           .authorities(UserRoles.ADMIN.getGrantedAuthorities())
           .build()

        val user: UserDetails = User.builder()
            .username("user")
            .password(passwordEncoder.encode("password"))
            .authorities(UserRoles.USER.getGrantedAuthorities())
//            .roles(UserRoles.USER.mnemo)
            .build()

        return InMemoryUserDetailsManager(user, admin)
    }
}