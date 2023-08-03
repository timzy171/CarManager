package com.example.carmanager;


import com.example.carmanager.entity.Car;
import com.example.carmanager.repo.CarRepository;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Route("/")
@CssImport(value = "./styles/searchView.css")
public class SearchView extends VerticalLayout {
    CarRepository carRepository;
    public SearchView(CarRepository carRepository) throws IOException {
        TextField searchText = new TextField();
        searchText.setPlaceholder("Input car name");
        Button searchButton = new Button();
        searchButton.setText("FIND");
        add(searchText);
        add(searchButton);
        setClassNames(searchText,searchButton);
        this.carRepository = carRepository;
        List<Car> carList = carRepository.findAll();
        Set<String> carMarks = new HashSet<>();
        for (Car car : carList){
            carMarks.add(car.mark);
        }
        for (String mark : carMarks){
            String URL = "https://ru.wikipedia.org/wiki/" + mark;
            try {
                Document document = Jsoup.connect(URL).get();
                Element imgInfo = document.select("img").first();
                String imageURL = imgInfo.absUrl("src");
                Image carImage = new Image(imageURL,mark);
                add(carImage);
            }
            catch (Exception e){
                System.out.println("ERROR - " + URL);
            }

        }
        searchButton.addClickListener(event -> {
            String inputCar = searchText.getValue().toLowerCase().trim();
            UI.getCurrent().navigate("/search/" + inputCar);
        });
    }

    private void setClassNames(TextField searchText,Button searchButton){
        searchText.setClassName("sTextField");
        searchButton.setClassName("sButton");
    }
}
