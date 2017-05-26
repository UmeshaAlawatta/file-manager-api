package com.trendsmixed.fma.module.incoterm;

import com.trendsmixed.fma.entity.Incoterm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncotermRepository extends JpaRepository<Incoterm, Integer> {

    public Incoterm findByCode(String code);

    public Incoterm findByName(String name);

}