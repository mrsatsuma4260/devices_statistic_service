package com.example.demo.repository.specification;

import com.example.demo.domain.Job;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;

public class JobByTimeTo implements Specification<Job> {

    private final LocalDateTime timeTo;

    public JobByTimeTo(final LocalDateTime timeTo) {
        this.timeTo = timeTo;
    }

    @Override
    public Predicate toPredicate(final Root<Job> root,
                                 final CriteriaQuery<?> criteriaQuery,
                                 final CriteriaBuilder criteriaBuilder) {

        if (timeTo == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }

        return criteriaBuilder.lessThanOrEqualTo(root.get("time"), this.timeTo);
    }
}
