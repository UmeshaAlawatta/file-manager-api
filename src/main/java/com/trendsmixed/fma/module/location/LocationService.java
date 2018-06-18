package com.trendsmixed.fma.module.location;

import com.trendsmixed.fma.dao.Combo;
import com.trendsmixed.fma.utility.Page;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class LocationService {

    private LocationRepository repository;

    public Iterable<Location> findAll() {
        return repository.findAll();
    }

    public Page<Location> findAll(Pageable pageable) {
        return new Page<>(repository.findAll(pageable));
    }

    public List<Combo> getCombo() {
        return repository.getCombo();
    }

    public Location save(Location location) {
        return repository.save(location);
    }

    public void save(List<Location> locations) {
        repository.save(locations);
    }

    public Location findOne(int id) {
        return repository.findOne(id);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public Location findByCode(String code) {
        return repository.findByCode(code);
    }
}
