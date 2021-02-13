package interviewQuestions;

import org.testng.annotations.Test;

public class FindStringLength {


    private String word = "this-is-a-sample-string";


    public void printMethod(int value){

        System.out.println("\nThe no of characters in \"" + word +"\" is : " + value);
    }

    @Test
    public void useInBuildMethod() {

        int length = word.length();
        printMethod(length);
    }


    @Test
    public void useToCharArray(){

        char[] array= word.toCharArray();   // convert string into a char array
        System.out.print("Content of Array:");

        int i = 0;
        for(char c: array){
            //System.out.print(c);
            i++;
        }
       printMethod(i);
    }


    @Test
    public void useArrayOutOfBoundException(){

        int i=0;
        try {
            for(i=0;;i++){
               word.charAt(i); // create the array out of bound exception to exit the loop
            }
        }
        catch (Exception e){

        }
        printMethod(i);
    }

}
