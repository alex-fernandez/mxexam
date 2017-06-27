package com.alexfrndz.orderexam.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class DBUtils {
    public static void addPredicateToConditions(List<Predicate> conditions, Predicate predicate) {
        if (predicate != null) {
            conditions.add(predicate);
        }
    }

    public static void addSort(CriteriaBuilder cb, CriteriaQuery<?> query, Root<?> root, String field) {
        Order sort = cb.asc(root.<Long>get(field));
        query.orderBy(sort);
    }

}
