package Homework4.reactive.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "web-client")
public class WebClientProps {

  private String baseUrl;
}
