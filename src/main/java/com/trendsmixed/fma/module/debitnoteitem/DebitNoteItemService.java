package com.trendsmixed.fma.module.debitnoteitem;

import com.trendsmixed.fma.dao.Combo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class DebitNoteItemService {

    private DebitNoteItemRepository repository;

    public Iterable<DebitNoteItem> findAll() {
        return repository.findAll();
    }

    public Page<DebitNoteItem> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Combo> getCombo() {
        return repository.getCombo();
    }

    public DebitNoteItem save(DebitNoteItem debitNoteItem) {
        return repository.save(debitNoteItem);
    }

    public void save(List<DebitNoteItem> debitNoteItemList) {
        repository.save(debitNoteItemList);
    }

    public DebitNoteItem findOne(int id) {
        return repository.findOne(id);
    }

    public void delete(int id) {
        repository.delete(id);
    }

}
