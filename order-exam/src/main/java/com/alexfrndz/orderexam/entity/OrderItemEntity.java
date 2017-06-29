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
        uniqueConstraints = @UniqueConstraint(name = "unique_order_item_id",
                columnNames = {"order_id", "item_id"}))
@lombok.Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemEntity extends DomainObject {


    @Column(name = "item_id", nullable = false)
    private Long itemId;

    @Column(name = "count", nullable = false)
    private int count;

    @Column(name = "cost", nullable = false)
    private Double cost;


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

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public enum Paths {
        id
    }


}
