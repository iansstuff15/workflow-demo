package dev.byian.workflow.dao;

import dev.byian.workflow.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SupplierDao extends JpaRepository<Supplier, UUID> {
    List<Supplier> findByName(String departmentName);

    List<Supplier> findByContactNumber(String contactNumber);

    List<Supplier> findByEmail(String email);

    List<Supplier> findByAddress(String address);
}
