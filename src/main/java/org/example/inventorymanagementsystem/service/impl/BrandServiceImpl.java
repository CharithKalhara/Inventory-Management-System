package org.example.inventorymanagementsystem.service.implementations;

import org.example.inventorymanagementsystem.entity.Brand;
import org.example.inventorymanagementsystem.repository.BrandRepository;
import org.example.inventorymanagementsystem.service.interfaces.BrandService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository repository;

    public BrandServiceImpl(BrandRepository repository) {
        this.repository = repository;
    }

    @Override
    public Brand createBrand(Brand brand) {

        if (repository.existsByName(brand.getName())) {
            throw new RuntimeException("Brand already exists.");
        }

        return repository.save(brand);
    }

    @Override
    public List<Brand> getAllBrands() {
        return repository.findAll();
    }

    @Override
    public Brand getBrandById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found."));
    }

    @Override
    public Brand updateBrand(Long id, Brand brand) {

        Brand existing = getBrandById(id);

        existing.setName(brand.getName());
        existing.setDescription(brand.getDescription());
        existing.setStatus(brand.isStatus());

        return repository.save(existing);
    }

    @Override
    public void deleteBrand(Long id) {

        Brand brand = getBrandById(id);

        repository.delete(brand);
    }
}