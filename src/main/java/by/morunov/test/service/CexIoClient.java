package by.morunov.test.service;

import by.morunov.test.domain.dto.CryptoсurrencyDto;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Alex Morunov
 */
@Component
public class CexIoClient {

    HttpClient httpClient = HttpClientBuilder.create().build();
    ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
    private RestTemplate restTemplate = new RestTemplate(requestFactory);
    List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

    public List<CryptoсurrencyDto> getCurrencies() {
        String urlBtc = "https://cex.io/api/last_price/BTC/USD";
        String urlEth = "https://cex.io/api/last_price/ETH/USD";
        String urlSol = "https://cex.io/api/last_price/SOL/USD";
        try {
            converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
            messageConverters.add(converter);
            restTemplate.setMessageConverters(messageConverters);
            List<CryptoсurrencyDto> list = new ArrayList<>();
            list.add(restTemplate.getForObject(new URI(urlBtc), CryptoсurrencyDto.class));
            list.add(restTemplate.getForObject(new URI(urlEth), CryptoсurrencyDto.class));
            list.add(restTemplate.getForObject(new URI(urlSol), CryptoсurrencyDto.class));
            return list;

        } catch (URISyntaxException e) {
            throw new RuntimeException();
        }

    }

}
