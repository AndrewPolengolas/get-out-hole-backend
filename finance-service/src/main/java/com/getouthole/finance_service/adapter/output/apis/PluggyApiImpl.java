package com.getouthole.finance_service.adapter.output.apis;

import com.getouthole.finance_service.infrastructure.config.properties.PluggyProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PluggyApiImpl implements PluggyApiInterface{

    private final PluggyProperties pluggyProps;
    private final RestTemplate restTemplate;

    @Override
    public String pluggyDefineApiKey() {
        if (pluggyProps.getClientId().isEmpty() || pluggyProps.getClientSecret().isEmpty()) {
            throw new RuntimeException("ClientId ou ClientSecret está vazio");
        }

        String url = "https://api.pluggy.ai/auth";

        Map<String, String> body = Map.of(
                "clientId", pluggyProps.getClientId(),
                "clientSecret", pluggyProps.getClientSecret()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Pluggy retornou: " + response.getBody());
                return response.getBody();
            } else {
                throw new RuntimeException("Erro da Pluggy: " + response.getStatusCode());
            }
        } catch (Exception e) {
            System.out.println("Erro ao chamar Pluggy: " + e.getMessage());
            throw new RuntimeException("Erro ao chamar Pluggy", e);
        }
    }

    @Override
    public String pluggyDefineConnectToken(String UserId, String apiKey) {

//        if (UserId.isEmpty() || apiKey.isEmpty()) {
//            throw new RuntimeException();
//        }
//
//        Mono<String> response = pluggyWebClient.post()
//                .uri("/connect_token")
//                .header("X-API-KEY", apiKey)
//                .bodyValue(Map.of(
//                        "clientId", pluggyProps.getClientId(),
//                        "clientSecret", pluggyProps.getClientSecret()
//                ))
//                .retrieve()
//                .bodyToMono(String.class);
//
//        System.out.println(response);
//
//        return response;
        return "";
    }

    private String extractApiKey(String responseBody) {
        // Extração simples pra teste, depois você pode usar uma lib JSON como Jackson
        int index = responseBody.indexOf("apiKey");
        if (index == -1) return null;

        int start = responseBody.indexOf(":", index) + 2;
        int end = responseBody.indexOf("\"", start);
        return responseBody.substring(start, end);
    }


}
