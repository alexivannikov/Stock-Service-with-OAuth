package root.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
class RestConfiguration {

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
