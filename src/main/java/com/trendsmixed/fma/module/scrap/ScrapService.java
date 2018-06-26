package com.trendsmixed.fma.module.scrap;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.trendsmixed.fma.module.section.Section;

@AllArgsConstructor
@Service
public class ScrapService {

    private ScrapRepository repository;

    public Iterable<Scrap> findAll() {
        return repository.findAll();
    }

    public Page<Scrap> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Scrap save(Scrap scrap) {
        return repository.save(scrap);
    }

    public void save(List<Scrap> scraps) {
        repository.save(scraps);
    }

    public Scrap findOne(int id) {
        return repository.findOne(id);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public Page<Scrap>findByScrapDateBetween(Date startDate, Date endDate, Pageable pageable) {
        return repository.findByScrapDateBetween(startDate, endDate, pageable);
    }

    public Page<Scrap> findBySectionAndScrapDateBetween(Section section, Date startDate, Date endDate, Pageable pageable) {
        return repository.findBySectionAndScrapDateBetween(section, startDate, endDate, pageable);
    }
}
