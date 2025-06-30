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
    List<Tasks> findAllByLimitDateBetweenAndStatusAndContentOrderByLimitDateAsc(Date startDate, Date endDate, short Status, String content);
    List<Tasks> findAllByLimitDateBetweenAndStatusOrderByLimitDateAsc(Date startDate, Date endDate, short Status);
    List<Tasks> findAllByLimitDateBetweenAndContentOrderByLimitDateAsc(Date startDate, Date endDate, String content);
    List<Tasks> findAllByLimitDateBetweenOrderByLimitDateAsc(Date startDate, Date endDate);
}
