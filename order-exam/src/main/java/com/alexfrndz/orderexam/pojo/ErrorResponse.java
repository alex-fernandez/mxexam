package com.alexfrndz.orderexam.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ErrorResponse {

    private String errorCode;
    private String errorMessage;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ErrorResponse {\n");
        sb.append("  errorCode: ").append(errorCode).append("\n");
        sb.append("  errorMessage: ").append(errorMessage).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
