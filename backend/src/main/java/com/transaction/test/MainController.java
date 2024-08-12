package com.transaction.test;

import com.transaction.test.entities.PaymentData;
import com.transaction.test.entities.PaymentSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class MainController {
    @Value("${FLOUCI.DEVID:default_value}")
    private String FLOUCI_DEVID;
    @Value("${FLOUCI.PUBLIC.TOKEN:default_value}")
    private String FLOUCI_PUBLIC;
    @Value("${FLOUCI.SECRET.TOKEN:default_value}")
    private String FLOUCI_SECRET;
    @Value("${FRONTEND.DOMAIN:default_value}")
    private String FrontEndDomain;
    @PostMapping("/generatePayment")
    public String GeneratePayment(@RequestBody PaymentData data) {
        RestTemplate restTemplate = new RestTemplate();
        PaymentSettings Settings = new PaymentSettings(
                FLOUCI_PUBLIC,
                FLOUCI_SECRET,
                data.isCardsPayment(),
                data.getAmount(),
                FrontEndDomain,
                FrontEndDomain,
                FLOUCI_DEVID
        );

        String Url = "https://developers.flouci.com/api/generate_payment";

        ResponseEntity<String> response = restTemplate.postForEntity(Url,Settings, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to fetch data: " + response.getStatusCode());
        }
    }

    @GetMapping("/verifyPayment/{ProductID}")
    public String VerifyPayment(@PathVariable("ProductID") String data) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://developers.flouci.com/api/verify_payment/";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("apppublic", FLOUCI_PUBLIC);
        headers.set("appsecret", FLOUCI_SECRET);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url + data,
                HttpMethod.GET,
                entity,
                String.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to fetch data: " + response.getStatusCode());
        }
    }

}
