package com.trendsmixed.fma.module.subcontractarrival;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.trendsmixed.fma.dao.Combo;

public interface SubcontractArrivalRepository
                extends PagingAndSortingRepository<SubcontractArrival, Integer> {

        @Query(value = "SELECT" + " new com.trendsmixed.fma.dao.Combo(o.id,CONCAT(o.id,''),'')"
                        + " FROM SubcontractArrival o")
        List<Combo> getCombo();

}