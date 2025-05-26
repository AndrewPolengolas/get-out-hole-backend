package com.getouthole.finance_service.adapter.input;

import com.getouthole.finance_service.adapter.output.apis.PluggyApiImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pluggy")
@RequiredArgsConstructor
public class TestPluggyController {

    private final PluggyApiImpl pluggyApi;

    @GetMapping("/auth")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> authPluggy() {
        String response = pluggyApi.pluggyDefineApiKey();
        return ResponseEntity.ok(response);
    }

}
