package com.trendsmixed.fma.module.rawmaterial;

import com.trendsmixed.fma.dao.Combo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RawMaterialRepository extends PagingAndSortingRepository<RawMaterial, Integer> {

    RawMaterial findByName(String name);

    @Query(value = "SELECT"
            + " new com.trendsmixed.fma.dao.Combo(o.id, o.code, o.name)"
            + " FROM RawMaterial o")
    List<Combo> getCombo();
}
