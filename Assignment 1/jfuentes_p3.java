//import libraries
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;

public class jfuentes_p3 {
    public static void main(String[] args)throws IOException{
        //create boolean flags
        boolean validMatrix = false;
        boolean canMultiply = false;

         //create arrays that will have matrices read into them
        double[][] matrix1;
        double[][] matrix2;
        double[][] productMatrix;

        //create file scanner
        Scanner input = new Scanner(System.in);

        //create variables to have user input stores
        String firstMatrix = "";
        String secondMatrix = "";

        //prompt the user for input
        System.out.println("Please enter the two matrices you would like to multiply separated by a space.\n"+
                                    "for example: mat1 mat2");
        //while the userInput is not valid loop
        while(!validMatrix){
                
            //take in user input into strings
	        firstMatrix = input.next();
            secondMatrix = input.next();

            //test first string to see if it is a matrix
            validMatrix = isMatrix(firstMatrix);

            //if string is not a valid matrix then give error
            if(!validMatrix){
                System.out.println("Invalid first matrix, please enter mat1, mat2, mat3, or mat4");
            }


            //test second string to see if it is a matrix
            validMatrix = isMatrix(secondMatrix);

            //if string is not a valid matrix then give error
            if(!validMatrix){
                System.out.println("Invalid second matrix, please enter mat1, mat2, mat3, or mat4");
            }

        }

        input.close();//close scanner class

        //since matrices are valid, find the matrix file the user is looking for using getMatrix
        //then load that matrix from the file into the matrices we have ready
        matrix1 = getMatrix(firstMatrix);
        matrix2 = getMatrix(secondMatrix);


        //check the size of the matrices are the same for addability using method "isAddable" and load that into our boolean flag
        canMultiply = isMultipliable(matrix1, matrix2);

        //If the matrices are multipliable then multiply them, print the product matrix to a file, and tell the user the name of the file
        if(canMultiply){
            productMatrix = multiplyMatrices(matrix1, matrix2);

            String fileName = printToFile(productMatrix, firstMatrix, secondMatrix);

            System.out.println("Muliplication matrix printed to: "+ fileName);

        }//else give error telling user they are not the same size
        else{
            System.out.println("Could not multiply matrices, amount of coloumns in first matrix does not equal amount of rows of the second.");
        }
        
            
	    

    
    }

    //method to check if user input matches a viable matrix file
    public static boolean isMatrix(String userInput){
        //initialize matrix checker
        boolean isMatrix = false;

        //if user input matches these strings then their input matches a matrix file
        if(userInput.equalsIgnoreCase("mat1")){
            isMatrix = true;
        }else if(userInput.equalsIgnoreCase("mat2")){
            isMatrix = true;
        }else if(userInput.equalsIgnoreCase("mat3")){
            isMatrix = true;
        }else if(userInput.equalsIgnoreCase("mat4")){
            isMatrix = true;
        }

        //return whether the input matches a matrix file
        return isMatrix;
    }

    //check the size of the matrices match so they can be added
    public static boolean isMultipliable(double[][] mat1, double[][] mat2){
        boolean isMultipliable = false;

        //get the cols of the first matrix and the rows of the second
        int mat1Cols = mat1[0].length;
        int mat2Rows = mat2.length;
        
        //check if cols of first matrix equal the rows of the second because then they can be multiplied
        if(mat1Cols == mat2Rows){
            isMultipliable = true;
        }
        
        //return results of test
        return isMultipliable;
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

    //method to print a matrix to a specified file name using the base matrices to build the file name
    public static String printToFile(double[][] matrix, String matrixName1, String matrixName2)throws IOException{

         //build a filename using the last characters of the two matrices used to create the new matrix
        String fileName = "jfuentes_p3_outM"+ matrixName1.charAt(matrixName1.length()-1) + matrixName2.charAt(matrixName2.length()-1) + ".txt";

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
                outputFile.printf("%10.0f",matrix[i][j]);
                }else{
                    outputFile.printf("%10.2f",matrix[i][j]);
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

    //method to multiply two matrices
    public static double[][] multiplyMatrices(double[][] matrix1, double[][] matrix2){

        //get rows and cols of matrices
        int mat1Rows = matrix1.length;
        int mat1Cols = matrix1[0].length;

        int mat2Rows = matrix2.length;
        int mat2Cols = matrix2[0].length;

        //create a list that will hold the product of individual components before the get added
        //will only be as long as matrix1 coloumns or matrix2 rows
        double[] products = new double[mat1Cols];

        //variable for the addition of the product of individual elements
        double sum = 0;

        //create a matrix that will hold matrix1 times matrix2
        double[][] productMatrix = new double[mat1Rows][mat2Cols];

        //iterate through all matrixes multipling, adding, and inserting elements where needed
        for(int k=0; k<mat1Rows; k++){
            for(int i=0; i<mat2Cols; i++){
                for(int j=0; j<mat2Rows; j++){
                    //multiple elements and put into products array
                    products[j] = matrix2[j][i] *matrix1[k][j];
                    
                }

                sum=0; 
                //iterate through products array, adding its elements together
                for(int j=0; j<products.length; j++){
                    sum = sum +products[j];
                }
                //put the sum of products array into matrix elemment
                productMatrix[k][i] = sum;
            }
        }

        //return the product matrix
        return productMatrix;
    }
}
