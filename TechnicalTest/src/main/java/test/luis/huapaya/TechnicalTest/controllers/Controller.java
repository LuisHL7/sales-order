package test.luis.huapaya.TechnicalTest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
public class Controller {

    @GetMapping(value = "/salesOrder")
    public List<Object> getSalesOrder(){
        String url = "https://api.holded.com/api/invoicing/v1/documents/salesorder";
        RestTemplate restTemplate = new RestTemplate();

        Object[] salesOrder = restTemplate.getForObject(url, Object[].class);

        return Arrays.asList(Objects.requireNonNull(salesOrder));

    }

    @RequestMapping("/")
    public String home(){
        return "Hello World!";
    }
}
