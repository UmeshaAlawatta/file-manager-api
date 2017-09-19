package com.trendsmixed.fma.module.supplier;

import com.trendsmixed.fma.dao.Combo;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SupplierRepository extends PagingAndSortingRepository<Supplier, Integer> {

    public Supplier findByCode(String code);


    @Query(value = "SELECT"
            + " new com.trendsmixed.fma.dao.Combo(o.id, o.code,o.name)"
            + " FROM Supplier o")
    public List<Combo> getCombo();
}
