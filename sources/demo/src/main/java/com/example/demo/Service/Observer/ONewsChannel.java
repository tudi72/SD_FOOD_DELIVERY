package com.example.demo.Service.Observer;

import lombok.Getter;
import lombok.Setter;

import java.util.Observable;
import java.util.Observer;

@Getter
@Setter
public class ONewsChannel implements Observer {

    private String news;

    @Override
    public void update(Observable o, Object news) {
        this.setNews((String) news);
    }
}
