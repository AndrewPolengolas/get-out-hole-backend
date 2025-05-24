package com.getouthole.finance_service.adapter.output.apis;

import reactor.core.publisher.Mono;

public interface PluggyApiInterface {

    Mono<String> pluggyDefineApiKey();

    Mono<String> pluggyDefineConnectToken(String UserId, String apiKey);
}
