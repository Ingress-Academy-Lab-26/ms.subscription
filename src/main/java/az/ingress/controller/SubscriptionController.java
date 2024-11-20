package az.ingress.controller;

import az.ingress.dto.SubscriptionRequest;
import az.ingress.dto.SubscriptionResponse;
import az.ingress.service.abstracts.SubscriptionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("v1/subscriptions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SubscriptionController {

    SubscriptionService service;

    @PostMapping
    public ResponseEntity<SubscriptionResponse> createSubscription(@RequestBody SubscriptionRequest request) {
        return ResponseEntity.status(CREATED).body(service.createSubscription(request));

    }

    @PostMapping("/cancel/{subscriptionId}")
    public ResponseEntity<Void> cancelSubscription(@PathVariable UUID subscriptionId) {
        service.cancelSubscription(subscriptionId);
        return ResponseEntity.noContent().build();
    }
}
