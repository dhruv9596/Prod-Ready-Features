package com.dhruv.prod_ready_features.prod_ready_features.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;

    private String name;

    private String email;

    private Integer age;



    private String role;

    private Double salary;
    private LocalDate dateOfJoining;
    @JsonProperty("isActive")
    private Boolean isActive;

//    public EmployeeDTO(Object o, String dhruv, String mail, int i, String admin, double v, LocalDate of, boolean b) {
//    }
}
