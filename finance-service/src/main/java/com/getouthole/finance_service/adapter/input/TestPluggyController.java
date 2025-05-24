package com.getouthole.finance_service.adapter.input;

import com.getouthole.finance_service.adapter.output.apis.PluggyApiImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pluggy")
@RequiredArgsConstructor
public class TestPluggyController {

    private final PluggyApiImpl pluggyApi;

    @GetMapping("/auth")
    public Mono<String> authPluggy(){
        return pluggyApi.pluggyDefineApiKey();
    }
}
