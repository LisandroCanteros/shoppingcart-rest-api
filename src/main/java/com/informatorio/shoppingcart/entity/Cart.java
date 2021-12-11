package com.informatorio.shoppingcart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private LocalDateTime creationDate;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    private String createdFrom;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceLine> invoiceLines = new ArrayList<>();
    @NotNull
    private Boolean open;

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", user=" + user +
                '}';
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCreatedFrom() {
        return createdFrom;
    }

    public void setCreatedFrom(String createdFrom) {
        this.createdFrom = createdFrom;
    }

    public List<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }

    public void addInvoiceLine(InvoiceLine line){
        invoiceLines.add(line);
        line.setCart(this);
    }

    public void removeInvoiceLine(InvoiceLine line){
        invoiceLines.remove(line);
        line.setCart(null);
    }
}
