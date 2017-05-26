package com.trendsmixed.fma.module.team;

import com.trendsmixed.fma.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TeamRepository extends JpaRepository<Team, Integer> {

    Team findByName(String name);

}