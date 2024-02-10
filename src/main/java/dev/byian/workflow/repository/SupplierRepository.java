package dev.byian.workflow.repository;

import dev.byian.workflow.domain.Supplier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, UUID>{

}
