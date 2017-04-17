package ua.km.khnu.virtual.university.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Igor Rybak
 */
@Configuration
public class GeneralConfiguration {
    @Bean
    public CloseableHttpClient httpClient(){
        return HttpClients.createDefault();
    }
}
