package org.example.inventorymanagementsystem.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseLocationResponse {

    private Long id;

    private String locationCode;

    private String description;

}