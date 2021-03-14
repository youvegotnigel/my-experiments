package calendar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

    public static void main(String args[]){

        System.out.println("*********** Print Current Date in *************"); //14 March 2021
        System.out.println(getCurrentDate());
        System.out.println("************  DD-MMMM-YYYY Format *************");

        System.out.println("Date : " + getDate(getCurrentDate()));
        System.out.println("Month : " + getMonth(getCurrentDate()));
        System.out.println("Year : " + getYear(getCurrentDate()));

    }

    public static String getCurrentDate(){
        Date date = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
        String strDate = formatter.format(date);
        //System.out.println("Date Format with dd MMMM yyyy : "+strDate);
        return strDate;
    }

    public static int getYear(String strDate){

        String year = strDate.split(" ")[2];
        return Integer.parseInt(year);
    }

    public static int getDate(String strDate){

        String date = strDate.split(" ")[0];
        return Integer.parseInt(date);
    }

    public static String getMonth(String strDate){

        String month = strDate.split(" ")[1];
        return month;
    }


}
