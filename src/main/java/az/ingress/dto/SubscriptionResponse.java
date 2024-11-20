package az.ingress.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubscriptionResponse {
    UUID id;
    UUID productId;
    UUID supplierId;
    String subscriptionType;
    LocalDateTime startDate;
    LocalDateTime endDate;
}
