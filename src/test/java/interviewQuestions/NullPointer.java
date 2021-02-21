package interviewQuestions;

public class NullPointer {


    public static void main(String args[]){

        String str = null;

        //str = new String("java");
        System.out.println("The String is " +  str);


        //How to handle null pointer
        if(str != null){

            System.out.println("The String Length is " + str.length());
        }
        else {
            System.out.println("ERROR");
        }



    }
}
