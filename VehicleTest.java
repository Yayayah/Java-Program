package vehiclessystem;

import java.util.Date;
import vehiclessystem.Garage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author wangyahui 1614494
 */
public class VehicleTest {

    public static void test() {

        Garage g = new Garage("Joe’s Garage");
        g.getName();
        Date d1 = DataInput.inputDateString("01/02/2016");
        Date d2 = DataInput.inputDateString("02/11/2016");
        Trucks t = new Trucks("truck", "phil", "damaged", "yellow", "need to be repaired", "240", d1, null, null);
        Cars c = new Cars("car", "Benz", "new", "red", "sold", "5000", d1, "7000", d2);
        Vans v = new Vans("van", "lili", "wrecked", "grey", "abandon", "300", d1, null, null);
        Motorbikes m = new Motorbikes("motorbike", null, "used", "green", "can sell", "200", d2, null, null);
        m.setName("Moto");
        c.getDate();
        c.getDateOfSell();
        g.buyVehicle(m);
        g.buyVehicle(v);
        g.buyVehicle(c);
        g.buyVehicle(t);
        System.out.println(g.availableSpace());
        System.out.println(g.ShowReportOfSell());
        System.out.println(g.ShowAllVehicles());  
        g.changeVehicleMark("phil");
        System.out.println(g.numberOfVehicle());
        g.sellVehicleWithName(c.name);
        System.out.println(g.ShowLossOfVehicle());
        g.changeVehicleMark("lili");
    }
}
