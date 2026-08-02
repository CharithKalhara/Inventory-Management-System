package org.example.inventorymanagementsystem.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.request.UnitRequest;
import org.example.inventorymanagementsystem.dto.response.UnitResponse;
import org.example.inventorymanagementsystem.service.interfaces.UnitService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/units")
@RequiredArgsConstructor
public class UnitController {

    private final UnitService unitService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UnitResponse create(@Valid @RequestBody UnitRequest request) {
        return unitService.create(request);
    }

    @GetMapping
    public List<UnitResponse> getAll() {
        return unitService.getAll();
    }

    @GetMapping("/{id}")
    public UnitResponse getById(@PathVariable Long id) {
        return unitService.getById(id);
    }

    @PutMapping("/{id}")
    public UnitResponse update(@PathVariable Long id,
                               @Valid @RequestBody UnitRequest request) {
        return unitService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        unitService.delete(id);
    }
}