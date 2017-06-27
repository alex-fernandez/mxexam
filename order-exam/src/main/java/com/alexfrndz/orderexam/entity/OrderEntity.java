package com.alexfrndz.orderexam.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
@lombok.Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity extends DomainObject {

    @Column(name = "customer_name", nullable = false, length = 100)
    private String customerName;

    @Column(name = "placement_date", nullable = false)
    private Date placementDate;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "order_item_entity_id_fk")
    )
    private Set<OrderItemEntity> orderItems;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Set<OrderItemEntity> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItemEntity> orderItems) {
        this.orderItems = orderItems;
    }

    public Date getPlacementDate() {
        return placementDate;
    }

    public void setPlacementDate(Date placementDate) {
        this.placementDate = placementDate;
    }

    public enum Paths {
         name, id
    }

    public boolean equals(Object o) {
        return ((this == o) || ((o != null) &&
                (getClass() == o.getClass()) &&
                customerName.equals(((OrderEntity) o).customerName))
        );
    }

}
