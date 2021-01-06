package xyz.nhatbao.springdemo.controller.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import xyz.nhatbao.springdemo.model.Product;

import java.util.Arrays;

// Product ResTemplate Controller
@RestController
@RequestMapping(value = "/template")
public class ProductTemplateController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.port:demoservice}")
    private String port;

    @RequestMapping(value = "/products")
    public String getAllProducts() {
        //  Define new headers
        HttpHeaders headers = new HttpHeaders();

        //  Allow JSON type response
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        //  To get result as String
        HttpEntity<String> entity = new HttpEntity(headers);

        //  Send GET request whith headers
        return restTemplate.exchange("http://localhost:" + port + "/products", HttpMethod.GET, entity, String.class).getBody();
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public String createProduct(@RequestBody Product product) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Product> entity = new HttpEntity<Product>(product, headers);
        return restTemplate.exchange("http://localhost:" + port + "/products", HttpMethod.POST, entity, String.class).getBody();
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public String updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Product> entity = new HttpEntity<Product>(product, headers);
        return restTemplate.exchange("http://localhost:" + port + "/products/" + id, HttpMethod.PUT, entity, String.class).getBody();
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public String deleteProduct(@PathVariable("id") String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Product> entity = new HttpEntity<Product>(headers);
        return restTemplate.exchange("http://localhost:" + port + "/products/" + id, HttpMethod.DELETE, entity, String.class).getBody();
    }
}
