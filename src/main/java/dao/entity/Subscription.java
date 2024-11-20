package dao.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subscription")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Subscription implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    UUID productId;
    UUID supplierId;
    LocalDateTime startDate;
    LocalDateTime endDate;
    String subscriptionType;

    @Enumerated(EnumType.STRING)
    Status status;


    public enum Status {
        ACTIVE,
        EXPIRED,
        CANCELED
    }
}
