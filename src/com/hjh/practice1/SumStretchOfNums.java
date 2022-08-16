package com.hjh.practice1;

import java.util.HashMap;
import java.util.Map;

public class SumStretchOfNums {

  private final static String sampleString = "12345";
  private final static int sampleInt = 1234;


  public static void main(String[] args) {
    int[] numList = new int[]{2,1,4,5,6,9,3};
    sumNumsBetweenStartToEndIndex(numList,2,4);
  }


  public static void sumNumsBetweenStartToEndIndex(int[] numList,int start,int end){

   int result = 0;
   for(int i = start-1; i<end;i++){
     result +=numList[i];
   }

    System.out.println(result);
  }
}
