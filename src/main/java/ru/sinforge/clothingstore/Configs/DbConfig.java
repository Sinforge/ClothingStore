package ru.sinforge.clothingstore.Configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = {
                "ru.sinforge.clothingstore.Repositories"
        }
)
public class DbConfig {
}
