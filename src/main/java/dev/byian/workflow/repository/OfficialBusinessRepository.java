package dev.byian.workflow.repository;

import dev.byian.workflow.domain.Department;
import dev.byian.workflow.domain.OfficialBusiness;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OfficialBusinessRepository extends CrudRepository<OfficialBusiness, UUID>{

}
