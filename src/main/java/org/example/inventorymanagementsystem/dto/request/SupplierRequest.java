package org.example.inventorymanagementsystem.dto.request;

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
public class SupplierRequest {

    private String name;

    private String contactPerson;

    private String phone;

    private String email;

    private String address;

    private Boolean status;
}