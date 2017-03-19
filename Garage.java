package vehiclessystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author wangyahui 1614494
 */
// Garage contains all the vehicles
public class Garage {

    private String name;
    private ArrayList<Vehicles> listOfVehicles;

    public Garage(String name) {
        this.name = name;
        this.listOfVehicles = new ArrayList<Vehicles>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Vehicles> getListOfVehicles() {
        return listOfVehicles;
    }

    //available space
    public double availableSpace() {
        Vehicles v = null;
        double space = 0;
        Iterator<Vehicles> it = listOfVehicles.iterator();
        while (it.hasNext()) {
            v = it.next();
            space = space + v.getSpace();
        }
        return (20 - space);
    }

    // try  buy a vehicle to garage
    public boolean buyVehicle(Vehicles someVehicles) {
        double space = 0;
        boolean validSpace = false;
        Vehicles v;
        //garage space is full
        if (space >= 20) {
            System.out.println("The space is full. You cannot buy a vehicle anymore.");
            return validSpace;
        } else {
            //garage space is not full
            //get the space
            Iterator<Vehicles> it = listOfVehicles.iterator();
            while (it.hasNext()) {
                v = it.next();
                space = space + v.getSpace();
            }
            double tmp = someVehicles.getSpace() + space;
            if (tmp <= 20) {

                this.listOfVehicles.add(someVehicles);
                space = tmp;

                System.out.println("You sucessfully bought a vehicle. The space of the garage is : " + space);
                validSpace = true;
            } else {
                System.out.println("The space cannot storage a " + someVehicles.getTypeOfVehicle() + ". You cannot buy it.");
            }
            return validSpace;
        }
    }

    // try sell an vehicle with a certain name
    public boolean sellVehicleNamed(String name) {
        boolean result = false;
        Iterator<Vehicles> it = listOfVehicles.iterator();
        Vehicles v;
        Vehicles vehicleTosell = null;
        while (it.hasNext()) {
            v = it.next();
            if (v.getName().equals(name)) {
                vehicleTosell = v;
            }
        }
        if (vehicleTosell != null & vehicleTosell.getMark().equals("selling")) {
            vehicleTosell.setMark("sold");
            vehicleTosell.setSpace(0);
            if (vehicleTosell.getPriceOfSell() == null) {
                System.out.println("Please input the price of sell:");
                String pOfSell = DataInput.inputString();
                vehicleTosell.setPriceOfSell(pOfSell);
            } else {

                System.out.println("The sale price has set in the list. It is " + vehicleTosell.getPriceOfSell() + "$.");
            }
            System.out.println("Please input the date of sell(d m y):");
            Date dOfsell = DataInput.inputDateFlexible();
            vehicleTosell.setDate(dOfsell);
            //this.listOfVehicles.remove(vehicleTosell);
            result = true;
        }
        return result;
    }

    // try sell a vehicle with a certain name
    public boolean sellVehicleWithName(String name) {
        Iterator<Vehicles> it = listOfVehicles.iterator();
        Vehicles v;
        while (it.hasNext()) {
            v = it.next();
            if (sellVehicleNamed(name)) {
                return true;
            }
        }
        return false;
    }

// try change the statue of a vehicle with a certain name
    public boolean changeVehicleMarked(String name) {
        boolean result = false;
        Iterator<Vehicles> it = listOfVehicles.iterator();
        Vehicles v;
        Vehicles vehicleTochange = null;
        while (it.hasNext()) {
            v = it.next();
            if (v.getName().equals(name)) {
                vehicleTochange = v;
            }
        }
        if (!vehicleTochange.getMark().equals("selling") & !vehicleTochange.getMark().equals("abandon")) {
            if (vehicleTochange.condition.equals("used")) {
                vehicleTochange.setMark("selling");
                System.out.println("The used vehicle can sell now.");
            }
            if (vehicleTochange.condition.equals("damaged")) {
                vehicleTochange.setCondition("used");
                vehicleTochange.setColor("green");
                vehicleTochange.setMark("selling");
                System.out.println("The damaged vehicle is repaird.It can sell now.");
            }
            result = true;
        }
        return result;
    }

    // try and change the status of a vehicle with a certain name
    public boolean changeVehicleMark(String name) {
        Iterator<Vehicles> it = listOfVehicles.iterator();
        Vehicles v;
        while (it.hasNext()) {
            v = it.next();
            if (changeVehicleMarked(name)) {
                return true;
            }
        }
        return false;

    }
    
    // How many vehicle objects?
    public int numberOfVehicle() {
        return listOfVehicles.size();
    }


    // get this information from all Vehicles
    public String ShowAllVehicles() {
        String desc = "------ Used Vehicle System : " + this.getName() + " ------" + "\n";
        Iterator<Vehicles> it = listOfVehicles.iterator();
        Vehicles v;
        while (it.hasNext()) {
            v = it.next();
            desc = desc + v.getTypeOfVehicle() + "\t" + v.getName() + "\t" + v.getCondition() + "\t" + v.getColor() + "\t" + v.getMark()
                    + "\t" + v.getPriceOfPurchase() + "\t" + v.getDate() + "\t" + v.getPriceOfSell() + "\t" + v.getDateOfSell() + "\n";
        };
        return desc;
    }
    
    // get this profit information from  selled Vehicles
    public String ShowReportOfSell() {
        String desc = "------ the sale report of " + this.getName() + " ------" + "\n";
        Iterator<Vehicles> it = listOfVehicles.iterator();
        Vehicles v;
        double total = 0.0;
        double t1;
        double t2;
        while (it.hasNext()) {
            v = it.next();
            if (v.getMark().equals("sold")) {
                desc = desc + v.getName() + "\t" + v.getPriceOfPurchase() + "\t" + v.getPriceOfSell() + "\n";
                t1 = Double.parseDouble(v.getPriceOfSell());
                t2 = Double.parseDouble(v.getPriceOfPurchase());
                total = total + (t1 - t2);
            }
        }

        desc = desc + "The total profit is: " + total + "$" + "\n";
        return desc;
    }

    // get this loss from  unflexible vehicles
    public String ShowLossOfVehicle() {
        String desc = "------ the loss report of " + this.getName() + " ------" + "\n";
        Iterator<Vehicles> it = listOfVehicles.iterator();
        Vehicles v;
        double total = 0.0;

        while (it.hasNext()) {
            v = it.next();
            if (v.getCondition().equals("wrecked")) {
                desc = desc + v.getName() + "\t" + v.getCondition() + "\t" + v.getPriceOfPurchase() + "\n";
                total = total + Double.parseDouble(v.getPriceOfPurchase());
                // total = total + t1;
            }
        }

        desc = desc + "The total loss is: " + total + "$" + "\n";
        return desc;
    }

    // get this sale information sorted by date of purchase
    public String showDateOfPurchase() {
        ArrayList<Vehicles> v = this.listOfVehicles;

        Vehicles v1 = null;
        Vehicles v2 = null;
        Vehicles tmp = null;
        for (int i = 0; i < v.size(); i++) {
            for (int j = i + 1; j < v.size(); j++) {
                v1 = v.get(i);
                v2 = v.get(j);

                if ((v1.getDate().compareTo(v2.getDate()) > 0)) {
                    tmp = v.get(i);
                    v.set(i, v.get(j));
                    v.set(j, tmp);
                }

            }
        }
        String desc = "------ Vehicles for sale sorted by date of purchase ------" + "\n";
        Iterator<Vehicles> it = v.iterator();
        Vehicles t;
        while (it.hasNext()) {
            t = it.next();
            if (t.getMark().equals("selling")) {
                desc = desc + t.getTypeOfVehicle() + "\t" + t.getName() + "\t" + t.getCondition() + "\t" + t.getColor() + "\t" + t.getMark()
                        + "\t" + t.getPriceOfPurchase() + "\t" + t.getDate() + "\t" + t.getPriceOfSell() + "\t" + t.getDateOfSell() + "\n";
            }
        };
        return desc;
    }

}
