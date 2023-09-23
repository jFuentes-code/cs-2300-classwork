//import libraries
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import Jama.Matrix;

public class jfuentes_p7 {
    public static void main(String[] args)throws IOException{
       
        //using getMatrix method get the matrices from each file and off of them create matrices using java library JAMA
        Matrix libMatrix1 = new Matrix(getMatrix("mat1"));
        Matrix libMatrix2 = new Matrix(getMatrix("mat2"));
        Matrix libMatrix3 = new Matrix(getMatrix("mat3"));
        Matrix libMatrix4 = new Matrix(getMatrix("mat4"));

        Matrix libMatrixR = new Matrix(getMatrix("r"));
        Matrix libMatrixS = new Matrix(getMatrix("s"));
        Matrix libMatrixU = new Matrix(getMatrix("u"));
        Matrix libMatrixV = new Matrix(getMatrix("v"));
        Matrix libMatrixW = new Matrix(getMatrix("w"));

        //create a list of matrices and a list for their names to dot product each of them respectively
        Matrix[] matrixList = {libMatrixR, libMatrixS, libMatrixU, libMatrixV, libMatrixW};
        String[] matrixNames = {"r", "s", "u", "v", "w"};

        //create matrices to hold dot product and transpose matrices
        Matrix dotProduct;
        Matrix transpose;

        //iterate through list of vectors taking the dot product of each possible combination (no repeat vectors)
        for(int i = 0; i<matrixList.length; i++){

            for(int j = 0; j<matrixList.length; j++){
                if(i !=j ){
                    //take the transpose of the first matrix so that we can use matrix multiplication since dot product has similar formula
                    transpose = matrixList[i].transpose();
                    //take dot product using matrix library multiplication method
                    dotProduct = transpose.times(matrixList[j]);
                    //print the dotproduct to a file using the names of the matrices used in calculation and the type of operation
                    //in this case we use 'D' for dot product
                    printToFile(dotProduct, (matrixNames[i]+matrixNames[j]),'D');
                }
            }
        }

        //take the transpose of each matrix from part 1 and put the transpose back into itself using library method "transpose"
        libMatrix1 = libMatrix1.transpose();
        libMatrix2 = libMatrix2.transpose();
        libMatrix3 = libMatrix3.transpose();
        libMatrix4 = libMatrix4.transpose();

        //print each transpose matrix to its own file using its matrix number and 'T' for its operation
        printToFile(libMatrix1, "1", 'T');
        printToFile(libMatrix2, "2", 'T');
        printToFile(libMatrix3, "3", 'T');
        printToFile(libMatrix4, "4", 'T');


        

    
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
        
            
        }else if(matrix.equalsIgnoreCase("r")||matrix.equalsIgnoreCase("s")||matrix.equalsIgnoreCase("u")||matrix.equalsIgnoreCase("v")||matrix.equalsIgnoreCase("w")){

            //instantiate the array with the amount of cols and rows specific to this matrix file
            filledMatrix = new double[2][1];

            //read elements in from file into the 2D array
            for(int i=0; i<filledMatrix.length; i++){
                for(int j=0; j<filledMatrix[0].length; j++){
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
    String fileName = "jfuentes_p7_out"+ operation + matrixName + ".txt";

        File file = new File(fileName);
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
