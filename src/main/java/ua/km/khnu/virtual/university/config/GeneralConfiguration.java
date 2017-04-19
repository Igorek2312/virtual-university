package ua.km.khnu.virtual.university.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
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
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<Object, Object>() {
            @Override
            protected void configure() {
                map(source,destination);
            }
        });
        return modelMapper;
    }
}
