package com.trendsmixed.fma.module.paint;

import com.trendsmixed.fma.dao.Combo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PaintService {

    private PaintRepository repository;

    public Iterable<Paint> findAll() {
        return repository.findAll();
    }

    public Page<Paint> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Combo> getCombo() {
        return repository.getCombo();
    }

    public Paint save(Paint paint) {
        return repository.save(paint);
    }

    public void save(List<Paint> paints) {
        repository.save(paints);
    }

    public Paint findOne(int id) {
        return repository.findOne(id);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public Paint findByCode(String code) {
        return repository.findByCode(code);
    }

    public Paint findByName(String name) {
        return repository.findByName(name);
    }
}
