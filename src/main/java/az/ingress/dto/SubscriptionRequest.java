package az.ingress.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubscriptionRequest {
     UUID productId;
     UUID supplierId;
     String subscriptionType;
}
