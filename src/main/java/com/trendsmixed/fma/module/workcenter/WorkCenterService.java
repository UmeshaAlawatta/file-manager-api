package com.trendsmixed.fma.module.workcenter;

import com.trendsmixed.fma.dao.Combo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class WorkCenterService {

    private WorkCenterRepository repository;

    public Iterable<WorkCenter> findAll() {
        return repository.findAll();
    }

    public Page<WorkCenter> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public WorkCenter save(WorkCenter workCenter) {
        return repository.save(workCenter);
    }

    public void save(List<WorkCenter> workCenters) {
        repository.save(workCenters);
    }

    public WorkCenter findOne(int id) {
        return repository.findOne(id);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public WorkCenter findByCode(String code) {
        return repository.findByCode(code);
    }

    public List<Combo> getCombo() {
        return repository.getCombo();
    }
}
