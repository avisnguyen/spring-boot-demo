package xyz.nhatbao.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringDemoApplication {
    /*The @ComponentScan annotation is used to find beans and the corresponding injected with @Autowired annotation.*/
    @Autowired
    private RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringDemoApplication.class, args);
    }

    /*With Spring Boot, no need to specify any arguments for @ComponentScan annotation.
    All component class files are automatically registered with Spring Beans*/
    @Bean
    public RestTemplate getRestTemplate() {
        System.out.println("Return New Rest Template");
        return new RestTemplate();
    }
}
