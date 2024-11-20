//package az.ingress.config;
//
//import com.rabbitmq.client.ConnectionFactory;
//import lombok.RequiredArgsConstructor;
//import lombok.Value;
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@RequiredArgsConstructor
//public class RabbitMQConfig {
//
//    private final String subscriptionQ;
//    private final String subscriptionDQL;
//    private final String subscriptionQExchange;
//    private final String subscriptionDQLExchange;
//    private final String subscriptionQKey;
//    private final String subscriptionDQLKey;
//
//
//    public RabbitMQConfig(@Value("${rabbitmq.queue.subscription}") String subscriptionQ,
//                          @Value("subscription_DQL") String subscriptionDQL) {
//        this.subscriptionQ = subscriptionQ;
//        this.subscriptionDQL = subscriptionDQL;
//        this.subscriptionQExchange = subscriptionQExchange + "_Exchange";
//        this.subscriptionDQLExchange = subscriptionDQLExchange + "_Exchange";
//        this.subscriptionQKey = subscriptionQKey + "_Key";
//        this.subscriptionDQLKey = subscriptionDQLKey + "_Key";
//    }
//
//    @Bean
//    DirectExchange subscriptionQExchange() {
//        return new DirectExchange(subscriptionQExchange);
//    }
//
//    @Bean
//    DirectExchange subscriptionDQLExchange() {
//        return new DirectExchange(subscriptionDQLExchange);
//    }
//
//    @Bean
//    Queue subscriptionQ() {
//        return QueueBuilder.durable(subscriptionQ)
//                .withArgument("x-dead-letter-exchange", subscriptionDQLExchange)
//                .withArgument("x-dead-letter-routing-key", subscriptionDQLKey)
//                .build();
//    }
//
//    @Bean
//    Queue subscriptionDQL() {
//        return QueueBuilder.durable(subscriptionDQL).build();
//    }
//
//    @Bean
//    Binding subscriptionQBinding() {
//        return BindingBuilder.bind(subscriptionQ())
//                .to(subscriptionQExchange()).with(subscriptionQKey);
//    }
//
//    @Bean
//    Binding subscriptionDQLBinding() {
//        return BindingBuilder.bind(subscriptionDQL())
//                .to(subscriptionDQLExchange()).with(subscriptionDQLKey);
//
//    }
//
//
//}
