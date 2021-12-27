package com.ezloc.app.servicesImpl;

import com.ezloc.app.entities.Invoice;
import com.ezloc.app.services.InvoiceService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Override
    public List<Invoice> findAll() {
        return null;
    }

    @Override
    @Transactional
    public Optional<Invoice> add(Optional<Invoice> invoice) {
        return Optional.empty();
    }

    @Override
    public Optional<Invoice> findById(long id) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public String update(Long id, Optional<Invoice> invoice) {
        return null;
    }

    @Override
    @Transactional
    public String delete(long id) {
        return null;
    }
}
