package dev.byian.workflow.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="payroll")
public class Payroll {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updatedAt = new Timestamp(System.currentTimeMillis());
    @Column(nullable = false)
    private float grossPay;
    @Column(nullable = false)
    private float netPay;
    @Column(nullable = false)
    private float deductions;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Employee employee;

    public void update(Payroll payroll) {
    if(payroll.getGrossPay() != 0) {
        this.setGrossPay(payroll.getGrossPay());
        this.netPay = this.grossPay - this.deductions;
    }
    if(payroll.getDeductions() != 0) {
        this.setDeductions(payroll.getDeductions());
        this.netPay = this.grossPay - this.deductions;
    }
    this.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
    }
}
