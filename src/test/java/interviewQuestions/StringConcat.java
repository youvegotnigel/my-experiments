package interviewQuestions;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringConcat {

    @Test
    public void conCatTest(){
        //Actual    = "Sat, Jan 30, '21"
        String expected  = "Sat, 30 Jan";

        // Getting the Current Date
        //String todayDate = new SimpleDateFormat("\"EEE, MMM d, ''yy\"").format(new Date());
        String todayDate = new SimpleDateFormat("\"EEE, d MMM, ''yy\"").format(new Date());

        //System.out.println("OI app date : " + displayedDate);
        System.out.println("Programme date : "+ todayDate);

        String[] arrSplit = todayDate.split(",");


        for (int i=0; i < arrSplit.length; i++){
            System.out.println("part-" + (i+1) +" : "+ arrSplit[i]);
        }

        String day = arrSplit[0].substring(1);
        System.out.println(day);

        String date = arrSplit[1].trim();
        System.out.println(date);

        String fixed = day +", " +date;
        System.out.println(fixed);

        Assert.assertEquals(expected,fixed);
        //Assert.assertEquals(expecte);
    }
}
