//import libraries
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;

public class jfuentes_p6 {
    
    public static void main(String[] args)throws IOException{
       
        //create arrays that will have matrices read into them
        double[][] matrix1;
        double[][] matrix2;
        double[][] matrix3;
        double[][] matrix4;

        //since matrices are valid, find the matrix file for each matrix using getMatrix
        //then load that matrix from the file into the matrices we have ready
        matrix1 = getMatrix("mat1");
        matrix2 = getMatrix("mat2");
        matrix3 = getMatrix("mat3");
        matrix4 = getMatrix("mat4");

        //using the transpose method transpose each matrix into itself
        matrix1 = transpose(matrix1);
        matrix2 = transpose(matrix2);
        matrix3 = transpose(matrix3);
        matrix4 = transpose(matrix4);

        //print each transposed matrix to its own file using its base name
        printToFile(matrix1, "mat1");
        printToFile(matrix2, "mat2");
        printToFile(matrix3, "mat3");
        printToFile(matrix4, "mat4");
        

    
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

    //method to print a matrix to a specified file name using the base matrices names to build the file name
    public static String printToFile(double[][] matrix, String matrixName1)throws IOException{

        //build a filename using the last characters of the matrix used to create the new matrix
        String fileName = "jfuentes_p6_mat"+ matrixName1.charAt(matrixName1.length()-1) + ".txt";

        //create iterators for the matrix
        int row = matrix.length;
        int col = matrix[0].length;

        //creates file using specified file name
        File file = new File(fileName);
        //Opens file to be written to 
		PrintWriter outputFile = new PrintWriter (file);

        //iterate through the matrix printing its elements
        for(int i=0; i<row; i++){
            for (int j=0; j<col; j++){

                //if the matrix elements is an int print to file without decimal places else print it with 2 decimal places
                if((matrix[i][j]%1)==0){
                outputFile.printf("%-8.0f",matrix[i][j]);
                }else{
                    outputFile.printf("%-8.2f",matrix[i][j]);
                }
            }
            outputFile.println("");
        }
      
		//close file to write
        outputFile.flush();
		outputFile.close();

        //returnn the file name created and used
        return fileName;
    }

    //method to transpose a matrix
    public static double[][] transpose(double[][] matrix){

        //create iterators for the matrix
        int row = matrix.length;
        int col = matrix[0].length;

        //create a matrix to act as transpose of old matrix, swaps values for cols and rows
        double[][] transpose = new double[col][row];

        //iterating throught the base matrix map its values to the opposite col and row of the transpose
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                transpose[j][i] = matrix[i][j];
            }
        }

        //return the transpose matrix
        return transpose;
    }
}
