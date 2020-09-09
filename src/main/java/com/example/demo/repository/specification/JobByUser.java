package com.example.demo.repository.specification;

import com.example.demo.domain.Job;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class JobByUser implements Specification<Job> {

    private final String user;

    public JobByUser(final String user) {
        this.user = user;
    }

    @Override
    public Predicate toPredicate(final Root<Job> root,
                                 final CriteriaQuery<?> criteriaQuery,
                                 final CriteriaBuilder criteriaBuilder) {
        if (user == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }

        return criteriaBuilder.equal(root.get("user"), user);
    }
}
