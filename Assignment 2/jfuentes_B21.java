import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import Jama.Matrix;

public class jfuentes_B21 {
    
    public static void main(String[] args)throws IOException{
        //create the augmented matrix derrived for the given roundabout problem
        double[][] augmentedArray = {{0,0,1,-1, 0},{0,-1,1,0, 0},{1,-1,0,0, 0},{1,0,0,-1, 0}};
        Matrix augmentedMatrix;

        //create file scanner
        Scanner input = new Scanner(System.in);

        //create variables to have user input stores
        double flow1 = 0;
        double flow2 = 0;
        double flow3 = 0;
        double flow4 = 0;

        boolean validInput = false;
 
        //prompt the user for input
        System.out.println("Please enter the four flow values in sequential order from flow 1 to flow 4 separated by a space.\n"+
                                     "for example: 100 300 400 200");
        //while the userInput is not valid loop
        while(!validInput){
            //try to get user input
            try{
                //take in user input into strings and set validInput to true
                flow1 = input.nextInt();
                flow2 = input.nextInt();
                flow3 = input.nextInt();
                flow4 = input.nextInt();
                validInput = true;
            }
            //if their is an error set validInput to false
            catch(Exception e){
                validInput = false;
            }
        } 

        input.close();//close scanner class
        
        //set the last values in each row of the augmented array to the flow value entered
        augmentedArray[0][augmentedArray[0].length -1] = flow1;
        augmentedArray[1][augmentedArray[0].length -1] = flow2;
        augmentedArray[2][augmentedArray[0].length -1] = flow3;
        augmentedArray[3][augmentedArray[0].length -1] = flow4;

        //turn augmented array into a matrix using jama
        augmentedMatrix = new Matrix(augmentedArray);

        //pirnt the augmented Matrix using the printTo file method
        printToFile(augmentedMatrix, "B21_AugmentedMatrix");

    }

    //method to print a matrix to a specified file name using the base matrices names 
    public static String printToFile(Matrix matrix, String matrixName)throws IOException{

        //build a filename using the matrix name
        String fileName = "jfuentes_"+ matrixName + ".txt";

         //creates file using specified file name
         File file = new File(fileName);
         //Opens file to be written to 
         PrintWriter outputFile = new PrintWriter (file);
        
        //using matrix library print the matrix to the output file using the number of matrix coloumns and amount of decimal spaces
        matrix.print(outputFile, matrix.getColumnDimension(), 0);
      
		//close file to write
        outputFile.flush();
		outputFile.close();

        //returnn the file name created and used
        return fileName;
    }

}
