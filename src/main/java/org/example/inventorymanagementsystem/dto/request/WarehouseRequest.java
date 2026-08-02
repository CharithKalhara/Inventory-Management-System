package org.example.inventorymanagementsystem.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseRequest {

    private String code;

    private String name;

    private String address;

    private Boolean active;

}