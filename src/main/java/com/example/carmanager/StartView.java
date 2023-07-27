package com.example.carmanager;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("/")
@CssImport(value = "./styles/startView.css")
public class StartView extends VerticalLayout {
    public StartView(){
        Image image = new Image("images/carManager.jpg","sorry");
        image.addClassName("carManagerIcon");
        add(image);
        Button button = new Button("Start searching");
        button.addClassName("b1");
        add(button);

        button.addClickListener(buttonClickEvent -> {
            UI.getCurrent().navigate("search");
        });
    }
}
