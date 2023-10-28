//import libraries
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import Jama.Matrix;


public class jfuentes_A22 {
    public static void main(String[] args)throws IOException{
       
        //hard code encoding array that will be converted to matrix using jama
        double[][] encodingArray = {{1,-1,-1,1},{2,-3,-5,4},{-2,-1,-2,2},{3,-3,-1,2}};

        //turn the encoding array into encoding matrix using jama
        Matrix encodingMatrix = new Matrix(encodingArray);
        //create inverse of encoding matrix using the inverse method in jama
        Matrix inverseMatrix = encodingMatrix.inverse();

        //get the number sequence from the given text file using getMessage method
        double[][] cypherArray = getMessage("input-A22.txt");
        //turn the cypher array into a matrix using jama
        Matrix cypher = new Matrix(cypherArray);

        //convert the cypher matrix to the plainText Matrix by multipling inverse matrix by the cypher matrix
        Matrix plainTextMatrix = inverseMatrix.times(cypher);

        //turn the plain text message into string usint plainTxtToString method
        String message = plainTxtToString(plainTextMatrix);

        
        //print the message to a file
        printToFile(message, "A22_message");

    
    }

    


    //create matrix to return the sequence of numbers from the input file
    public static double[][] getMessage(String input )throws IOException{

        //create a variables to hold all of the file message
        String message = "";
        

        //open the specified file
        File file = new File(input);
        //create scanner for the file
        Scanner readFile = new Scanner(file);

        //read elements in from file into message string
        while(readFile.hasNext()){
        message = readFile.next();
        message = message.concat(readFile.nextLine() + " ");
        }

        //parse the sequence of numbers using the spaces as delimeters
        String[] listOfValues = message.split(" ");

        //initialize the size of the 2D array with 4 rows as is required by the hill matrix
        //divide the total size of the matrix by these 4 rows to get the amount of columns
        int rows = 4;
        int cols = (int)(Math.ceil(listOfValues.length/4.0));
        int counter = 0;
        double[][] cypherMatrix = new double[rows][cols];

        //iterate over the cols first then rows filling each element with the
        //next sequential number string converted to int
        for(int i=0; i<cols; i++){
            for(int j=0; j<rows; j++){
                if(counter < message.length()){
                    cypherMatrix[j][i] = Integer.parseInt(listOfValues[counter]);
                    counter++;
                }else{
                    cypherMatrix[j][i] = 0;
                }
            }
        }
        
        //close scanner file
		readFile.close();

        //return the cypher matrix from the file
        return cypherMatrix;
		

    }

    //method to turn the plain text matrix to the original message
    public static String plainTxtToString(Matrix plainTxt){

        //instantiate the message string
        String message = "";
        //turn the matrix into an array for easy transveribility
        double[][] txtArray = plainTxt.getArray();

        //iterate throught the array converting the int to its unicode char and appending it to the message
        for(int i=0; i<plainTxt.getColumnDimension(); i++){
            for(int j=0; j<plainTxt.getRowDimension(); j++){
                message = message + (char)(int)(Math.round(txtArray[j][i]));

            }
        }

        return message;
    }

    //method to print a matrix to a specified file name
    public static String printToFile(String message, String matrixName)throws IOException{

        //build a filename using the matrix name
        String fileName = "jfuentes_"+ matrixName + ".txt";

         //creates file using specified file name
         File file = new File(fileName);
         //Opens file to be written to 
         PrintWriter outputFile = new PrintWriter (file);
        
        //using matrix library print the matrix to the output file using the number of matrix coloumns and amount of decimal spaces
        outputFile.print(message);
      
		//close file to write
        outputFile.flush();
		outputFile.close();

        //returnn the file name created and used
        return fileName;
    }

    
}
