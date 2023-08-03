package com.example.carmanager;


import com.example.carmanager.repo.CarRepository;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.IFrame;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.io.IOException;

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
            String videoURL = null;
            IFrame iFrame = new IFrame();
            try {
                videoURL = document.select("iframe").first().absUrl("src");
                iFrame.setSrc(videoURL);
            }
            catch (Exception e){
            }
            iFrame.setAllow("accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture");
            iFrame.getElement().setAttribute("allowfullscreen", true);
            iFrame.getElement().setAttribute("frameborder", "0");
            iFrame.setHeight("315px");
            iFrame.setWidth("100%");
            add(new H1(firstUpperCase(mark) + " " + firstUpperCase(model).replaceAll("_"," ")));
            add(new H3(description));
            if(videoURL != null){
                add(iFrame);
            }
        } catch (IOException e) {
            System.out.println(car);
            throw new RuntimeException(e);
        }
    }

    public String firstUpperCase(String word){
        return word.substring(0,1).toUpperCase() + word.substring(1);
    }

}
