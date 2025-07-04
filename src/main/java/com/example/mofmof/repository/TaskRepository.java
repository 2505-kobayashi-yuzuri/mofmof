package com.example.mofmof.repository;

import com.example.mofmof.repository.entity.Tasks;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Repository
public interface TaskRepository extends JpaRepository<Tasks, Integer> {
    List<Tasks> findAllByLimitDateBetweenAndStatusAndContentContainingOrderByLimitDateAsc(Date startDate, Date endDate, Short Status, String content);
    List<Tasks> findAllByLimitDateBetweenAndStatusOrderByLimitDateAsc(Date startDate, Date endDate, Short Status);
    List<Tasks> findAllByLimitDateBetweenAndContentContainingOrderByLimitDateAsc(Date startDate, Date endDate, String content);
    List<Tasks> findAllByLimitDateBetweenOrderByLimitDateAsc(Date startDate, Date endDate);
    @Modifying
    @Query(value = "UPDATE tasks SET status = :status, updated_date = CURRENT_TIMESTAMP WHERE id = :id", nativeQuery = true)
   void updateStatusById(@Param("id") Integer id, @Param("status") Short status);
}
