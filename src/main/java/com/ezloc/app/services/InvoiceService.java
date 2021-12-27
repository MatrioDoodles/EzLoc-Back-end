package com.ezloc.app.services;

import com.ezloc.app.entities.Invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {
    List<Invoice> findAll();
    Optional<Invoice> add(Optional<Invoice> invoice);
    Optional<Invoice>  findById(long id);
    String update(Long id,Optional<Invoice> invoice);
    String delete(long id);
}
