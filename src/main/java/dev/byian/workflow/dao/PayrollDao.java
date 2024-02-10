package dev.byian.workflow.dao;

import dev.byian.workflow.domain.Department;
import dev.byian.workflow.domain.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PayrollDao extends JpaRepository<Payroll, UUID> {
}
