package com.getouthole.finance_service.adapter.output.apis;

import reactor.core.publisher.Mono;

public interface PluggyApiInterface {

    String pluggyDefineApiKey();

    String pluggyDefineConnectToken(String UserId, String apiKey);
}
