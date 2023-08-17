package com.example.carmanager;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

@Route("/mark")
@CssImport(value = "./styles/markView.css")
public class MarkView extends VerticalLayout implements HasUrlParameter<String> {

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String mark) {
        add(HelloPage.getMenu());
        String URL = "http://www.motorpage.ru/" + mark;
        try {
            Document document = Jsoup.connect(URL).get();
            Elements dts = document.select("dl.col-md-8").select("dt");
            Elements dds = document.select("dl.col-md-8").select("dd");
            var labelLayout = new HorizontalLayout();
            Image markImage = new Image("logo/" + mark.toLowerCase() + ".png",mark);
            labelLayout.addClassName("labelLayout");
            markImage.setWidth("200px");
            labelLayout.add(markImage);
            add(labelLayout);
            for (int i = 0; i < 5; i++) {
                var hl = new HorizontalLayout();
                if(i <= 1){
                    hl.add(new H3(dts.get(i).text() + ":"));
                }
                else{
                    hl.add(new H3(dts.get(i).text()));
                }
                hl.add(new H3(dds.get(i).text()));
                hl.addClassName("infoLayout");
                add(hl);
            }
            var buttonHl = new HorizontalLayout();
            Button allCarsButton = new Button("SHOW ALL CARS");
            allCarsButton.addClickListener(event -> {
                UI.getCurrent().navigate("/search/" + mark);
            });
            buttonHl.addClassName("buttonHl");
            allCarsButton.addClassName("allCarsButton");
            buttonHl.add(allCarsButton);
            add(buttonHl);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
