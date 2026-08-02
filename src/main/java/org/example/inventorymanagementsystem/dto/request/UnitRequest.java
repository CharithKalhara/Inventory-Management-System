package org.example.inventorymanagementsystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnitRequest {

    @NotBlank(message = "Unit name is required")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "Short name is required")
    @Size(max = 20)
    private String shortName;

    @Size(max = 255)
    private String description;

    private Boolean status = true;
}