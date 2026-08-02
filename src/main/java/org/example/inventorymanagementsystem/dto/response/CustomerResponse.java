package org.example.inventorymanagementsystem.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {

    private Long id;

    private String customerCode;

    private String name;

    private String email;

    private String phone;

    private String address;

    private Boolean active;
}