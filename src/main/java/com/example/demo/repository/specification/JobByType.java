package com.example.demo.repository.specification;

import com.example.demo.domain.Job;
import com.example.demo.domain.JobType;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class JobByType implements Specification<Job> {

    private final String type;

    public JobByType(final String type) {
        this.type = type;
    }

    @Override
    public Predicate toPredicate(final Root<Job> root,
                                 final CriteriaQuery<?> criteriaQuery,
                                 final CriteriaBuilder criteriaBuilder) {

        final JobType jobTypeEnumValue = JobType.getByValue(this.type);
        if (this.type != null && jobTypeEnumValue == null) {
            return criteriaBuilder.isFalse(criteriaBuilder.literal(true));
        }

        if (type == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }

        return criteriaBuilder.equal(root.get("type"), jobTypeEnumValue);
    }
}
