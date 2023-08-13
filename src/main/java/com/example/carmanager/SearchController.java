package com.example.carmanager;


import com.example.carmanager.entity.Car;
import com.example.carmanager.repo.CarRepository;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Route("/search")
@CssImport(value = "./styles/searchController.css")
public class SearchController extends VerticalLayout implements HasUrlParameter<String>{
    @Autowired
    public CarRepository carRepository;

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter  String s) {
        List<Car> carList = carRepository.findByMarkAndModel(s);
        AtomicInteger i = new AtomicInteger();
        AtomicReference<HorizontalLayout> hl = new AtomicReference<>(new HorizontalLayout());
        hl.get().setSpacing(false);
        hl.get().getThemeList().add("spacing-xl");
        carList.forEach(
                car -> {
                    String model = car.model.toLowerCase().replaceAll("-", "_").replaceAll(" ", "_");
                    Image carImage = HTMLParser.getCarImage(model, car.mark);
                    carImage.setWidth("250px");
                    if (i.get() < 5 && carList.size() > 5) {
                        Div div = new Div();
                        i.incrementAndGet();
                        div.add(carImage);
                        H6 carInfo = new H6(car.mark + " " + car.model);
                        div.add(carInfo);
                        hl.get().add(div);
                    } else if (carList.size() <= 5) {
                        hl.get().add(carImage);
                        H6 carInfo = new H6(car.mark + " " + car.model);
                        hl.get().add(carInfo);
                        i.incrementAndGet();
                        if (i.get() == carList.size()) {
                            hl.get().setClassName("carImagesLayout");
                            add(hl.get());
                        }
                    } else {
                        hl.get().setClassName("carImagesLayout");
                        add(hl.get());
                        hl.set(new HorizontalLayout());
                        hl.get().setSpacing(false);
                        hl.get().getThemeList().add("spacing-xl");
                        i.set(1);
                        Div div = new Div();
                        div.add(carImage);
                        H6 carInfo = new H6(car.mark + " " + car.model);
                        div.add(carInfo);
                        hl.get().add(div);
                    }
                });
    }
}
