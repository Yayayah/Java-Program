package vehiclessystem;

import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author wangyahui
 */
public class Motorbikes extends Vehicles {

    public Motorbikes(String type, String name,String condition, String color, String mark, String price, Date d, String ps, Date dOfSell) {
        this.typeOfVehicle = "Motorbike";
        this.name = name;
        this.condition=condition;
        this.color = color;
        this.mark = mark;
        this.priceOfPurchase = price;
        this.dateOfPurchase = d;
             this.dateOfSell=dOfSell;
        this.priceOfSell = ps;
        this.space = (double) 0.5;

    }

}
