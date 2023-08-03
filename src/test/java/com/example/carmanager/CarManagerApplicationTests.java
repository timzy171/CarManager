package com.example.carmanager;

import com.example.carmanager.entity.Car;
import com.example.carmanager.repo.CarRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CarManagerApplicationTests {
    @Autowired
    public CarRepository carRepository;


    @Test
    void contextLoads() {
        int c = 0;
        List<Car> carList = carRepository.findAll();
        for (Car realCar : carList) {
            String car = realCar.mark.toLowerCase().trim() + '-' + realCar.model.toLowerCase().trim();
            String[] carInfo = car.split("-");
            String mark = carInfo[0];
            String model = "";
            if (carInfo.length > 2) {
                for (int i = 1; i < carInfo.length; i++) {
                    model += carInfo[i] += "_";
                }
                model = model.substring(0, model.length() - 1);
            } else {
                model = carInfo[1];
            }
            String URL;
            try {
                if (model.contains(" ")) {
                    model = model.replaceAll(" ", "_");
                    URL = "http://www.motorpage.ru/" + firstUpperCase(mark) + "/" + model + "/last/";
                } else {
                    URL = "http://www.motorpage.ru/" + firstUpperCase(mark) + "/" + firstUpperCase(model) + "/last/";

                }
                try {
                    Document document = Jsoup.connect(URL).get();
                    System.out.println(document.baseUri() + " SUCCESS");
                    ++c;
                }
                catch (Exception e){
                    System.out.println(car + " ERROR!");
                    carRepository.delete(realCar);
                }
            } catch (Exception e) {
            }
        }
        System.out.println("VALUE = " + c);
    }

    public String firstUpperCase(String word){
        return word.substring(0,1).toUpperCase() + word.substring(1);
    }
}
