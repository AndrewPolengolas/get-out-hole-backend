package com.getouthole.finance_service.infrastructure.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "pluggy")
@Getter
@Setter
public class PluggyProperties {
    private String clientId;
    private String clientSecret;
}
