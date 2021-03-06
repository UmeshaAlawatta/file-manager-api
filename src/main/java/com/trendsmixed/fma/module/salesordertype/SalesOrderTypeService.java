package com.trendsmixed.fma.module.salesordertype;

import com.trendsmixed.fma.dao.Combo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SalesOrderTypeService {

    private SalesOrderTypeRepository repository;

    public Iterable<SalesOrderType> findAll() {
        return repository.findAll();
    }

    public Page<SalesOrderType> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Combo> getCombo() {
        return repository.getCombo();
    }

    public SalesOrderType save(SalesOrderType salesOrderType) {
        return repository.save(salesOrderType);
    }

    public void save(List<SalesOrderType> salesOrderTypes) {
        repository.save(salesOrderTypes);
    }

    public SalesOrderType findOne(int id) {
        return repository.findOne(id);
    }

    public void delete(int id) {
        repository.delete(id);
    }
}
