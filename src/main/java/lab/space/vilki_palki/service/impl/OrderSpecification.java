package lab.space.vilki_palki.service.impl;

import jakarta.persistence.criteria.Predicate;
import lab.space.vilki_palki.entity.Address;
import lab.space.vilki_palki.entity.Order;
import lab.space.vilki_palki.model.AddressRequest;
import lab.space.vilki_palki.model.OrderRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;

@Component
public class OrderSpecification {
    public Specification<Order> getOrdersByRequestByOrderByUserId(OrderRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (nonNull(request.getQuery()) && !Objects.equals(request.getQuery(), "")) {
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("products")), "%" + request.getQuery().toLowerCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), "%" + request.getQuery().toLowerCase() + "%")
                ));
            }
            if (nonNull(request.getUserId())) {
                predicates.add(criteriaBuilder.equal(root.get("user").get("id"), request.getUserId()));
            }
            query.orderBy(criteriaBuilder.desc(root.get("createAt")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

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
            if (nonNull(request.getUserId())) {
                predicates.add(criteriaBuilder.equal(root.get("user").get("id"), request.getUserId()));
            }
            query.orderBy(criteriaBuilder.desc(root.get("createAt")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}