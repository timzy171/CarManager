package com.example.carmanager;

import com.example.carmanager.entity.Car;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.example.carmanager.repo.CarRepository;

import java.util.List;

@Route("/search")
@CssImport(value = "./styles/searchView.css")
public class SearchView extends VerticalLayout {
    public CarRepository carRepository;
    public SearchView(CarRepository carRepository){
        this.carRepository = carRepository;
        TextField searchText = new TextField();
        searchText.setPlaceholder("Input car name");
        Button searchButton = new Button();
        searchButton.setText("FIND");
        Image image = new Image("images/carManager.jpg","sorry");
        image.setWidth("100x");
        image.setHeight("200px");
        add(searchText);
        add(searchButton);
        add(image);
        setClassNames(searchText,searchButton,image);
        searchButton.addClickListener(event -> {
            List<Car> carList = carRepository.findAll();
            System.out.println(carList);
        });
    }

    private void setClassNames(TextField searchText,Button searchButton,Image image){
        searchText.setClassName("sTextField");
        searchButton.setClassName("sButton");
        image.setClassName("carManagerIcon");
    }
}
