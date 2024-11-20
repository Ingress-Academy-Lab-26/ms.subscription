package az.ingress.mapper;

import az.ingress.dto.SubscriptionResponse;
import dao.entity.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubscriptionMapper {

    SubscriptionResponse toResponse(Subscription subscription);

}
