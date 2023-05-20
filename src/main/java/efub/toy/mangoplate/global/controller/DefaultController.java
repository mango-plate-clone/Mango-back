package efub.toy.mangoplate.global.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DefaultController {
    @GetMapping("/health")
    public ResponseEntity healthCheck() {
        return ResponseEntity.ok(null);
    }
}

