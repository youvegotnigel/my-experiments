package interviewQuestions;

import org.testng.annotations.Test;

public class LengthOfArray {

    int[] numbers = {5, 7, -3, 9, 2, -9, 0, 3};
    char[] vowels = {'a','e','i','o','u'};
    String[] names = {"CHO","Timo","CP10","Kante","Haevertz","Mason","Chilwell","Silva","Zouma","James","Memndi"};


    public void printMethod(int value){
        System.out.println("\nThe length of the array is " + value);
    }

    @Test
    public void useLengthMethod(){

        int numbersLength = numbers.length;
        int vowelsLength = vowels.length;
        int namesLength = names.length;

        printMethod(numbersLength);
        printMethod(vowelsLength);
        printMethod(namesLength);
    }

    @Test
    public void useIntArrayOutOfBoundException(){

        int i=0;
        try{
            for(i=0;;i++){
                int temp = numbers[i];
            }
        }
        catch (Exception e){

        }
        printMethod(i);
    }

    @Test
    public void useCharArrayOutOfBoundException(){

        int i=0;
        try{
            for(i=0;;i++){
                int temp = vowels[i];
                //System.out.println(temp);
            }
        }
        catch (Exception e){

        }
        printMethod(i);
    }

    @Test
    public void useStringArrayOutOfBoundException(){

        int i=0;
        try{
            for(i=0;;i++){
                String temp = names[i];
                //System.out.println(temp);
            }
        }
        catch (Exception e){

        }
        printMethod(i);
    }
}
