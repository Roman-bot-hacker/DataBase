package DTO;

import controller.ProviderController;
import domain.Invoice;
import exceptions.NoSuchGoodException;
import exceptions.NoSuchProviderException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.ResourceSupport;

import java.sql.Date;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class InvoiceDTO {
    Invoice invoice;

    public InvoiceDTO(Invoice invoice, Link selfLink) throws NoSuchGoodException, NoSuchProviderException {
        ResourceSupport rSupport = new ResourceSupport();
        this.invoice = invoice;
        rSupport.add(selfLink);
        rSupport.add(linkTo(ControllerLinkBuilder.methodOn(ProviderController.class).getProvidersByGoodID(invoice.getId())).withRel("invoices"));
    }

    public Long getInvoiceId() {
        return invoice.getId();
    }

    public Date getInvoiceDate() {return invoice.getDate();}
}
