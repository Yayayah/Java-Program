package vehiclessystem;

import java.util.Date;

/**
 *
 * @author wangyahui 1614494
 */
public class Vehicles {

// name, theMake, model, typeOfVehicle,  color, condition, price, location
    protected String name;
    protected String theMake;
    protected String model;
    protected String typeOfVehicle;
    protected String color;
    protected String condition;
    protected String priceOfPurchase;
    protected String priceOfSell;

    protected double space;
    protected Date dateOfPurchase;
    protected Date dateOfSell;
    protected String mark;

    // this creates a String form of a vehicle object that be written to a text file
    public String flatten() {
        return typeOfVehicle + "," + name + "," + condition + "," + color + "," + mark + "," + priceOfPurchase + "," + dateOfPurchase.getTime() + "," + priceOfSell + "," + dateOfSell.getTime();
    }

    public Date getDate() {
        return dateOfPurchase;
    }

    public void setDate(Date date) {
        this.dateOfPurchase = date;
    }

    public Date getDateOfSell() {
        return dateOfSell;
    }

    public void setDateOfSell(Date date) {
        this.dateOfSell = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getTypeOfVehicle() {
        return typeOfVehicle;
    }

    public void setTypeOfVehicle(String typeOfVehicle) {
        this.typeOfVehicle = typeOfVehicle;
    }

    public String getTheMake() {
        return theMake;
    }

    public void setTheMake(String theMake) {
        this.theMake = theMake;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getPriceOfPurchase() {
        return priceOfPurchase;
    }

    public void setPriceOfPurchase(String price) {
        this.priceOfPurchase = price;
    }

    public void setPriceOfSell(String price) {
        //  String str = this.priceOfPurchase;
        //  int a = Integer.parseInt(str) + 100;
        this.priceOfSell = price;
    }

    public String getPriceOfSell() {
        return priceOfSell;
    }

    public void setSpace(double space) {
        this.space = space;
    }

    public double getSpace() {
        return space;
    }

}
