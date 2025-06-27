package com.example.mofmof.repository;

import com.example.mofmof.repository.entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface TaskRepository extends JpaRepository<Tasks, Integer> {
}
