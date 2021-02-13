package interviewQuestions;

import org.testng.annotations.Test;

public class ReverseStuff {

    private String word = "this-is-a-sample-string";
    private int num = 123456789;

    @Test
    public void reverseString(){


        String reverse = "";

        for(int i=word.length()-1; i>=0 ;i--){
            reverse = reverse + word.charAt(i);
        }
        System.out.println(reverse);
    }

    @Test
    public void useStringBuffer(){

        StringBuffer buffer = new StringBuffer(word);
        String reverse = buffer.reverse().toString();
        System.out.println(reverse);
    }

    @Test
    public void reverseInt(){

        int reverse=0;
        while (num !=0){
            reverse = reverse*10 + num%10;
            num = num/10;
        }
        System.out.println(reverse);

    }

    public void swapTwoNumbers(int num1, int num2){
        System.out.println("\n***************Before Swap*************");
        System.out.println("num1 is : " + num1);
        System.out.println("num2 is : " + num2);

        num1 = num1*num2;
        num2 = num1/num2;
        num1 = num1/num2;

        System.out.println("\n****************After Swap*************");
        System.out.println("num1 is : " + num1);
        System.out.println("num2 is : " + num2);
    }

    public void swapTwoNumbersWithTempVariable(int num1, int num2){
        System.out.println("\n***************Before Swap*************");
        System.out.println("num1 is : " + num1);
        System.out.println("num2 is : " + num2);

        int temp = 0;
        temp = num1;
        num1 = num2;
        num2 = temp;

        System.out.println("\n****************After Swap*************");
        System.out.println("num1 is : " + num1);
        System.out.println("num2 is : " + num2);
    }

    @Test
    public void testSwapNumbers(){
        swapTwoNumbers(12,5);
        swapTwoNumbersWithTempVariable(55,22);
    }
}
