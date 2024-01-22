package com.akhil.seapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityAppConfig {
    @Autowired
    HttpSecurity httpSecurity;
    //    @Bean
//    public InMemoryUserDetailsManager setUpUser() {
//        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("admin"));
//        authorities.add(new SimpleGrantedAuthority("user"));
//        authorities.add(new SimpleGrantedAuthority("visitor"));
//
//        UserDetails akhileshUser = new User("akhilesh", "akhilesh", authorities);
//        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
//        inMemoryUserDetailsManager.createUser(akhileshUser);
//        return inMemoryUserDetailsManager;
//    }
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
    @Bean
    public InMemoryUserDetailsManager setUpUsers() {
        UserDetails akhil = User
                .withUsername("akhil")
                .password("{bcrypt}$2a$12$CIFELn5/n.j4XhZgtbdeoOJaVOyLx6jX0MkqQUDMaPIlaENTn9hV6")
                .roles("admin", "user").build();
        UserDetails anil = User
                .withUsername("anil")
                .password("{bcrypt}$2a$12$02SVvCaJ1giPU/eq8MAwEub49UDRzxmZuArsa2YjRPL7ijU67.wUi")
                .roles("user")
                .build();

        return new InMemoryUserDetailsManager(akhil, anil);
    }

//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
    @Bean
    SecurityFilterChain settingUpHttpSecurity() throws Exception {
//        httpSecurity
//                .authorizeHttpRequests()
//                .anyRequest()
//                .authenticated();
//        httpSecurity.formLogin();
        //hi->authenticated, /bye-> denied, /hello -> permited by all
//        httpSecurity.authorizeHttpRequests().requestMatchers("/hi").authenticated();
//        httpSecurity.authorizeHttpRequests().requestMatchers("/bye").permitAll();
//        httpSecurity.authorizeHttpRequests().requestMatchers("/hello").denyAll();
//        httpSecurity.httpBasic();
        /*for spring security above 6.0.0*/
        httpSecurity
                .authorizeHttpRequests(customizer->{
                   customizer.requestMatchers("/hi","/hello").authenticated();
                   customizer.requestMatchers("/bye").permitAll();
                });
        httpSecurity.formLogin(Customizer.withDefaults());
        httpSecurity.httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }
    @Bean(name = "mvcHandlerMappingIntrospector")
    HandlerMappingIntrospector handlerMappingIntrospector(){
        return new HandlerMappingIntrospector();
    }
}
