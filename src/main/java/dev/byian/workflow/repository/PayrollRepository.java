package dev.byian.workflow.repository;

import dev.byian.workflow.domain.OfficialBusiness;
import dev.byian.workflow.domain.Payroll;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PayrollRepository extends CrudRepository<Payroll, UUID>{

}
