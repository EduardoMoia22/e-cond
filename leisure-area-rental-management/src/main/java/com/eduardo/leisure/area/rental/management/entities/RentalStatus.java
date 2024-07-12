package com.eduardo.leisure.area.rental.management.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "tb_rental_status")
public class RentalStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private String description;

    public RentalStatus() {
    }

    public RentalStatus(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public enum Values {
        RENTED(1L, "Alugado"),
        RETURNED(2L, "Devolvido");

        private final Long id;

        private final String description;

        Values(Long id, String description) {
            this.id = id;
            this.description = description;
        }

        public RentalStatus toRentalStatus() {
            return new RentalStatus(this.id, this.description);
        }
    }
}
