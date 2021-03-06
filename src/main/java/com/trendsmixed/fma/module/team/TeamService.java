package com.trendsmixed.fma.module.team;

import com.trendsmixed.fma.dao.Combo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TeamService {

    private TeamRepository repository;

    public Iterable<Team> findAll() {
        return repository.findAll();
    }

    public Page<Team> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Combo> getCombo() {
        return repository.getCombo();
    }

    public Team save(Team team) {
        return repository.save(team);
    }

    public Iterable<Team> save(List<Team> teams) {
        return repository.save(teams);
    }

    public Team findOne(int id) {
        return repository.findOne(id);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public Team findByName(String name) {
        return repository.findByName(name);
    }

    public long count() {
        return repository.count();
    }

}
