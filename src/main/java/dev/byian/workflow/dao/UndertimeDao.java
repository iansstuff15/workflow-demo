package dev.byian.workflow.dao;

import dev.byian.workflow.domain.Department;
import dev.byian.workflow.domain.Undetime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UndertimeDao extends JpaRepository<Undetime, UUID> {
}
