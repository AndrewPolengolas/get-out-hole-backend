package com.getouthole.finance_service.adapter.output.apis;

import com.getouthole.finance_service.infrastructure.config.properties.PluggyProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PluggyApiImpl implements PluggyApiInterface{

    private final PluggyProperties pluggyProps;
    private final WebClient pluggyWebClient;

    @Override
    public Mono<String> pluggyDefineApiKey() {

        if (pluggyProps.getClientId().isEmpty() || pluggyProps.getClientSecret().isEmpty()){
            throw new RuntimeException();
        }

        Mono<String> response = pluggyWebClient.post()
                .uri("/auth")
                .bodyValue(Map.of(
                        "clientId", pluggyProps.getClientId(),
                        "clientSecret", pluggyProps.getClientSecret()
                ))
                .retrieve()
                .bodyToMono(String.class);

        System.out.println(response);

        return response;
    }

    @Override
    public Mono<String> pluggyDefineConnectToken(String UserId, String apiKey) {

        if (UserId.isEmpty() || apiKey.isEmpty()) {
            throw new RuntimeException();
        }

        Mono<String> response = pluggyWebClient.post()
                .uri("/connect_token")
                .header("X-API-KEY", apiKey)
                .bodyValue(Map.of(
                        "clientId", pluggyProps.getClientId(),
                        "clientSecret", pluggyProps.getClientSecret()
                ))
                .retrieve()
                .bodyToMono(String.class);

        System.out.println(response);

        return response;
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
