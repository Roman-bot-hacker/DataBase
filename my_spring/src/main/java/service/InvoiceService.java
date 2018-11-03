package service;

import domain.Invoice;
import domain.Provider;
import exceptions.ExistsProviderForInvoiceException;
import exceptions.NoSuchInvoiceException;
import exceptions.NoSuchProviderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.InvoiceRepository;
import repository.ProviderRepository;

import java.util.List;
import java.util.Set;

@Service
public class InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    ProviderRepository providerRepository;

    public List<Invoice> getAllInvoice() {
        return invoiceRepository.findAll();
    }

    public Invoice getInvoice(Long invoice_id) throws NoSuchInvoiceException {
        Invoice invoice = invoiceRepository.findById(invoice_id).get();
        if (invoice == null) throw new NoSuchInvoiceException();
        return invoice;
    }

    @Transactional
    public void createInvoice(Invoice invoice, Long provider_id) throws NoSuchProviderException {
        if (provider_id > 0) {
            Provider provider = providerRepository.findById(provider_id).get();
            if (provider == null) throw new NoSuchProviderException();
            invoice.setProviderByProvider(provider);
        }
        invoiceRepository.save(invoice);
    }

    @Transactional
    public void updateInvoice(Invoice uInvoice, Long invoice_id, Long provider_id) throws NoSuchInvoiceException, NoSuchProviderException {
        Provider provider = providerRepository.findById(provider_id).get();
        if (provider_id > 0) {
            if (provider == null) throw new NoSuchInvoiceException();
        }
        Invoice invoice = invoiceRepository.findById(invoice_id).get();
        if (invoice == null) throw new NoSuchProviderException();
        //update
        invoice.setDate(uInvoice.getDate());
        if (provider_id > 0) invoice.setProviderByProvider(provider);
        else invoice.setProviderByProvider(null);
        invoiceRepository.save(invoice);
    }

    @Transactional
    public void deleteInvoice(Long invoice_id) throws NoSuchInvoiceException, ExistsProviderForInvoiceException {
        Invoice invoice = invoiceRepository.findById(invoice_id).get();
        if (invoice == null) throw new NoSuchInvoiceException();
        if (invoice.getProviderByProvider() != null) throw new ExistsProviderForInvoiceException();
        invoiceRepository.delete(invoice);
    }

    public Set<Invoice> getInvoicesByProviderID(Long provider_id) throws NoSuchInvoiceException {
        Provider provider = providerRepository.findById(provider_id).get();//2.0.0.M7
        if (provider == null) throw new NoSuchInvoiceException();
        return provider.getInvoices();
    }
}
