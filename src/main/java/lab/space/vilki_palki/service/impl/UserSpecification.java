package lab.space.vilki_palki.service.impl;

import jakarta.persistence.criteria.Predicate;
import lab.space.vilki_palki.entity.User;
import lab.space.vilki_palki.model.user.UserRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;

@Component
public class UserSpecification {
    public Specification<User> getUsersByRequest(UserRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (nonNull(request.getQuery()) && !Objects.equals(request.getQuery(), "")) {
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%" + request.getQuery().toLowerCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + request.getQuery().toLowerCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("facebookId")), "%" + request.getQuery().toLowerCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("phone")), "%" + request.getQuery().toLowerCase() + "%")
                ));
            }
            query.orderBy(criteriaBuilder.desc(root.get("createAt")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
