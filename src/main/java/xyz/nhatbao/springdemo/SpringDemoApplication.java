package xyz.nhatbao.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableScheduling
@SpringBootApplication
@RestController
public class SpringDemoApplication {
    /*The @ComponentScan annotation is used to find beans and the corresponding injected with @Autowired annotation.*/
    @Autowired
    private RestTemplate restTemplate;

    //    @Value annotation is used to read the environment or application property value
    //    @Value("${spring.application.name:spring-boot-demo}") to set default value if the property is not found
    @Value("${spring.application.name}")
    private String name;

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

    //    Show the application name
    @RequestMapping(value = "/")
    public String name() {
        return "Application name: " + name;
    }
}
