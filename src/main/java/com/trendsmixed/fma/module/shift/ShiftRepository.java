package com.trendsmixed.fma.module.shift;

import com.trendsmixed.fma.dao.Combo;
import com.trendsmixed.fma.entity.Shift;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ShiftRepository extends PagingAndSortingRepository<Shift, Integer> {

    public Shift findByCode(String code);
    
    @Query(value = "SELECT"
            + " new com.trendsmixed.fma.dao.Combo(o.id, o.code, o.name)"
            + " FROM Shift o")
    public List<Combo> getCombo();
}