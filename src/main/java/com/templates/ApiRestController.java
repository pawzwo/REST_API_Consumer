package com.templates;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/countries")
public class ApiRestController {

    @Autowired
    private RestTemplate restTemplate;

    private static String countriesUrl = "https://restcountries.com/v3.1/all";

    @GetMapping("/show-capitals")
    public List<Object> getCountries() {
        Object[] countries = restTemplate.getForObject(countriesUrl, Object[].class);
        ObjectMapper mapper = new ObjectMapper();
        List<String[]> capitals = Arrays.stream(countries).map(o->mapper.convertValue(o, Country.class)).map(Country::getCapital).collect(Collectors.toList());
        for (String[] s:capitals) {
            System.out.println(Arrays.toString(s));
        }
        return Arrays.asList(capitals);
    }

}
