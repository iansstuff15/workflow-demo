package dev.byian.workflow.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updatedAt = new Timestamp(System.currentTimeMillis());
    @Column(nullable = false)
    private String serialNumber;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private Date acquiredDate;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Employee assignedTo;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Employee assignedBy;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Supplier supplier;
    @OneToOne
    @JoinColumn(nullable = false)
    private Department department;
}
