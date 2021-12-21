package com.ezloc.app.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("${crossorigins.url}")
@RequestMapping(value = "/invoices")
public class InvoiceController {
}
