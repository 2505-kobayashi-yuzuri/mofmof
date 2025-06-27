package com.example.mofmof.repository;

import com.example.mofmof.repository.entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Integer> {
    List<Tasks> findAllByLimitDateBetweenOrderByLimitDateAcs(Date startDate, Date endDate);
}