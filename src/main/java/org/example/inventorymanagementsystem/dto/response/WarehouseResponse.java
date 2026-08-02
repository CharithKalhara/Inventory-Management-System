package org.example.inventorymanagementsystem.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseResponse {

    private Long id;

    private String code;

    private String name;

    private String address;

    private Boolean active;

    private List<WarehouseLocationResponse> locations;

}