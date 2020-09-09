package com.example.demo.repository.specification;

import com.example.demo.domain.Job;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;

public class JobByTimeFrom implements Specification<Job> {

    private final LocalDateTime timeFrom;

    public JobByTimeFrom(final LocalDateTime timeFrom) {
        this.timeFrom = timeFrom;
    }

    @Override
    public Predicate toPredicate(final Root<Job> root,
                                 final CriteriaQuery<?> criteriaQuery,
                                 final CriteriaBuilder criteriaBuilder) {

        if (timeFrom == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }

        return criteriaBuilder.greaterThanOrEqualTo(root.get("time"), this.timeFrom);
    }
}
