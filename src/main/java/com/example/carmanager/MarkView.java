package com.example.carmanager;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
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
            String markName = document.select("h1").get(0).text();
            var labelLayout = new HorizontalLayout();
            labelLayout.addClassName("labelLayout");
            labelLayout.add(new H2(markName));
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
                hl.setAlignItems(Alignment.CENTER);
                add(hl);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
