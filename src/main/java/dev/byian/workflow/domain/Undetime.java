package dev.byian.workflow.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="undertime")
public class Undetime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp updatedAt = new Timestamp(System.currentTimeMillis());
    @Column(nullable = false)
    private String reason;
    @Column( columnDefinition = "VARCHAR(255) DEFAULT 'PENDING'")
    private String status;
    private String attachment;
    @Column(nullable = false)
    private Date startDate;
    @Column(nullable = false)
    private Date endDate;
    @Column(nullable = false)
    private Time startTime;
    @Column(nullable = false)
    private Time endTime;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Employee appliedBy;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Employee reviwedBy;
}
