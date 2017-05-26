package com.trendsmixed.fma.module.user;

import com.trendsmixed.fma.dao.Combo;

import com.trendsmixed.fma.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    User findByEmailAndPasswordAndStatus(String email, String password, String status);
    
    @Query(value = "SELECT"
            + " new com.trendsmixed.fma.dao.Combo(o.id, o.email, o.email)"
            + " FROM User o")
    public List<Combo> getCombo();
}