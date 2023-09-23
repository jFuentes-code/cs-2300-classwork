//import libraries
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class jfuentes_p1 {
    public static void main(String[] args)throws IOException {

        //creating the size variables for my name
        int firstName = 6;
        int lastName = 7;
        
        //createing 2-d arrays that will act was matrices
        double[][] matrix1 = new double[lastName][firstName];

        double[][] matrix2 = new double[lastName][firstName];

        double[][] matrix3 = new double[2][4];

        double[][] matrix4 = new double[4][2];

        double counter = 1;

        //filling mat1
        for(int i=0; i<lastName; i++){
            for (int j=0; j<firstName; j++){
                matrix1[i][j] = (int)counter;
                counter++;
                
            }
            
        }

        //filling mat2
        counter = 2;
        for(int i=0; i<firstName; i++){
            for (int j=0; j<lastName; j++){
                matrix2[j][i] = (int)counter;
                counter+= 3;
            }
        }

        //filling mat3
        counter = 10;
        for(int i=0; i<2; i++){
            for (int j=0; j<4; j++){
                matrix3[i][j] = (int)counter;
                counter-=2;
                
            }
            
        }

        //filling mat4
        counter = -6;
        for(int i=0; i<2; i++){
            for (int j=0; j<4; j++){
                matrix4[j][i] = counter;
                counter = counter+Math.abs(counter*(1.5));
                
            }
            
        }

        //print all arrays to a file using the printToFile method
        printToFile(matrix1, lastName, firstName, "jfuentes_mat1.txt");
        printToFile(matrix2, lastName, firstName, "jfuentes_mat2.txt");
        printToFile(matrix3, 2, 4, "jfuentes_mat3.txt");
        printToFile(matrix4, 4, 2, "jfuentes_mat4.txt");

        
    }

    //method to print a matrix to a specified file name using its num of rows and cols
    public static void printToFile(double[][] matrix, int row, int col, String fileName)throws IOException{

        //creates file using specified file name
        File file = new File(fileName);
        //Opens file to be written to 
		PrintWriter outputFile = new PrintWriter (file);

        //if the matrix contains only ints print to file without decimal places
        if((matrix[row-1][col-1]%1)==0){

            //iterate through the matrix
            for(int i=0; i<row; i++){
                for (int j=0; j<col; j++){
                    //print to the file without decimal places giving 5 spaces to each element
                    outputFile.printf("%-5.0f",matrix[i][j]);

                }
                outputFile.println("");
            }
        }//else if the matrix contains elements that have decimals print them with decimals
        else{
            for(int i=0; i<row; i++){
                for (int j=0; j<col; j++){
                    //print elements to file with 2 decimal places and 8 spaces
                    outputFile.printf("%-8.2f",matrix[i][j]);

                }
                outputFile.println("");
            }
        }
		//close file to write
        outputFile.flush();
		outputFile.close();
    }
}
    