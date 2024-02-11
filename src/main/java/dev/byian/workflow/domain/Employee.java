package dev.byian.workflow.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id",nullable = false)
    @JsonBackReference
    private Department department;

    public void update(Employee employee){
    if(employee.getFirstName() != null) this.setFirstName(employee.getFirstName());
    if(employee.getLastName() != null) this.setLastName(employee.getLastName());
    if(employee.getPhone() != null) this.setPhone(employee.getPhone());
    if(employee.getPosition() != null) this.setPosition(employee.getPosition());
    if(employee.getEmail() != null) this.setEmail(employee.getEmail());
    if(employee.getDailyRate() != 0.0) this.setDailyRate(employee.getDailyRate());
    if(employee.getPaySchedule() != null) this.setPaySchedule(employee.getPaySchedule());
    if(employee.getDepartment() != null) this.setDepartment(employee.getDepartment());
    if(employee.getSickLeaveCredits() != 0.0) this.setSickLeaveCredits(employee.getSickLeaveCredits());
    if(employee.getVacationLeaveCredits() != 0.0) this.setVacationLeaveCredits(employee.getVacationLeaveCredits());
    if(employee.getEmergencyLeaveCredits() != 0.0) this.setEmergencyLeaveCredits(employee.getEmergencyLeaveCredits());
    if(employee.getMaternityLeaveCredits() != 0.0) this.setMaternityLeaveCredits(employee.getMaternityLeaveCredits());
    if(employee.getPaternityLeaveCredits() != 0.0) this.setPaternityLeaveCredits(employee.getPaternityLeaveCredits());
    this.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
    }
}
