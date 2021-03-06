package com.trendsmixed.fma.module.salesweight;

import com.trendsmixed.fma.utility.Page;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SalesWeightService {

    private SalesWeightRepository repository;

    public Iterable<SalesWeight> findAll() {
        return repository.findAll();
    }

    public Page<SalesWeight> findAll(Pageable pageable) {
        return new Page<>(repository.findAll(pageable));
    }

    public SalesWeight save(SalesWeight salesWeight) {
        return repository.save(salesWeight);
    }

    public void save(List<SalesWeight> salesWeights) {
        repository.save(salesWeights);
    }

    public SalesWeight findOne(int id) {
        return repository.findOne(id);
    }

    public void delete(int id) {
        repository.delete(id);
    }

}
