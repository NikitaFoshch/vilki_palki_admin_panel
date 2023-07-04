package lab.space.vilki_palki.service.impl;

import lab.space.vilki_palki.entity.Order;
import lab.space.vilki_palki.model.order.OrderRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;

@Component
public class OrderSpecification {

    public Specification<Order> getOrdersByRequest(OrderRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (nonNull(request.getQuery()) && !Objects.equals(request.getQuery(), "")) {
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("orderCode")), "%" + request.getQuery().toLowerCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("products")), "%" + request.getQuery().toLowerCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), "%" + request.getQuery().toLowerCase() + "%")
                ));
            }

            if (nonNull(request.getDeliveryStatus())) {
                if ((request.getDeliveryStatus().name().equalsIgnoreCase("ACCEPT"))
                        || (request.getDeliveryStatus().name().equalsIgnoreCase("ON_WAY"))
                        || (request.getDeliveryStatus().name().equalsIgnoreCase("IN_PROCESS"))) {
                    predicates.add(criteriaBuilder.or(
                            criteriaBuilder.equal(root.get("deliveryStatus"), Order.DeliveryStatus.ACCEPT),
                            criteriaBuilder.equal(root.get("deliveryStatus"), Order.DeliveryStatus.ON_WAY),
                            criteriaBuilder.equal(root.get("deliveryStatus"), Order.DeliveryStatus.IN_PROCESS)
                    ));
                } else {
                    predicates.add(criteriaBuilder.or(
                            criteriaBuilder.equal(root.get("deliveryStatus"), Order.DeliveryStatus.CANCELED),
                            criteriaBuilder.equal(root.get("deliveryStatus"), Order.DeliveryStatus.DONE)
                    ));
                }
            }
            if (nonNull(request.getUserId())) {
                predicates.add(criteriaBuilder.equal(root.get("user").get("id"), request.getUserId()));
            }
            query.orderBy(criteriaBuilder.desc(root.get("createAt")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}