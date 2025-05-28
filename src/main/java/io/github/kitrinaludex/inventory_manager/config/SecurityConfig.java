package io.github.kitrinaludex.inventory_manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable);
        http.httpBasic(Customizer.withDefaults());
         http.authorizeHttpRequests(auth -> auth
                         .requestMatchers("/register").permitAll()
                         .requestMatchers("/inventory/*").authenticated()
                        );


        return http.build();
    }


    @Bean
    public PasswordEncoder PasswordEncoder() {
        return NoOpPasswordEncoder.getInstance(); //todo: actually encrypt passwords
    }
}
