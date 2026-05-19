package org.example.healthcare.config;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws  Exception{
        http.csrf(csrf->csrf.disable())
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/rendezVous").hasAnyRole("ADMIN","PATIENT","MEDECIN")
                        .requestMatchers(HttpMethod.GET,"/api/dossier/{id}").hasAnyRole("ADMIN","PATIENT","MEDECIN")
                        .requestMatchers(HttpMethod.PUT,"/api/dossier/{id}/diagnostic").hasAnyRole("ADMIN","MEDECIN")
                        .requestMatchers(HttpMethod.PUT,"/api/dossier/{id}/observations").hasAnyRole("ADMIN","MEDECIN")
                        .requestMatchers(HttpMethod.POST,"/api/dossier").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/rendezVous/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/rendezVous").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH,"/api/rendezVous/{id}/annuler").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/rendezVous/patient/{patientId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/rendezVous/medecin/{medecinId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/patient").hasAnyRole("ADMIN","PATIENT")
                        .requestMatchers(HttpMethod.PUT,"/api/patient/{id}").hasAnyRole("ADMIN","PATIENT")
                        .requestMatchers(HttpMethod.GET,"/api/patient/{id}").hasAnyRole("ADMIN","PATIENT")
                        .requestMatchers(HttpMethod.DELETE,"/api/patient/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/patient").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/patient").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/medecin").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/medecin").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/medecin/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/medecin/{id}").hasRole("ADMIN")
                        .anyRequest().authenticated()

                ).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

