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
public class UnitResponse {

    private Long id;

    private String name;

    private String shortName;

    private String description;

    private Boolean status;
}