package com.example.carmanager;

import com.vaadin.flow.component.html.Image;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class HTMLParser {
    public static Image getCarImage(String model, String mark) {
        String URL;
        try {
            if (model.contains(" ")) {
                URL = "http://www.motorpage.ru/" + CarInfoView.firstUpperCase(mark) + "/" + model + "/last/";
                Integer a = 2;
            } else {
                URL = "http://www.motorpage.ru/" + CarInfoView.firstUpperCase(mark) + "/" + CarInfoView.firstUpperCase(model) + "/last/";

            }
            Document document = Jsoup.connect(URL).get();
            Element imgInfo = document.getElementsByAttributeValue("itemprop", "image").first();
            assert imgInfo != null;
            String imageURL = imgInfo.absUrl("src");
            return new Image(imageURL, mark + " " + model);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
