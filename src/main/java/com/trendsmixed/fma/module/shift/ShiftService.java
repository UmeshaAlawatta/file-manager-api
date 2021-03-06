package com.trendsmixed.fma.module.shift;

import com.trendsmixed.fma.dao.Combo;
import com.trendsmixed.fma.utility.Page;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ShiftService {

    private ShiftRepository repository;

    public Iterable<Shift> findAll() {
        return repository.findAll();
    }

    public Page<Shift> findAll(Pageable pageable) {
        return new Page<Shift>(repository.findAll(pageable));
    }

    public List<Combo> getCombo() {
        return repository.getCombo();
    }

    public Shift save(Shift shift) {
        return repository.save(shift);
    }

    public Shift findOne(int id) {
        return repository.findOne(id);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public Shift findByCode(String code) {
        return repository.findByCode(code);
    }
}
