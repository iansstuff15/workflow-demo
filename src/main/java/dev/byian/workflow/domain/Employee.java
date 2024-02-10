package dev.byian.workflow.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false, columnDefinition = "UUID DEFAULT uuid_generate_v4()")
    private UUID id;
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updatedAt = new Timestamp(System.currentTimeMillis());
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String position;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(precision = 2, columnDefinition = "FLOAT DEFAULT 15.0")
    private float sickLeaveCredits = 15;
    @Column(precision = 2, columnDefinition = "FLOAT DEFAULT 15.0")
    private float vacationLeaveCredits = 15;
    @Column(precision = 2, columnDefinition = "FLOAT DEFAULT 15.0")
    private float emergencyLeaveCredits = 15;
    @Column(precision = 2, columnDefinition = "FLOAT DEFAULT 0.0")
    private float paternityLeaveCredits = 0;
    @Column(precision = 2, columnDefinition = "FLOAT DEFAULT 0.0")
    private float maternityLeaveCredits = 0;
    @Column(precision = 2, columnDefinition = "FLOAT DEFAULT 0.0")
    private double dailyRate = 0.0;
    @Column(nullable = false)
    //bi monthly, monthly, annually
    private String paySchedule;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Department department;
    @ManyToOne
    @JoinColumn()
    private Employee supervisor;
    @OneToMany(mappedBy = "supervisor")
    private List<Employee> subordinates;
}
