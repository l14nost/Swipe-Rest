package com.example.SwipeRest.specification;

import com.example.SwipeRest.entity.Apartment;
import javax.persistence.criteria.*;

import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;

@Builder
public class ApartmentForLcdSpecification implements Specification<Apartment> {
    private int keyWord;

    @Override
    public Predicate toPredicate(Root<Apartment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        query.distinct(true);

        Predicate predicate = criteriaBuilder.and(
                criteriaBuilder.isNull(root.get("frame")),
                criteriaBuilder.or(
                        criteriaBuilder.equal(root.get("number"),keyWord)
//                        criteriaBuilder.like(root.get("surname"), "%" + keyWord + "%"),
//                        criteriaBuilder.like(root.get("mail"), "%" + keyWord + "%")
//                    criteriaBuilder.equal(root.get("id"), userSearchingDto.getName())

                )
        );
        query.orderBy(criteriaBuilder.asc(root.get("number")));
        return predicate;


    }
}
