package com.trendsmixed.fma.module.status;

import com.trendsmixed.fma.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StatusRepository extends JpaRepository<Status, Integer> {

    Status findByName(String name);

}