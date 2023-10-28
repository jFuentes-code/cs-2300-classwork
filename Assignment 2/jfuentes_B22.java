//import libraries
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import org.ejml.data.DMatrixRMaj;
import org.ejml.dense.row.misc.RrefGaussJordanRowPivot_DDRM;



public class jfuentes_B22 {
    public static void main(String[] args)throws IOException{
        //create a DMatrixRMaj matrix using the ejml library, create this matrix off of the 
        //augmented matrix create and store in part 1
        DMatrixRMaj augmentedMatrix = new DMatrixRMaj(getMatrix("jfuentes_B21_AugmentedMatrix.txt"));

        //create the first ref
        RrefGaussJordanRowPivot_DDRM reducer = new RrefGaussJordanRowPivot_DDRM();

        //use the reduce method to get the rref
        reducer.reduce(augmentedMatrix, 5);
        //print the rref
        //formating is weird so also print to console for clarity
        printToFile(augmentedMatrix, "B22_rref_Matrix");
        //print to rref to console for clarity
        augmentedMatrix.print();

    }

    //create matrix to return the password from the input file
    public static double[][] getMatrix(String input )throws IOException{

        //create a variables to hold all of the file message

        ArrayList<Integer> matrixValues = new ArrayList<Integer>();

        //open the specified file
        File file = new File(input);
        //create scanner for the file
        Scanner readFile = new Scanner(file);

        //read elements in from file into message string
        while(readFile.hasNext()){
        matrixValues.add(readFile.nextInt());

        }

        //initialize the size of the 2D array with 4 rows as is required
        //divide the total size of the matrix by these 4 rows to get the amount of columns
        int rows = 4;
        int cols = (int)(Math.ceil(matrixValues.size()/4.0));
        int counter = 0;
        double[][] augmentedMatrix = new double[rows][cols];

        //fill the augmented Matrix with the values from the text file stored in array list
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){

                augmentedMatrix[i][j] = matrixValues.get(counter);
                counter++;

            }
        }
        
        //close scanner file
		readFile.close();

        //return the message from the file
        return augmentedMatrix;
		

    }

    //method to print a matrix to a specified file name using the base matrices names
    public static String printToFile(DMatrixRMaj matrix, String matrixName)throws IOException{

        //build a filename using the matrix name
        String fileName = "jfuentes_"+ matrixName + ".txt";

         //creates file using specified file name
         File file = new File(fileName);
         //Opens file to be written to 
         PrintWriter outputFile = new PrintWriter (file);
        
        //using matrix library print the matrix to the output file using the number of matrix coloumns and amount of decimal spaces
        outputFile.print(matrix.toString());
      
		//close file to write
        outputFile.flush();
		outputFile.close();

        //returnn the file name created and used
        return fileName;
    }

}
