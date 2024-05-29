package com.example.ivt22ServerDemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class JsonController {
    ArrayList<Unit> units = new ArrayList<>();
    public JsonController() {
        units.add(new Unit("Лял2",100,150));
        units.add(new Unit("Лял2",30,15));
        units.add(new Unit("Лял3",230,142));
    }

    @GetMapping("/str")

    public String getStr(){
        return "{\"welcome\":\"Привет, Мир!\"}";
    }
    @GetMapping("/unit")
    public Unit getUnit(){
        Unit unit = new Unit("Сергей32", 0, 10);
        //unit.setX(12);
        return unit;
    }
    @GetMapping("/user")
    public User user(){
        User user = new User();
        user.getParams().put("name","Sergey");
        user.getParams().put("x",20);
        user.getParams().put("y",10);
        return user;
    }
    @GetMapping("/units")
    public ArrayList<Unit> getUnits(){
        for (Unit u: units) {
            u.setX(u.getX() + 1);
            u.setY(u.getY() + 1);
        }
        return units;
    }
//
//    JSONArray array = new JSONArray();
//    for (int i = 0; i < array.length(); i++) {
//        System.out.println("#"+array.get(i));
//    }
}
