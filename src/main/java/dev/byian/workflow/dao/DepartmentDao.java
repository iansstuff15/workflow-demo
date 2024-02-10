package dev.byian.workflow.dao;

import dev.byian.workflow.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DepartmentDao extends JpaRepository<Department, UUID> {
    List<Department> findByName(String name);

    Optional<Department> findFirstByOrderByIdAsc();
}
