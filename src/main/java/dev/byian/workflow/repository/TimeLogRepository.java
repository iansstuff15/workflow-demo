package dev.byian.workflow.repository;

import dev.byian.workflow.domain.Employee;
import dev.byian.workflow.domain.TimeLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TimeLogRepository extends CrudRepository<TimeLog, UUID>{

}
