//import libraries
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import Jama.Matrix;

public class jfuentes_p4 {
    public static void main(String[] args)throws IOException{
       
        //create matrices using java library JAMA that will hold addition of matrices and multiplication of matrices
        Matrix productMatrix;
        Matrix sumMatrix;

        //using getMatrix method get the matrices from each file and off of them create matrices using java library JAMA
        Matrix libMatrix1 = new Matrix(getMatrix("mat1"));
        Matrix libMatrix2 = new Matrix(getMatrix("mat2"));
        Matrix libMatrix3 = new Matrix(getMatrix("mat3"));
        Matrix libMatrix4 = new Matrix(getMatrix("mat4"));

        //only sum and multiply matrices that fit size criteria
        sumMatrix = libMatrix1.plus(libMatrix2);
        productMatrix = libMatrix3.times(libMatrix4);

        //print the first set of matrices to a new file
        printToFile(sumMatrix, "12", 'A');
        printToFile(productMatrix, "34", 'M');

        //next set of matrices: only sum and multiply matrices that fit size criteria
        sumMatrix = libMatrix2.plus(libMatrix1);
        productMatrix = libMatrix4.times(libMatrix3);

        //print the next set of matrices to a new file
        printToFile(sumMatrix, "21", 'A');
        printToFile(productMatrix, "43", 'M');

    
    }

    


    //create matrix to return a matrix from a specified file
    public static double[][] getMatrix(String matrix )throws IOException{

        //create a filler matrix
        double[][] filledMatrix;

        //create possible sizes for the matrix
        int lastName = 7;
        int firstName = 6;

        //put the matrix file to lower case
        matrix = matrix.toLowerCase();

        //open the specified file
        File file = new File("jfuentes_" + matrix + ".txt");
        //create scanner for the file
        Scanner readFile = new Scanner(file);

        //match the matrix name to a matrix file so that we can instantiate our 2D array with the correct amount of cols and rows
        if(matrix.equalsIgnoreCase("mat1")){

            //instantiate the array with the amount of cols and rows specific to this matrix file
            filledMatrix = new double[lastName][firstName];

            //read elements in from file into the 2D array
            for(int i=0; i<lastName; i++){
                for(int j=0; j<firstName; j++){
                    filledMatrix[i][j] = readFile.nextDouble();
                }
            }

        }else if(matrix.equalsIgnoreCase("mat2")){
            
            //instantiate the array with the amount of cols and rows specific to this matrix file
            filledMatrix = new double[lastName][firstName];

            //read elements in from file into the 2D array
            for(int i=0; i<lastName; i++){
                for(int j=0; j<firstName; j++){
                    filledMatrix[i][j] = readFile.nextDouble();
                }
            }
        
        }else if(matrix.equalsIgnoreCase("mat3")){
        
            //instantiate the array with the amount of cols and rows specific to this matrix file
            filledMatrix = new double[2][4];

            //read elements in from file into the 2D array
            for(int i=0; i<2; i++){
                for(int j=0; j<4; j++){
                    filledMatrix[i][j] = readFile.nextDouble();
                }
            }
        
        }else if(matrix.equalsIgnoreCase("mat4")){
        
            //instantiate the array with the amount of cols and rows specific to this matrix file
            filledMatrix = new double[4][2];

            //read elements in from file into the 2D array
            for(int i=0; i<4; i++){
                for(int j=0; j<2; j++){
                    filledMatrix[i][j] = readFile.nextDouble();
                }
            }
        
            
        }else{
            //if the matrix cannot be found the fill the array with null
            filledMatrix=null;
        }
        //close scanner file
		readFile.close();

        //return array that was filled by file
        return filledMatrix;
		

    }

    //method to print a matrix to a specified file name using the base matrices names and type of operation done to build the file name
    public static String printToFile(Matrix matrix, String matrixName, char operation)throws IOException{

        //build a filename using the operation done to the matrix and the base matrix name
        String fileName = "jfuentes_p4_out"+ operation + matrixName + ".txt";

         //creates file using specified file name
         File file = new File(fileName);
         //Opens file to be written to 
         PrintWriter outputFile = new PrintWriter (file);
        
        //using matrix library print the matrix to the output file using the number of matrix coloumns and amount of decimal spaces
        matrix.print(outputFile, matrix.getColumnDimension(), 1);
      
		//close file to write
        outputFile.flush();
		outputFile.close();

        //returnn the file name created and used
        return fileName;
    }

    
}
