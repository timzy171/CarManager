package com.example.carmanager;


import com.example.carmanager.repo.CarRepository;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;

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

@Route("/search/carInfo")
@CssImport(value = "./styles/carInfo.css")
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
            addInfoToPage(URL);
        } catch (IOException e) {
            System.out.println(car);
            throw new RuntimeException(e);
        }
    }

    public static String firstUpperCase(String word){
        return word.substring(0,1).toUpperCase() + word.substring(1);
    }

    public void addInfoToPage(String URL) throws IOException {
        Document document = Jsoup.connect(URL).get();
        addImageToPage(document);
        H1 carName = new H1(document.select("h1").first().text());
        carName.setClassName("title");
        add(carName);

        Elements elements = document.getElementsByAttributeValue("itemprop","description");
        var info = elements.get(0).getAllElements();

        for (int i = 0; i < info.size(); i++) {
            if(info.get(i).is("h2")){
                H2 text = new H2(firstUpperCase(info.get(i).text()));
                text.addClassName("title");
                add(text);
            }
            if(info.get(i).is("p")){
                add(new H4(info.get(i).text()));
            }
        }

        addVideoToPage(document);

    }

    public void addImageToPage(Document document) throws IOException {
        Element imgInfo = document.getElementsByAttributeValue("itemprop","image").first();
        String imageURL = imgInfo.absUrl("src");
        Image carImage = new Image(imageURL,"Error");
        add(carImage);
    }

    public void addVideoToPage(Document document){
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
        if(videoURL != null){
            add(iFrame);
        }
    }
}
