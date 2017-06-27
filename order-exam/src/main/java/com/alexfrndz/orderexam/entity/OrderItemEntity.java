package com.alexfrndz.orderexam.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "order_item", indexes = {
        @Index(name = "item_id", columnList = "item_id")
},
        uniqueConstraints = @UniqueConstraint(name = "idx_item_id",
                columnNames = {"item_id"}))
@lombok.Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemEntity extends DomainObject {


    @Column(name = "item_id", nullable = false, length = 100)
    private Long itemId;


    @Column(name = "item_cost", nullable = false, length = 100)
    private Double itemCost;


    @JoinColumn(name = "order_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "order_item_entity_id_pk")
    )
    @ManyToOne
    private OrderEntity order;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Double getItemCost() {
        return itemCost;
    }

    public void setItemCost(Double itemCost) {
        this.itemCost = itemCost;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public enum Paths {
        nam, id
    }


}
