package com.trendsmixed.fma.module.materialcostperkg;

import com.trendsmixed.fma.utility.Page;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MaterialCostPerKgService {

    private MaterialCostPerKgRepository repository;

    public Iterable<MaterialCostPerKg> findAll() {
        return repository.findAll();
    }

    public Page<MaterialCostPerKg> findAll(Pageable pageable) {
        return new Page<>(repository.findAll(pageable));
    }

    public MaterialCostPerKg save(MaterialCostPerKg materialCostPerKg) {
        return repository.save(materialCostPerKg);
    }

    public void save(List<MaterialCostPerKg> materialCostPerKgs) {
        repository.save(materialCostPerKgs);
    }

    public MaterialCostPerKg findOne(int id) {
        return repository.findOne(id);
    }

    public void delete(int id) {
        repository.delete(id);
    }

}
