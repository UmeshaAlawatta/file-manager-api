package com.trendsmixed.fma.module.debitnote;

import com.trendsmixed.fma.dao.Combo;
import com.trendsmixed.fma.utility.Page;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DebitNoteService {

    private DebitNoteRepository repository;

    public Iterable<DebitNote> findAll() {
        return repository.findAll();
    }

    public Page<DebitNote> findAll(Pageable pageable) {
        return new Page<>(repository.findAll(pageable));
    }

    public DebitNote save(DebitNote debitNote) {
        return repository.save(debitNote);
    }

    public DebitNote findOne(int id) {
        return repository.findOne(id);
    }

    public void delete(int id) {
        repository.delete(id);
    }
    public List<Combo> getCombo() {
        return repository.getCombo();
    }
}
