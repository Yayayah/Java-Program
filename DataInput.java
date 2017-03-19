package vehiclessystem;

// A class for data input methods
// Yahui Wang 1614494
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DataInput {

    static Scanner kb = new Scanner(System.in);

    //  keyboard input.
    public static String inputString() {
        return kb.nextLine();
    }

    //input date
    public static Date inputDateFlexible() {
        SimpleDateFormat df1 = new SimpleDateFormat("d M y");
        SimpleDateFormat df2 = new SimpleDateFormat("d/M/y");
        Date d = null;
        while (d == null) {
            System.out.println("Enter a date: ");
            String s = inputString();
            try {
                d = df1.parse(s);
            } catch (ParseException e1) {
                try {
                    d = df2.parse(s);
                } catch (ParseException e2) {
                    System.out.println("Not a valid date");
                }
            }
        }
        return d;
    }

    //input a string as date 
    public static Date inputDateString(String s) {
        SimpleDateFormat df1 = new SimpleDateFormat("d M y");
        SimpleDateFormat df2 = new SimpleDateFormat("d/M/y");
        Date d = null;
        while (d == null) {
            try {
                d = df1.parse(s);
            } catch (ParseException e1) {
                try {
                    d = df2.parse(s);
                } catch (ParseException e2) {
                    System.out.println("Not a valid date");
                }
            }
        }
        return d;
    }

}
