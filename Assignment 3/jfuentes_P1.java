//import libraries
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import Jama.Matrix;

public class jfuentes_P1 {
    public static void main(String[] args)throws IOException{
       
        //create identity matrix for computation
        Matrix identityMatrix = Matrix.identity(3,3);


        //using getMatrix method get the matrices from each file and off of them create matrices using java library JAMA
        Matrix matrixD = new Matrix(getMatrix("jfuentes_P1_matD.txt"));
        Matrix matrixE = new Matrix(getMatrix("jfuentes_P1_matE.txt"));

        Matrix gaussJordon = identityMatrix.minus(matrixD).inverse().times(matrixE);



        printToFile(gaussJordon);

    }

    


    //create matrix to return a matrix from a specified file
    public static double[][] getMatrix(String input )throws IOException{

        //create a variables to hold all of the file message
        String message = "";

        double[][] cypherMatrix = new double[1][1];

        int rows;
        int cols;
        int counter;
        

        //open the specified file
        File file = new File(input);
        //create scanner for the file
        Scanner readFile = new Scanner(file);

        //read elements in from file into message string
        while(readFile.hasNext()){
        
        message = message.concat(readFile.next() + " ");
        }

        //parse the sequence of numbers using the spaces as delimeters
        String[] listOfValues = message.split(" ");

        if(listOfValues.length>3){
            //initialize the size of the 2D array with 4 rows as is required by the hill matrix
            //divide the total size of the matrix by these 4 rows to get the amount of columns
            rows = 3;
            cols = 3;
            counter = 0;
            cypherMatrix = new double[rows][cols];

            //iterate over the cols first then rows filling each element with the
            //next sequential number string converted to int
            for(int i=0; i<cols; i++){
                for(int j=0; j<rows; j++){
                    if(counter < message.length()){
                        cypherMatrix[i][j] = Double.parseDouble(listOfValues[counter]);
                        counter++;
                    }else{
                        cypherMatrix[i][j] = 0;
                    }
                }
            }
        }else{
            //initialize the size of the 2D array with 4 rows as is required by the hill matrix
            //divide the total size of the matrix by these 4 rows to get the amount of columns
            rows = 3;
            cols = 1;
            counter = 0;
            cypherMatrix = new double[rows][cols];

            //iterate over the cols first then rows filling each element with the
            //next sequential number string converted to int
            for(int i=0; i<rows; i++){
                for(int j=0; j<cols; j++){
                    if(counter < message.length()){
                        cypherMatrix[i][j] = Double.parseDouble(listOfValues[counter]);
                        counter++;
                    }else{
                        cypherMatrix[i][j] = 0;
                    }
                }
            }
        }
        //close scanner file
		readFile.close();
        
        //return the cypher matrix from the file
        return cypherMatrix;
		
        
    }

    //method to print a matrix to a specified file name using the base matrices names and type of operation done to build the file name
    public static String printToFile(Matrix matrix)throws IOException{

        //build a filename using the operation done to the matrix and the base matrix name
        String fileName = "jfuentes_P1_solutionMatrix.txt";

         //creates file using specified file name
         File file = new File(fileName);
         //Opens file to be written to 
         PrintWriter outputFile = new PrintWriter (file);
        
        //using matrix library print the matrix to the output file using the number of matrix coloumns and amount of decimal spaces
        matrix.print(outputFile, matrix.getColumnDimension(), 1);

        for(int i=0;i<matrix.getRowDimension(); i++){
            for(int j=0; j<matrix.getColumnDimension(); j++)
            System.out.printf("%-10.1f\n", matrix.get(i,j));
        }
      
		//close file to write
        outputFile.flush();
		outputFile.close();

        //returnn the file name created and used
        return fileName;
    }

    
}
