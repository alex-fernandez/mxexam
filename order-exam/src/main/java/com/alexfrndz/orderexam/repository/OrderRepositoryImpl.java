package com.alexfrndz.orderexam.repository;

import com.alexfrndz.orderexam.entity.OrderEntity;
import com.alexfrndz.orderexam.pojo.PaginationSearchRequest;
import com.alexfrndz.orderexam.pojo.SearchResponse;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.jpa.criteria.CriteriaQueryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Slf4j
public class OrderRepositoryImpl implements OrderRepositoryCustom {


    @Autowired
    private EntityManager em;

    @Override
    public SearchResponse<OrderEntity> search(String customerName, PaginationSearchRequest searchRequest) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<OrderEntity> query = cb.createQuery(OrderEntity.class);
        List<Predicate> conditions = Lists.newArrayList();

        Root<OrderEntity> venueRoot = query.from(OrderEntity.class);
        Predicate anyPredicate = null;

        if (StringUtils.isNotEmpty(customerName)) {
            anyPredicate = cb.like(cb.lower(venueRoot.<String>get(OrderEntity.Paths.name.name())), "%" + customerName.toLowerCase() + "%");
            DBUtils.addPredicateToConditions(conditions, anyPredicate);
        }
        query.where(conditions.toArray(new Predicate[conditions.size()]));
        DBUtils.addSort(cb, query, venueRoot, "id");
        query.distinct(true);
        List<OrderEntity> entities = em.createQuery(query)
                .setFirstResult(searchRequest.getStart()).setMaxResults(searchRequest.getCount()).getResultList();
        Long total = 0L;
        //count query
        CriteriaQueryImpl<Long> countQuery = (CriteriaQueryImpl<Long>) cb.createQuery(Long.class);
        countQuery.getRoots().add(venueRoot);
        countQuery.select(cb.countDistinct(venueRoot));
        countQuery.where(conditions.toArray(new Predicate[conditions.size()]));
        total = em.createQuery(countQuery).getSingleResult();
        return new SearchResponse<OrderEntity>(searchRequest.getStart(), searchRequest.getCount(), total.intValue(), entities);
    }


}