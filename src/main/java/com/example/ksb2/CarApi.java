package com.example.ksb2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        carList.add(new Car(4, "Fiat", "Sienna", "Blue"));
    }

    @GetMapping
    public ResponseEntity<List<Car>> getCarList() {
        return new ResponseEntity<>(carList, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Car> getCarListById(@PathVariable int id) {
        Optional<Car> foundCar = carList.stream().filter(car -> car.getId() == id).findFirst();
        if (foundCar.isPresent()) return new ResponseEntity<>(foundCar.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<Car>> getCarListByColor(@PathVariable String color) {
        List<Car> list = new ArrayList<>();
        carList.stream().filter(car -> car.getColor().equalsIgnoreCase(color)).forEach(list::add);
        if (list != null) return new ResponseEntity<>(list, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity addingCar(@RequestBody Car car) {
        boolean add = carList.add(car);
        if (add) return new ResponseEntity(HttpStatus.CREATED);
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping()
    public ResponseEntity changingCar(@RequestBody Car newCar) {
        Optional<Car> foundCar = carList.stream().filter(car -> car.getId() == newCar.getId()).findFirst();
        if (foundCar.isPresent()) {
            carList.remove(foundCar.get());
            carList.add(newCar);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping()
    public ResponseEntity changingCarDetail(@RequestBody Car newCar) {
        Optional<Car> foundCar = carList.stream().filter(car -> car.getId() == newCar.getId()).findFirst();
        if (foundCar.isPresent()) {
            carList.remove(foundCar.get());
            carList.add(newCar);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping()
    public ResponseEntity<Car> deleteCar(@RequestBody Car CarToDelete) {
        Optional<Car> foundCar = carList.stream().filter(car -> car.getId() == CarToDelete.getId()).findFirst();
        if (foundCar.isPresent()) {
            carList.remove(foundCar.get());
            return new ResponseEntity<>(foundCar.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
