//import libraries
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import Jama.Matrix;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import java.awt.Font;
import javax.imageio.ImageIO;


public class jfuentes_P2 {

    

    public static void main(String[] args)throws IOException{
       

        //using getMatrix method get the matrices from each file and off of them create matrices using java library JAMA
        Matrix orderedPairs = new Matrix(getMatrix("jfuentes_P2_orderedPairs.txt"));

        Matrix xAxis = new Matrix(orderedPairs.getRowDimension(),2);
        Matrix yAxis = new Matrix(orderedPairs.getRowDimension(),1);

        for(int i=0; i<orderedPairs.getRowDimension(); i++){
            xAxis.set(i,1,orderedPairs.get(i,0));
            xAxis.set(i,0,1);
        }
        
        for(int i=0; i<orderedPairs.getRowDimension(); i++){
            yAxis.set(i,0,orderedPairs.get(i,1));

        }

        Matrix coefficientMat = xAxis.transpose().times(xAxis).inverse().times(xAxis.transpose().times(yAxis));

        
        System.out.print("hello");

        LineGraph demo = new LineGraph("Price of Power Drill vs Monthly Sales", coefficientMat);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

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

        
        //initialize the size of the 2D array with 4 rows as is required by the hill matrix
        //divide the total size of the matrix by these 4 rows to get the amount of columns
        rows = listOfValues.length/2;
        cols = 2;
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
        
        //close scanner file
		readFile.close();
        
        //return the cypher matrix from the file
        return cypherMatrix;
		
        
    }
    
}
class LineGraph extends ApplicationFrame {


    public LineGraph(String title, Matrix coefficienMatrix) {
        super(title);

        String slope = String.format("%.1f",coefficienMatrix.get(1,0));
        String intercept = String.format("%.1f",coefficienMatrix.get(0,0));

        // Create a dataset for the line equation y = -1.78x + 127.6
        XYDataset equation = DatasetUtilities.sampleFunction2D(
                x -> coefficienMatrix.get(1,0) * x + coefficienMatrix.get(0,0), -100, 100, 100, "y = "+ slope +"x + "+ intercept);

        // Create a dataset for the additional points
        DefaultXYDataset pointDataset = new DefaultXYDataset();
        double[][] points = {{25, 30, 35, 40}, {82, 75, 67, 55}};
        pointDataset.addSeries("Points", points);

        // Create the chart with the line equation
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Graph of Power Drill Price vs Monthly Sales", "Price (in dollars)", "Monthly Sales", equation);

        // Get the plot of the chart and add the points
        org.jfree.chart.plot.XYPlot plot = (org.jfree.chart.plot.XYPlot) chart.getPlot();
        plot.setDataset(1, pointDataset);
        org.jfree.chart.renderer.xy.XYItemRenderer renderer = new org.jfree.chart.renderer.xy.XYLineAndShapeRenderer(false, true);
        plot.setRenderer(1, renderer);

        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setRange(40, 90);

        NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
        xAxis.setRange(20, 50);

        Font labelFont = new Font("Arial", Font.PLAIN, 14);

        // Label the points with their values below the points
        for (int i = 0; i < points[0].length; i++) {
            double x = points[0][i];
            double y = points[1][i];
            org.jfree.chart.annotations.XYTextAnnotation annotation = new org.jfree.chart.annotations.XYTextAnnotation("(" + x + "," + y + ")", x, y - 5);
            annotation.setTextAnchor(org.jfree.ui.TextAnchor.HALF_ASCENT_CENTER);
            annotation.setFont(labelFont);
            plot.addAnnotation(annotation);
        }
        

        // Create and set up the chart panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);

        // Save the chart as an image file (PNG format)
        try {
            File imageFile = new File("jfuentes_P2_Output.png"); // Set the file name and format
            ImageIO.write(chart.createBufferedImage(800, 600), "png", imageFile); // Adjust dimensions if needed
            System.out.println("Chart saved as: " + imageFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}