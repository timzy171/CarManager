package com.example.carmanager;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.example.carmanager.repo.CarRepository;

import java.util.List;

@Route("/")
@CssImport(value = "./styles/searchView.css")
public class SearchView extends VerticalLayout {
    public SearchView(){
        TextField searchText = new TextField();
        searchText.setPlaceholder("Input car name");
        Button searchButton = new Button();
        searchButton.setText("FIND");
        add(searchText);
        add(searchButton);
        setClassNames(searchText,searchButton);

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
