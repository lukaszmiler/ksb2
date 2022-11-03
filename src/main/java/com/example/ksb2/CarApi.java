package com.example.ksb2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarApi {
    List<Car> carList = new ArrayList<>();

    public CarApi() {
        carList.add(new Car(1, "Toyota", "Yaris", "White"));
        carList.add(new Car(2, "Audi", "Q8", "Red"));
        carList.add(new Car(3, "Skoda", "Superb", "Blue"));
    }

    @GetMapping
    public ResponseEntity<List<Car>> getCarList() {
        return new ResponseEntity<>(carList, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Car> getCarListById(@PathVariable int id) {
        Optional<Car> foundCar = carList.stream().filter(car -> car.getId() == id).findFirst();
        if (foundCar.isPresent()) return new ResponseEntity<>(foundCar.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
