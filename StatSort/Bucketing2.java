import java.time.LocalTime;
import java.util.Arrays;
import java.util.*;
import java.util.Random;

public class Bucketing2 {
   public static double parta,partb,partc,zscore,sd; 
   public static int bucketValue;
   public static double stdev = 5;
   public static double mean = 10;
   public static int upperLimit = 50000000;
   public static int buckets = 10;
   public static int elements = (int)(1.15*upperLimit);
   public static double partd = 5.3;
   public static double [] intBucket = new double[upperLimit];
   public static double [][] bucketing = new double [10][upperLimit/4];
   public static int [] bucketCounter = new int[10]; 
	public static void main(String[] args) {
   //creating an array that fits a normal distribution curve given the standard deviation and mean
        Random r = new Random();
        mean = 10; 
        for (int i = 0; i < upperLimit; i++){
           intBucket[i] = (double)(r.nextGaussian()*stdev + mean);
          // System.out.println(intBucket[i]);
        }
        System.out.println("Start: "+LocalTime.now());
        StatSort(intBucket);
        System.out.println("End: "+LocalTime.now());
      
    }
	private static void StatSort(double [] intBucket) {
        for (int i = 0; i < upperLimit; i++){
            double iValue = intBucket[i];
   //calculating the z-score ((x-mean)/(sd)) for iValue, which is just next element in array 
            zscore=((iValue-mean)/stdev);
            //System.out.println(zscore);
   //implementation of cubic function found that will return bucketValue with 0.3374 being the largest range, 0.0456 being the smallest range
   //and 3.474 being the highest z-score used to create function
            bucketValue = (int)(Math.floor((-0.3374*(Math.pow(zscore,3)))+(-0.0456*(Math.pow(zscore,2)))+(3.474*zscore)+partd));
   //this formula will give negative bucketValues and values greater than 9 (10)
   // need to maintain the number of buckets for space complexity
            if (bucketValue > 9){
               bucketValue = 9;
            }
            else if (bucketValue<0){
               bucketValue = 0;
            }
    //in the 2D array, iValue will be placed in its respective column (bucketValue) and added in as next index using the bucketCounter
            bucketing[bucketValue][bucketCounter[bucketValue]]=iValue; 
           // System.out.println(bucketValue);
            bucketCounter[bucketValue]=bucketCounter[bucketValue]+1;
        }
        for (int x = 0; x < bucketing.length; x++){
            sortbyColumn(bucketing,x);
        }
        for (int i = 0; i < bucketing.length; i++) {
            for (int j = 0; j < bucketing[i].length; j++){
               if (bucketing[i][j] == 0){
               }
               else{
               }
            }
        }
  }
 public static void sortbyColumn(double arr[][], int col){
      Arrays.sort(arr, new Comparator<double[]>() {
         public int compare(final double[] entry1,final double[] entry2) {
            if (entry1[col] > entry2[col])
                return 1;
            else
                return -1;
          }
        });  
    }

}
