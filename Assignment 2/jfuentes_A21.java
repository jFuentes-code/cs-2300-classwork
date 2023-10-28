//import libraries
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import Jama.Matrix;


public class jfuentes_A21 {
    public static void main(String[] args)throws IOException{
       
        //hard code encoding array that will be converted to matrix using jama
        double[][] encodingArray = {{1,-1,-1,1},{2,-3,-5,4},{-2,-1,-2,2},{3,-3,-1,2}};

        //turn the encoding array into encoding matrix using jama
        Matrix encodingMatrix = new Matrix(encodingArray);

        //get the message from the given text file using getMessage method
        String message = getMessage("input-A21.txt");

        //turn the message into a plain text array using getPlainText method
        //turn the 2D plain text array into a plainTxt matrix using Jama
        Matrix plainTxt = new Matrix(getPlainText(message));

        //multiply the encoding matrix by the plainText matrix to get the final cypher Matrix
        Matrix cypherMatrix = encodingMatrix.times(plainTxt);

        
        //output the plain text matrix and the cypher matrix to their own text files
        printToFile(plainTxt, "A21_PlainTextMatrix");
        printToFile(cypherMatrix, "A21_CypherMatrix");

    
    }

    


    //create matrix to return the password from the input file
    public static String getMessage(String input )throws IOException{

        //create a variables to hold all of the file message
        String message;
        

        //open the specified file
        File file = new File(input);
        //create scanner for the file
        Scanner readFile = new Scanner(file);

        //read elements in from file into message string

        message = readFile.nextLine();
        
        //close scanner file
		readFile.close();

        //return the message from the file
        return message;
		

    }

    //method to turn message into a plain text 2D array
    public static double[][] getPlainText(String message){

        //initialize the size of the 2D array with 4 rows as is required by the hill matrix
        //divide the total size of the matrix by these 4 rows to get the amount of columns
        int rows = 4;
        int cols = (int)(Math.ceil(message.length()/4.0));
        double[][] plainText = new double[rows][cols];

        //create counter to iterate through each char of the message
        int counter = 0;

        //iterate over the rows first then cols filling each element with the
        //unicode value of each char in message, if no more chars fill remainder of array with 0's
        for(int i=0; i<cols; i++){
            for(int j=0; j<rows; j++){
                if(counter < message.length()){
                    plainText[j][i] = (int)message.charAt(counter);
                    counter++;
                }else{
                    plainText[j][i] = 0;
                }
            }
        }


        
        //return 2D array
        return plainText;
    }

    //method to print a matrix to a specified file name 
    public static String printToFile(Matrix matrix, String matrixName)throws IOException{

        //build a filename matrix name
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
