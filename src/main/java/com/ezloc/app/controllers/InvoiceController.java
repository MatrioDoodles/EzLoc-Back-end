package com.ezloc.app.controllers;

import com.ezloc.app.config.Constants;
import com.ezloc.app.entities.Invoice;
import com.ezloc.app.services.InvoiceService;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/invoices")
public class InvoiceController {
    private InvoiceService InvoiceService;

    @Autowired
    public InvoiceController(InvoiceService InvoiceService) {
        this.InvoiceService = InvoiceService;
    }

    @GetMapping
    public CollectionModel<Invoice> findAll() {
        List<Invoice> allInvoices = InvoiceService.findAll();
        for (Invoice Invoice : allInvoices) {
            Long InvoiceId = Invoice.getId();
            Link selfLink = linkTo(InvoiceController.class).slash(InvoiceId).withSelfRel();
            Invoice.add(selfLink);
        }


        Link link = linkTo(InvoiceController.class).withSelfRel();
        CollectionModel<Invoice> result = CollectionModel.of(allInvoices, link);
        return result;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {

        Optional<Invoice> Invoice = InvoiceService.findById(id);

        if(Invoice.isPresent()) {
            Invoice resource = Invoice.get();
            Link selfLink = linkTo(InvoiceController.class).slash(id).withSelfRel();
            EntityModel<Invoice> result = EntityModel.of(resource,selfLink);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.INVOICE_NOT_FOUND);
        }
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<Invoice> create(@RequestBody Optional<Invoice> resource) {
        Preconditions.checkNotNull(resource,"Populate all infos");
        return InvoiceService.add(resource);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Optional<Invoice> resource) {

        Optional<Invoice> Invoice = InvoiceService.findById(id);
        if(Invoice.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(InvoiceService.update(id,resource));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.INVOICE_NOT_FOUND);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        Optional<Invoice> Invoice = InvoiceService.findById(id);
        if(Invoice.isPresent()) {

            return ResponseEntity.status(HttpStatus.OK).body(InvoiceService.delete(id));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Constants.INVOICE_NOT_FOUND);
    }
}
