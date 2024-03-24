package com.test.service.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")
@Entity
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "UUID")
    @Schema(name = "id", example = "ef5625c1-dc1a-4db8-9acf-e082b5501f5f",
        description = "Account id")
    private UUID id;

    @Column(name = "document_number")
    @Schema(name = "document_number", example = "ABC-102011", description = "Document number")
    private String documentNumber;

    @Column(name = "balance")
    @Schema(name = "balance", example = "100.25", description = "Account balance")
    private BigDecimal balance;

    @CreationTimestamp
    @Column(name = "created")
    @Schema(name = "created", example = "", description = "Account created time")
    private Timestamp created;

    @UpdateTimestamp
    @Column(name = "updated")
    @Schema(name = "updated", example = "", description = "Account updated time")
    private Timestamp updated;
}
