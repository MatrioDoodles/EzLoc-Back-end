package com.ezloc.app.servicesImpl;

import com.ezloc.app.entities.Invoice;
import com.ezloc.app.repositories.InvoiceRepository;
import com.ezloc.app.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {


    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Invoice> add(Optional<Invoice> invoice) {
        return Optional.of(invoiceRepository.save(invoice.get()));
    }

    @Override
    public Optional<Invoice> findById(long id) {
        return invoiceRepository.findById(id);
    }

    @Override
    @Transactional
    public Invoice update(Long id, Optional<Invoice> invoice) {
        Invoice resource = invoiceRepository.getById(id);
        resource.setCode(Optional.ofNullable(invoice).map(c->c.get().getCode()).orElse(resource.getCode()));
        resource.setCheckoutType(Optional.ofNullable(invoice).map(c->c.get().getCheckoutType()).orElse(resource.getCheckoutType()));
        resource.setPaid(Optional.ofNullable(invoice).map(c->c.get().isPaid()).orElse(resource.isPaid()));
        resource.setInvoiceFile(Optional.ofNullable(invoice).map(c->c.get().getInvoiceFile()).orElse(resource.getInvoiceFile()));
        resource.setInvoiceFileName(Optional.ofNullable(invoice).map(c->c.get().getInvoiceFileName()).orElse(resource.getInvoiceFileName()));
        return  invoiceRepository.save(resource);

    }

    @Override
    @Transactional
    public String delete(long id) {
        invoiceRepository.deleteById(id);
        return "Invoice N° "+id+" Deleted Successfully";
    }
}
