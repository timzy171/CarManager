package com.example.carmanager;


import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

@Route("/search/carInfo")
public class CarInfoView  extends VerticalLayout implements HasUrlParameter<String> {
    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String car) {
        try {
            Document document = Jsoup.connect("https://ru.wikipedia.org/wiki/" + car.replace("-","_")).get();
            String info = document.select("div.mw-parser-output > p:not(href)").text();
            add(new H1(info));
        } catch (IOException e) {
            System.out.println(car);
            throw new RuntimeException(e);
        }
    }
}
