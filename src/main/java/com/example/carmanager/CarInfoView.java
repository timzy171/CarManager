package com.example.carmanager;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;

@Route("/search/carInfo")
public class CarInfoView  extends VerticalLayout implements HasUrlParameter<String> {
    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String car) {
        add(new H1(car));
    }
}
