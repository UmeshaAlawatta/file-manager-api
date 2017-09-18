package com.trendsmixed.fma.module.containersize;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.trendsmixed.fma.dao.Combo;
import org.springframework.data.domain.Page;

@Service
public class ContainerSizeService {

    @Autowired
    private ContainerSizeRepository repository;

    public Iterable<ContainerSize> findAll() {
        return repository.findAll();
    }

    public Page<ContainerSize> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Combo> getCombo() {
        return repository.getCombo();
    }

    public ContainerSize save(ContainerSize containerSize) {
        return repository.save(containerSize);
    }

    public void save(List<ContainerSize> items) {
        repository.save(items);
    }

    public ContainerSize findOne(int id) {
        return repository.findOne(id);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public ContainerSize findByName(String name) {
        return repository.findByName(name);
    }
}