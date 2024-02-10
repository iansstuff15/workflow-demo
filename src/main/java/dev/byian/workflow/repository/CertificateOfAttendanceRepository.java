package dev.byian.workflow.repository;

import dev.byian.workflow.domain.CertificateOfAttendance;
import dev.byian.workflow.domain.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CertificateOfAttendanceRepository extends CrudRepository<CertificateOfAttendance, UUID>{

}
