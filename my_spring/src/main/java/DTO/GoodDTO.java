package DTO;

import controller.ProviderController;
import domain.Good;
import exceptions.NoSuchGoodException;
import exceptions.NoSuchProviderException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class GoodDTO extends ResourceSupport {
    Good good;

    public GoodDTO(Good good, Link selfLink) throws NoSuchGoodException, NoSuchProviderException {
        this.good = good;
        add(selfLink);
        add(linkTo(ControllerLinkBuilder.methodOn(ProviderController.class).getProvidersByGoodID(good.getId())).withRel("provider"));
    }

    public Long getGoodId() {return good.getId();}

    public String getGoodBrand() {return good.getBrand();}

    public Double getGoodPrice() {return good.getPrice();}

    public int getGoodCount() {return good.getCount();}
}
