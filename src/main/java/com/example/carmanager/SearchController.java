package com.example.carmanager;


import com.example.carmanager.entity.Car;
import com.example.carmanager.repo.CarRepository;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.router.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Route("/search")
public class SearchController extends VerticalLayout implements HasUrlParameter<String>{
    @Autowired
    public CarRepository carRepository;;

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter  String s) {
        List<Car> carList = carRepository.findByMark(s);
        List<Button> carButtons = new ArrayList<>();
        carList.forEach(
                car ->{
                    add(new H1(car.mark + " " + car.model));
                    Button button = new Button(car.model);
                    button.setId(car.model);
                    button.addClickListener(buttonClickEvent -> {
                        UI.getCurrent().navigate("/search/carInfo/" +
                                car.mark.toLowerCase().trim() + '-' + car.model.toLowerCase().trim());
                    });
                    add(button);
                    carButtons.add(button);
                }
        );
    }


    @Test
    public void isIt(){
    }
}
