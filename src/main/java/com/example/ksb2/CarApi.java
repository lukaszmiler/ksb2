package com.example.ksb2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CarApi {
    List<Car> carList = new ArrayList<>();

    public CarApi() {
        carList.add(new Car(1, "Toyota", "Yaris", "White"));
        carList.add(new Car(2, "Audi", "Q8", "Red"));
        carList.add(new Car(3, "Skoda", "Superb", "Blue"));
        carList.add(new Car(4, "Fiat", "Sienna", "Blue"));
    }

    @GetMapping("/cars")
    public String showCars(Model model) {
        model.addAttribute("cars", carList);
        model.addAttribute("newCar", new Car());
        return "cars";
    }

    @GetMapping("/id/{id}")
    public String getCarListById(@PathVariable int id, Model model) {
        Optional<Car> foundCar = carList.stream().filter(car -> car.getId() == id).findFirst();
        if (foundCar.isPresent()) {
            model.addAttribute("foundCar", foundCar.get());
        } else {
            model.addAttribute("foundCar", new Car());
        }
        return "carById";
    }

    @GetMapping("/color/{color}")
    public String getCarListByColor(@PathVariable String color, Model model) {
        List<Car> list = new ArrayList<>();
        carList.stream().filter(car -> car.getColor().equalsIgnoreCase(color)).forEach(list::add);
        if (!list.isEmpty()) model.addAttribute("cars", list);
        return "carByColor";
    }

    @PostMapping("/addCar")
    public String addCar(@ModelAttribute Car car) {
        carList.add(car);
        return "redirect:/cars";
    }

    @GetMapping("/removeCar")
    public String removeCar(@ModelAttribute Car car) {
        for (Car c : carList) {
            if (c.getId() == car.getId()) {
                carList.remove(c);
                break;
            }
        }
        return "redirect:/cars";
    }

    @GetMapping("/modifyCar")
    public String modifyCar(@ModelAttribute Car newCar) {
        Optional<Car> foundCar = findCar(newCar);
        if (foundCar.isPresent()) {
            foundCar.get().setModel(newCar.getModel());
            foundCar.get().setMark(newCar.getMark());
            foundCar.get().setColor(newCar.getColor());
        }
        return "redirect:/cars";
    }

    private Optional<Car> findCar(Car car) {
        return carList.stream().filter(c -> c.getId() == car.getId()).findFirst();
    }
}