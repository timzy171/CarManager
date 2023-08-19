package com.example.carmanager;


import com.example.carmanager.entity.Car;
import com.example.carmanager.repo.CarRepository;
import com.vaadin.annotations.Push;
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
@Push
@CssImport(value = "./styles/searchController.css")
public class SearchController extends VerticalLayout implements HasUrlParameter<String>{
    @Autowired
    public CarRepository carRepository;

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter  String s) {
        add(HelloPage.getMenu());
        List<Car> carList = carRepository.findByMarkAndModel(s);
        AtomicInteger i = new AtomicInteger();
        AtomicReference<HorizontalLayout> hl = new AtomicReference<>(new HorizontalLayout());
        hl.get().setSpacing(false);
        hl.get().getThemeList().add("spacing-xl");
        carList.forEach(
                car -> {
                    String model = car.model.toLowerCase().replaceAll("-", "_").replaceAll(" ", "_");
                    Image carImage = HTMLParser.getCarImage(model, car.mark);
                    carImage.setClassName("searchCarImage");
                    setClickListenerEvent(carImage,car.mark,car.model);
                    carImage.setWidth("250px");
                    if (i.get() < 5 && carList.size() > 5) {
                        Div div = new Div();
                        i.incrementAndGet();
                        addDivToLayout(hl, car, carImage, div);
                    } else if (carList.size() <= 5) {
                        Div div = new Div();
                        div.add(carImage);
                        H6 carInfo = new H6(car.mark + " " + car.model);
                        carInfo.addClassName("carLabel");
                        div.add(carInfo);
                        i.incrementAndGet();
                        hl.get().add(div);
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
                        addDivToLayout(hl, car, carImage, div);
                    }
                });
    }

    private void addDivToLayout(AtomicReference<HorizontalLayout> hl, Car car, Image carImage, Div div) {
        div.add(carImage);
        H6 carInfo = new H6(car.mark + " " + car.model);
        carInfo.addClassName("carLabel");
        setClickListenerEvent(carInfo,car.mark,car.model);
        div.add(carInfo);
        hl.get().add(div);
    }

    private void setClickListenerEvent(H6 carLabel,String mark,String model){
        carLabel.addClickListener(h6ClickEvent -> {
            UI.getCurrent().navigate("/search/carInfo/" +  mark.toLowerCase().trim() + '-' + model.toLowerCase().trim());
        });
    }

    private void setClickListenerEvent(Image carImage,String mark,String model){
        carImage.addClickListener(h6ClickEvent -> {
            UI.getCurrent().navigate("/search/carInfo/" +  mark.toLowerCase().trim() + '-' + model.toLowerCase().trim());
        });
    }
}
