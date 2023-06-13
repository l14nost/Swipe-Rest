package com.example.SwipeRest.specification;

import com.example.SwipeRest.entity.User;
import com.example.SwipeRest.enums.TypeUser;
import javax.persistence.criteria.*;

import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;

@Builder
public class UserSpecification implements Specification<User> {
    private String keyWord;
    private TypeUser typeUser;

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        query.distinct(true);

        Predicate predicate = criteriaBuilder.and(
                criteriaBuilder.isFalse(root.get("blackList")),
                criteriaBuilder.equal(root.get("typeUser"),typeUser),
                criteriaBuilder.or(
                        criteriaBuilder.like(root.get("name"), "%" + keyWord + "%"),
                        criteriaBuilder.like(root.get("surname"), "%" + keyWord + "%")
//                        criteriaBuilder.like(root.get("mail"), "%" + keyWord + "%")
//                    criteriaBuilder.equal(root.get("id"), userSearchingDto.getName())

                )
        );
        query.orderBy(criteriaBuilder.asc(root.get("surname")));
        return predicate;


    }
}
