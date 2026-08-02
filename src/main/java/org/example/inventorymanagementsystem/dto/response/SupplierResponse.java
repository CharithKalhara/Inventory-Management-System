package org.example.inventorymanagementsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierResponse {

    private Long id;

    private String name;

    private String contactPerson;

    private String phone;

    private String email;

    private String address;

    private Boolean status;
}