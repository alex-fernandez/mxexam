package com.alexfrndz.orderexam.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "items", indexes = {
        @Index(name = "name", columnList = "name")
},
        uniqueConstraints = @UniqueConstraint(name = "idx_product_entity_id",
                columnNames = {"name"}))
@lombok.Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemEntity extends DomainObject {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "cost", nullable = false)
    private Double cost;

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public enum Paths {
        name, id
    }

}
