package dev.byian.workflow.repository;

import dev.byian.workflow.domain.Department;
import dev.byian.workflow.domain.LeaveRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LeaveRequestRepository extends CrudRepository<LeaveRequest, UUID>{

}
