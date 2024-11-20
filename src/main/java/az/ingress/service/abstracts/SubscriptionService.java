package az.ingress.service.abstracts;

import az.ingress.dto.SubscriptionRequest;
import az.ingress.dto.SubscriptionResponse;

import java.util.UUID;

public interface SubscriptionService {
    SubscriptionResponse createSubscription(SubscriptionRequest request);

    void cancelSubscription(UUID subscriptionId);
}
