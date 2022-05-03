package com.example.demo.Model.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OneStringDTO {

    String oneString;

    Double oneDouble;

    public String getOneString() {
        return oneString;
    }

    public void setOneString(String oneString) {
        this.oneString = oneString;
    }

    public Double getOneDouble() {
        return oneDouble;
    }

    public void setOneDouble(Double oneDouble) {
        this.oneDouble = oneDouble;
    }
}
