//import libraries
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
public class jfuentes_p5 {
    public static void main(String[] args)throws IOException {
        //create boolean flags
        boolean validMatrix = false;
        double dotProduct = 0;

        //create arrays that will have matrices read into them
        double[][] matrix1;
        double[][] matrix2;

        //create file scanner
        Scanner input = new Scanner(System.in);

        //create variables to have user input stores
        String firstMatrix = "";
        String secondMatrix = "";

        //prompt the user for input
        System.out.println("Please enter the two vectors you would like to calculate the dot product of separated by a space.\n"+
                                    "for example: r s");

        //while the userInput is not valid loop
        while(!validMatrix){
                

	        //take in user input into strings
	        firstMatrix = input.next();
            secondMatrix = input.next();

            //test first string to see if it is a matrix
            validMatrix = isMatrix(firstMatrix);

            //if string is not a valid matrix then give error
            if(!validMatrix){
                System.out.println("Invalid first vector, please enter r, s, u, v, or w");
            }


            //test second string to see if it is a matrix
            validMatrix = isMatrix(secondMatrix);

            //if string is not a valid matrix then give error
            if(!validMatrix){
                System.out.println("Invalid second vector, please enter r, s, u, v, or w");
            }

        }

        input.close();//close scanner class

        //since matrices are valid, find the matrix file the user is looking for using getMatrix
        //then load that matrix from the file into the matrices we have ready
        matrix1 = getMatrix(firstMatrix);
        matrix2 = getMatrix(secondMatrix);

        //take the dot product of the matrixes using the findDotProduct method
        dotProduct = findDotProduct(matrix1, matrix2);
       
        //print the product dot product to a file, and tell the user the name of the file
        String fileName = printToFile(dotProduct, firstMatrix, secondMatrix);
        System.out.println("Dot product printed to: "+ fileName);

    }

    //method to check if user input matches a viable matrix file
    public static boolean isMatrix(String userInput){
        //initialize matrix checker
        boolean isMatrix = false;

        //if user input matches these strings then their input matches a matrix file
        if(userInput.equalsIgnoreCase("r")){
            isMatrix = true;
        }else if(userInput.equalsIgnoreCase("s")){
            isMatrix = true;
        }else if(userInput.equalsIgnoreCase("u")){
            isMatrix = true;
        }else if(userInput.equalsIgnoreCase("v")){
            isMatrix = true;
        }else if(userInput.equalsIgnoreCase("w")){
            isMatrix = true;
        }

        //return whether the input matches a matrix file
        return isMatrix;
    }


    //create matrix to return a matrix from a specified file
    public static double[][] getMatrix(String matrix )throws IOException{

        //create a filler matrix
        double[][] filledMatrix = new double[2][1];

        //put the matrix file to lower case
        matrix = matrix.toLowerCase();

        //open the specified file
        File file = new File("jfuentes_" + matrix + ".txt");
        //create scanner for the file
        Scanner readFile = new Scanner(file);
       
        //read elements in from file into the 2D array
        for(int i=0; i<filledMatrix.length; i++){
            for(int j=0; j<filledMatrix[0].length; j++){
                filledMatrix[i][j] = readFile.nextDouble();
            }
        }

        //close scanner file
		readFile.close();

        //return array that was filled by file
        return filledMatrix;
    }

    //method to print a matrix to a specified file name using the base matrices to build the file name
    public static String printToFile(double dotProduct, String matrixName1, String matrixName2)throws IOException{

        //build a filename using the last characters of the two matrices used to create the new matrix
        String fileName = "jfuentes_p5_out"+ matrixName1.charAt(matrixName1.length()-1) + matrixName2.charAt(matrixName2.length()-1) + ".txt";

        //creates file using specified file name
        File file = new File(fileName);
        //Opens file to be written to 
		PrintWriter outputFile = new PrintWriter (file);

        //if the dot product is an int print to file without decimal places else print it with 2 decimal places
        if((dotProduct%1)==0){
            outputFile.printf("%-8.0f",dotProduct);
        }else{
            outputFile.printf("%-8.2f",dotProduct);
        }
  
      
		//close file to write
        outputFile.flush();
		outputFile.close();

        //returnn the file name created and used
        return fileName;
    }

    //method to find the dot product of two matrices
    public static double findDotProduct(double[][] matrix1, double[][] matrix2){

        //get the row and col of first matrix
        int row = matrix1.length;
        int col = matrix1[0].length;

        //varaible to hold dot product
        double dotProduct = 0;

        //method that will hold the products of elements before they are added
        double[] products = new double[row];

        double sum = 0;

        //get the product of vector elements before addition step
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                products[i] = matrix2[i][j] *matrix1[i][j];
                
            }
            
        }
        //add the products of vector elements for dot product
        sum=0; 
        for(int j=0; j<products.length; j++){
            sum = sum +products[j];
        }
        dotProduct = sum;
        
        //return the dot product
        return dotProduct;
        
    }
}
