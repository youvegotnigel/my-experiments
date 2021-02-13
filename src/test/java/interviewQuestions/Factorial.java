package interviewQuestions;

import org.testng.annotations.Test;

public class Factorial {


    public int getFactorial(int n){
       if (n==0)
           return 1;
       else
           return n*getFactorial(n-1);
    }

    public int getDigitLength(long n) {
       return String.valueOf(n).length();
    }

    @Test(enabled = false)
    public void print(){
        System.out.println( "Factorial : " + getFactorial(22));
        System.out.println("Digit Length : " + getDigitLength(1233));
        //factorialTest(23);

    }

    @Test
    public void factorialTest(int n){
        if (getDigitLength(getFactorial(n))==n)
            System.out.println("true");
        else
            System.out.println("false");

    }

    public String convertIntToString(int n){
        return String.valueOf(n);
    }

    public int getLengthOfString(String s){

        char [] array = s.toCharArray();
        int i=0;

        for(char c : array){
            i++;
        }
        return i;
    }

    @Test
    public void test(){

        String temp = convertIntToString(123456);
        System.out.println(getLengthOfString(temp));
    }


}
