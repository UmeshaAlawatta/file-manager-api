package com.trendsmixed.fma.module.controlpoint;

import com.trendsmixed.fma.dao.Combo;
import com.trendsmixed.fma.utility.Page;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ControlPointService {

    private ControlPointRepository repository;

    public Iterable<ControlPoint> findAll() {
        return repository.findAll();
    }

    public Page<ControlPoint> findAll(Pageable pageable) {
        return new Page<ControlPoint>(repository.findAll(pageable));
    }

    public ControlPoint save(ControlPoint ControlPoint) {
        return repository.save(ControlPoint);
    }

    public void save(List<ControlPoint> controlPoints) {
        repository.save(controlPoints);
    }

    public ControlPoint findOne(int id) {
        return repository.findOne(id);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public ControlPoint findByCode(String code) {
        return repository.findByCode(code);
    }

    public List<Combo> getCombo() {
        return repository.getCombo();
    }
}
