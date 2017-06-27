package com.alexfrndz.orderexam.repository;

import com.alexfrndz.orderexam.entity.ItemEntity;
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
public class ItemRepositoryImpl implements ItemRepositoryCustom {


    @Autowired
    private EntityManager em;

    @Override
    public SearchResponse<ItemEntity> search(String itemName, PaginationSearchRequest searchRequest) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ItemEntity> query = cb.createQuery(ItemEntity.class);
        List<Predicate> conditions = Lists.newArrayList();

        Root<ItemEntity> root = query.from(ItemEntity.class);
        Predicate anyPredicate = null;

        if (StringUtils.isNotEmpty(itemName)) {
            anyPredicate = cb.like(cb.lower(root.<String>get(ItemEntity.Paths.name.name())), "%" + itemName.toLowerCase() + "%");
            DBUtils.addPredicateToConditions(conditions, anyPredicate);
        }
        query.where(conditions.toArray(new Predicate[conditions.size()]));
        DBUtils.addSort(cb, query, root, "id");
        query.distinct(true);
        List<ItemEntity> entities = em.createQuery(query)
                .setFirstResult(searchRequest.getStart()).setMaxResults(searchRequest.getCount()).getResultList();
        Long total = 0L;
        //count query
        CriteriaQueryImpl<Long> countQuery = (CriteriaQueryImpl<Long>) cb.createQuery(Long.class);
        countQuery.getRoots().add(root);
        countQuery.select(cb.countDistinct(root));
        countQuery.where(conditions.toArray(new Predicate[conditions.size()]));
        total = em.createQuery(countQuery).getSingleResult();
        return new SearchResponse<ItemEntity>(searchRequest.getStart(), searchRequest.getCount(), total.intValue(), entities);
    }


}