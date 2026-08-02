package org.example.inventorymanagementsystem.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequest {

    private String customerCode;

    private String name;

    private String email;

    private String phone;

    private String address;

    private Boolean active;
}