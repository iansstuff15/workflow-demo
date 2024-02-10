package dev.byian.workflow.repository;

import dev.byian.workflow.domain.Department;
import dev.byian.workflow.domain.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, UUID>{

}
