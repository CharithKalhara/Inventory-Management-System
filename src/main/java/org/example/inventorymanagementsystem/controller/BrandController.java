package org.example.inventorymanagementsystem.controller;

import org.example.inventorymanagementsystem.entity.Brand;
import org.example.inventorymanagementsystem.service.interfaces.BrandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    private final BrandService service;

    public BrandController(BrandService service) {
        this.service = service;
    }

    @PostMapping
    public Brand create(@RequestBody Brand brand) {
        return service.createBrand(brand);
    }

    @GetMapping
    public List<Brand> getAll() {
        return service.getAllBrands();
    }

    @GetMapping("/{id}")
    public Brand getById(@PathVariable Long id) {
        return service.getBrandById(id);
    }

    @PutMapping("/{id}")
    public Brand update(@PathVariable Long id,
                        @RequestBody Brand brand) {
        return service.updateBrand(id, brand);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteBrand(id);
    }
}