package com.alexfrndz.orderexam.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    @NotNull(message = "'id' is required.")
    @NotEmpty(message = "'id' is required.")
    private Long id;

    @NotNull(message = "'name' is required.")
    @NotEmpty(message = "'name' is required.")
    private int count;

    private double itemCost;
}
