package ua.km.khnu.virtual.university.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        return objectMapper;
    }

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        org.modelmapper.config.Configuration configuration = modelMapper.getConfiguration();
        configuration.setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
}
