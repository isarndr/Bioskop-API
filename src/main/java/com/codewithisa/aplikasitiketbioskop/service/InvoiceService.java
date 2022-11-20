package com.codewithisa.aplikasitiketbioskop.service;

import org.springframework.stereotype.Service;

@Service
public interface InvoiceService {

    /**
     * untuk mengenerate file pdf invoice
     */
    void generateInvoice();
}
