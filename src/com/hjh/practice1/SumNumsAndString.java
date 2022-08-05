package com.hjh.practice1;

public class SumNumsAndString {

  private final static String sampleString = "12345";
  private final static int sampleInt = 1234;


  public static void main(String[] args) {

    sum(sampleString);
    sumInt();
  }


  public static int sum(String v){
    char[] stringToChar = v.toCharArray();
    int result=0;
    for (char c : stringToChar) {
      result = c - '0' + result;
    }
    return result;
  }

  public static int sumInt(){
    String st = String.valueOf(sampleInt);
    return sum(st);

  }
}
