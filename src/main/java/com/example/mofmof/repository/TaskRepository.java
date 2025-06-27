package com.example.mofmof.repository;

import com.example.mofmof.repository.entity.Tasks;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Repository
public interface TaskRepository extends JpaRepository<Tasks, Integer> {
    List<Tasks> findAllByLimitDateBetweenAndStatusAndTextOrderByLimitDateAcs(Date startDate, Date endDate, Short Status, String text);
    List<Tasks> findAllByLimitDateBetweenAndStatusOrderByLimitDateAcs(Date startDate, Date endDate, Short Status);
    List<Tasks> findAllByLimitDateBetweenAndTextOrderByLimitDateAcs(Date startDate, Date endDate, String text);
    List<Tasks> findAllByLimitDateBetweenOrderByLimitDateAcs(Date startDate, Date endDate);
}
