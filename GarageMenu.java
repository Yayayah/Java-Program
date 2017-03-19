package vehiclessystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author wangyahui 1614494
 */
public class GarageMenu {

    static Garage theGarage;
    //static Location initialLocation = new Location(0, 0);

    public static void menuRun() {
        if (!readObjects()) {
            theGarage = new Garage("Joe’s Garage");
        }
        System.out.println("Welcome to " + theGarage.getName());
        boolean exit = false;
        String select = "";

        // keep processing menu requests until exit
        while (!exit) {
            System.out.println("0= exit, 1=buy vehicle, 2=sell vehicle, 3=display vehicles, 4=available space 5= sale by date of purchase 6= change the status 7= show sale report 8=show the loss: ");
            select = DataInput.inputString();
            System.out.println();
            switch (select) {
                case "0":
                    exit = true;
                    break;
                case "1":
                    addVehicle();
                    break;
                case "2":
                    removeVehicle();
                    break;
                case "3":
                    displayVehicles();
                    break;
                case "4":
                    System.out.println(theGarage.availableSpace());
                    break;
                case "5":
                    displayVehiclesForSell();
                    break;
                case "6":
                    changeStatus();
                    break;
                case "7":
                    showReport();
                    break;
                 case "8":
                    showLoss();
                    break;   
                default:
                    System.out.println("Invalid option");
            }
        }
        storeObjects();

    }

    // enter details about a new vehicle.
    // add that vehicle to the garage
    public static void addVehicle() {
        Vehicles v;

        System.out.println("Enter vehicle type (car, truck, van, or motorbike):");
        String type = DataInput.inputString();
        System.out.println("Enter vehicle name:");
        String name = DataInput.inputString();
        System.out.println("Enter vehicle price($):");
        String price = DataInput.inputString();

        System.out.println("Enter date of purchase(d m y):");
        Date date = DataInput.inputDateFlexible();

        System.out.println("Enter vehcile status (new, used, damaged, wrecked):");
        String condition = DataInput.inputString();
        String color = null;
        String mark = null;
        if (condition.equals("new")) {
            color = "red";
            mark = "selling";
        }
        if (condition.equals("used")) {
            color = "green";
            mark = "can sell";
        }
        if (condition.equals("damaged")) {
            color = "yellow";
            mark = "need to be repaired";
        }
        if (condition.equals("wrecked")) {
            color = "grey";
            mark = "abandon";
        }
        // System.out.println("Enter vehicle sell of purchase:");
        // String ps = DataInput.inputString();
        // System.out.println("Enter vehicle sell of date:");
        // Date dOfSell = DataInput.inputDateFlexible();
        switch (type) {
            case "car":
                v = new Cars(type, name, condition, color, mark, price, date, null, null);

                break;
            case "truck":
                v = new Trucks(type, name, condition, color, mark, price, date, null, null);
                break;
            case "van":
                v = new Vans(type, name, condition, color, mark, price, date, null, null);
                break;
            case "motorbike":
                v = new Motorbikes(type, name, condition, color, mark, price, date, null, null);
                break;
            default:
                System.out.println("Invalid vehicle type");
                return;
        }
        theGarage.buyVehicle(v);
    }

    // sell a vehicle from the garage
    public static void removeVehicle() {
        System.out.println("Enter name of vehicle to sell :");
        String name = DataInput.inputString();
        boolean removeResult = theGarage.sellVehicleWithName(name);
        if (removeResult) {
            System.out.println(name + " was successfully selled.");
        } else {
            System.out.println("The garage can not sell " + name);
        }
    }

    // change the status of vehicle after inspection by a mechanic
    public static void changeStatus() {
        System.out.println("Enter name of vehicle to sell :");
        String name = DataInput.inputString();
        boolean changeS = theGarage.changeVehicleMark(name);
        if (changeS) {
            System.out.println(name + " was successfully changed the status.");
        } else {
            System.out.println(name + " cannot be changed. Because it is wrecked");

        }
    }
    
    // show all vehicles in garage
    public static void displayVehicles() {
        System.out.println(theGarage.ShowAllVehicles());
    }
    
    //show the profit report for sale
    public static void showReport(){System.out.println(theGarage.ShowReportOfSell());
    }
    //show the loss report for unfixable vehicle
    public static void showLoss(){System.out.println(theGarage.ShowLossOfVehicle());
    }
    // show vehicles for sale sorted by date of purchase in garage
    public static void displayVehiclesForSell() {
        System.out.println(theGarage.showDateOfPurchase());
    }

   // store all objects in a text file
    public static void storeObjects() {
        File file = new File("VehicleFile.txt");
        try {
            BufferedWriter fw = new BufferedWriter(new FileWriter(file, true));
            // write one line for Garage
            fw.write(theGarage.getName());
            fw.newLine();

            Iterator<Vehicles> itr;
            itr = theGarage.getListOfVehicles().iterator();
            Vehicles v;
            while (itr.hasNext()) {
                v = itr.next();
                // write vehicle info
                fw.write(v.getTypeOfVehicle() + "\t" + v.getName() + "\t" + v.getCondition() + "\t" + v.getColor() + "\t" + v.getMark()
                    + "\t" + v.getPriceOfPurchase() + "\t" + v.getDate() + "\t" + v.getPriceOfSell() + "\t" + v.getDateOfSell());
                fw.newLine();
            }
            fw.flush();
            fw.close();
            System.out.println("Objects written to file");
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }

   // read  vehicle objects from a text file 
    public static boolean readObjects() {

        boolean success = false;
        File file = new File("Vehicles.txt");
        String line;
        try {
            BufferedReader fr = new BufferedReader(new FileReader(file));
            // read Garage line
            line = fr.readLine();
            if (line != null) {
                theGarage = new Garage(line);
            } else {
                return false;
            }
            Vehicles v = null;

            while ((line = fr.readLine()) != null) {
                String[] s = line.split(",");
                Date d = DataInput.inputDateString(s[6]);
                Date dOfSell = DataInput.inputDateString(s[8]);
                // Date dOfSell = new Date(Long.parseLong(s[8]));
                if (s[0].equalsIgnoreCase("car")) {
                    v = new Cars(s[0], s[1], s[2], s[3], s[4], s[5], d, s[7], dOfSell);
                    theGarage.buyVehicle(v);
                }
                if (s[0].equalsIgnoreCase("van")) {
                    v = new Vans(s[0], s[1], s[2], s[3], s[4], s[5], d, s[7], dOfSell);
                    theGarage.buyVehicle(v);
                }
                if (s[0].equalsIgnoreCase("truck")) {
                    v = new Trucks(s[0], s[1], s[2], s[3], s[4], s[5], d, s[7], dOfSell);
                    theGarage.buyVehicle(v);
                }
                if (s[0].equalsIgnoreCase("motorbike")) {
                    v = new Motorbikes(s[0], s[1], s[2], s[3], s[4], s[5], d, s[7], dOfSell);
                    theGarage.buyVehicle(v);
                }

            }
            fr.close();
            success = true;
        } catch (IOException e1) {
            System.out.println("Error reading file");
        } catch (ArrayIndexOutOfBoundsException e2) {
            System.out.println("Error. Wrong number of fields");
        } catch (NumberFormatException e3) {
            System.out.println("Error. Date is not a long or id number not an integer");
        }
        return success;
    }

}
