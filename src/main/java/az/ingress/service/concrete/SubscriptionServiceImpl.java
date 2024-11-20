package az.ingress.service.concrete;

import az.ingress.dto.SubscriptionRequest;
import az.ingress.dto.SubscriptionResponse;
import az.ingress.mapper.SubscriptionMapper;
import dao.entity.Subscription;
import az.ingress.queue.QueueSender;
import az.ingress.queue.SubscriptionMessageDTO;
import dao.repository.SubscriptionRepository;
import az.ingress.service.abstracts.SubscriptionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static az.ingress.util.ErrorCodes.NOT_FOUND;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class SubscriptionServiceImpl implements SubscriptionService {
    SubscriptionRepository repository;
    QueueSender queueSender;
    SubscriptionMapper mapper;

    public SubscriptionResponse createSubscription(SubscriptionRequest request) {
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime endDate = switch (request.getSubscriptionType().toUpperCase()) {
            case "WEEKLY" -> now.plusWeeks(1);
            case "MONTHLY" -> now.plusMonths(1);
            default -> throw new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND.getAzMsg());
        };
        var subscription = repository.save(
                Subscription.builder()
                        .productId(request.getProductId())
                        .supplierId(request.getSupplierId())
                        .subscriptionType(request.getSubscriptionType())
                        .startDate(now)
                        .endDate(endDate)
                        .build()
        );
        return mapper.toResponse(subscription);
    }

    @PostConstruct
    public void sendToQueue() {
        queueSender.sendMessageToQueue(new SubscriptionMessageDTO("123e4567-e89b-12d3-a456-426614174000"));
    }

    @Scheduled(cron = "0 */5 * * * *")
    public void checkExpiredSubscriptions() {
        List<Subscription> expiredSubscriptions = repository.findByEndDateBeforeAndStatus(LocalDateTime.now(), Subscription.Status.ACTIVE);
        for (Subscription subscription : expiredSubscriptions) {
            subscription.setStatus(Subscription.Status.EXPIRED);
            repository.save(subscription);
            queueSender.sendMessageToQueue(new SubscriptionMessageDTO(subscription.getProductId().toString()));

        }

    }


    public void cancelSubscription(UUID subscriptionId) {
        Subscription subscription = repository.findById(subscriptionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND.getAzMsg()));

        subscription.setStatus(Subscription.Status.CANCELED);
        repository.save(subscription);
        queueSender.sendMessageToQueue(new SubscriptionMessageDTO(subscription.getProductId().toString()));
    }

}



