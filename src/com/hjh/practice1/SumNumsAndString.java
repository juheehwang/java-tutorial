package com.hjh.practice1;

import java.util.HashMap;
import java.util.Map;

public class SumNumsAndString {

  private final static String sampleString = "12345";
  private final static int sampleInt = 1234;


  public static void main(String[] args) {

//    sum(sampleString);
// sumInt();
 checkDuplicatedNum(new int[]{2,1,4,5,6,3});
  }


  public static int sum(String v){
    char[] stringToChar = v.toCharArray();
    int result=0;
    for (char c : stringToChar) {
      result = c - '0' + result;
    }
    return result;
  }


  public static void sumInt(){
    int sampleInt = 1334;
    int sum=0;

    while (sampleInt>0){
      int mok = sampleInt /10;
      int nam = sampleInt % 10;
      sum += nam;
      sampleInt = mok;
    }
    System.out.println("sum"+sum);

  }


  public static void checkDuplicatedNum(final int[] numList){
    Map<Integer, Integer> intMap = new HashMap<>();
    boolean result = false;
    int maxVal =0;
    int sumNumList = 0;
    int sumMaxValFactorial = 0;

    for(int i : numList){
      maxVal = Math.max(maxVal,i);
      sumNumList += i;
      intMap.put(i,1);
    }

    while(maxVal>0){
      sumMaxValFactorial += maxVal;
      maxVal = maxVal-1;
    }


    if(intMap.containsKey(1)&& intMap.size() == numList.length && sumNumList==sumMaxValFactorial) {
      result = true;
    }
    System.out.println(result);
  }
}
