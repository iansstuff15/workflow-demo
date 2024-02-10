package dev.byian.workflow.dao;

import dev.byian.workflow.domain.CertificateOfAttendance;
import dev.byian.workflow.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CertificateOfAttendanceDao extends JpaRepository<CertificateOfAttendance, UUID> {
}
