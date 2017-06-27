package com.alexfrndz.orderexam.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Long id;

    @NotNull(message = "'Placement Date' is required.")
    private Date placementDate;

    @NotNull(message = "'Customer Name' is required.")
    @NotEmpty(message = "'Customer Name' is required.")
    private String customerName;

    @Size(message = "'item' should be > 1")
    private List<OrderItem> items;

    private Date createdOn;

    private Date updatedOn;


}
