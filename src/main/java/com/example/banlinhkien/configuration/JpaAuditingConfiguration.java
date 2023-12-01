package com.example.banlinhkien.configuration;


import com.example.banlinhkien.other.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvide")
public class JpaAuditingConfiguration {
    @Bean
    public AuditorAware<String> auditorProvide() {
        return new AuditorAwareImpl();
    }
}
