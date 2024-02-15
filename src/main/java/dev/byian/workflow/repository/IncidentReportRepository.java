package dev.byian.workflow.repository;

import dev.byian.workflow.domain.CertificateOfAttendance;
import dev.byian.workflow.domain.IncidentReport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IncidentReportRepository extends CrudRepository<IncidentReport, UUID>{

}
