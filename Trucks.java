/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclessystem;

import java.util.Date;

/**
 *
 * @author wangyahui
 */
public class Trucks extends Vehicles {

    public Trucks(String type, String name,String condition, String color, String mark, String price, Date d, String ps, Date dOfSell) {
        this.typeOfVehicle = "Truck";
        this.name = name;
        this.condition=condition;
        this.color = color;
        this.mark = mark;
        this.priceOfPurchase = price;
             this.dateOfSell=dOfSell;
        this.dateOfPurchase = d;
        this.priceOfSell = ps;
        this.space = 4;
    }

}
