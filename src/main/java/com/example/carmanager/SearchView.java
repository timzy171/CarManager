package com.example.carmanager;

import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("/search")
public class SearchView extends VerticalLayout {
    public SearchView(){
        MessageInput input = new MessageInput();
        input.addSubmitListener(submitEvent -> {
            System.out.println(submitEvent.getValue());
        });
        add(input);

    }

}
