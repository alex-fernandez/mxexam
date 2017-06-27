package com.alexfrndz.orderexam.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private Long id;

    @NotNull(message = "'Placement Date' is required.")
    private Date placementDate;

    @NotNull(message = "'Customer Name' is required.")
    @NotEmpty(message = "'Customer Name' is required.")
    private String customerName;

    @Size(message = "'item' should be > 1")
    private Set<OrderItem> items;

    private Date createdOn;

    private Date updatedOn;


}
