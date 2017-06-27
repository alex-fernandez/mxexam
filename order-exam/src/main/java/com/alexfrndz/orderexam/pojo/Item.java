package com.alexfrndz.orderexam.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private Long id;

    @NotNull(message = "'name' is required.")
    @NotEmpty(message = "'name' is required.")
    private String name;

    @NotNull(message = "'cost' is required.")
    @NotEmpty(message = "'cost' is required.")
    private Double cost;

    private Date createdOn;

    private Date updatedOn;

}
