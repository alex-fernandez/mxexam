package com.alexfrndz.orderexam.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    @NotNull(message = "Item 'id' is required.")
    private Long id;

    @NotNull(message = "Item 'count' is required.")
    @Min(value = 1, message = "'count' must be > 0")
    private int count;

    private double cost;
}
