package com.example.navigation.repository;

import com.example.navigation.entity.Navigate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NavigateRepository extends JpaRepository<Navigate, Integer> {
//    @Query(value = "SELECT * FROM navigate WHERE state :status LIMIT :page, :size", nativeQuery = true)
//    List<Navigate> findAllByPage(@Param("page") int page, @Param("size") int size, @Param("state") Enum state);

}
