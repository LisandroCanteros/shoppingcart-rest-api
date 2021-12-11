package com.informatorio.shoppingcart.repository;

import com.informatorio.shoppingcart.entity.InvoiceLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceLineRepository extends JpaRepository<InvoiceLine, Long> {
}
