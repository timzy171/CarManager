package com.example.carmanager;


import com.example.carmanager.repo.CarRepository;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Arrays;

@Route("/search/carInfo")
public class CarInfoView  extends VerticalLayout implements HasUrlParameter<String> {
    @Autowired
    CarRepository carRepository;
    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String car) {
        String[] carInfo = car.split("-");
        String mark = carInfo[0];
        String model = "";
        if(carInfo.length > 2){
            for (int i = 1; i < carInfo.length; i++) {
                model += carInfo[i] += "_";
            }
            model = model.substring(0,model.length() - 1);
        }
        else{
            model = carInfo[1];
        }
        String URL;
        try {
            if(model.contains(" ")){
                model = model.replaceAll(" ","_");
                URL = "http://www.motorpage.ru/" + firstUpperCase(mark) + "/" + model + "/last/";
            }
            else{
                URL = "http://www.motorpage.ru/" + firstUpperCase(mark) + "/" + firstUpperCase(model) + "/last/";

            }
            Document document = Jsoup.connect(URL).get();
            Element imgInfo = document.getElementsByAttributeValue("itemprop","image").first();
            String imageURL = imgInfo.absUrl("src");
            Image carImage = new Image(imageURL,car);
            add(carImage);
            String description = document.getElementsByAttributeValue("itemprop","description").text();
            add(new H1(description));
        } catch (IOException e) {
            System.out.println(car);
            throw new RuntimeException(e);
        }
    }

    public String firstUpperCase(String word){
        return word.substring(0,1).toUpperCase() + word.substring(1);
    }

    public String allUpperCase(String word){
        String[] split = word.split("-");
        return split[0].toUpperCase() + "_" + split[1].toUpperCase();
    }
}
