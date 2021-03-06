package com.trendsmixed.fma.module.designation;

import com.trendsmixed.fma.dao.Combo;
import com.trendsmixed.fma.utility.Page;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DesignationService {

    private DesignationRepository repository;

    public Iterable<Designation> findAll() {
        return repository.findAll();
    }

    public Page<Designation> findAll(Pageable pageable) {
        return new Page<>(repository.findAll(pageable));
    }

    public List<Combo> getCombo() {
        return repository.getCombo();
    }

    public Designation save(Designation designation) {
        return repository.save(designation);
    }

    public void save(List<Designation> designations) {
        repository.save(designations);
    }

    public Designation findOne(int id) {
        return repository.findOne(id);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public Designation findByName(String name) {
        return repository.findByName(name);
    }

    public Designation findByCode(String code) {
        return repository.findByCode(code);
    }
}
