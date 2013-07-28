package com.hengyi.japp.foreign.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hengyi.japp.foreign.domain.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, String> {
}
