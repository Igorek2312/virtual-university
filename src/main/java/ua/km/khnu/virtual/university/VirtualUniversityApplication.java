package ua.km.khnu.virtual.university;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

import java.util.TimeZone;

@SpringBootApplication
@PropertySource(
        value = "classpath:application-${spring.profiles.active}.properties",
        ignoreResourceNotFound = true
)
@EntityScan(basePackages = {
        "ua.km.khnu.virtual.university.model",
        "org.springframework.data.jpa.convert.threeten"
})
public class VirtualUniversityApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(VirtualUniversityApplication.class);
    }

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(VirtualUniversityApplication.class, args);
    }
}
