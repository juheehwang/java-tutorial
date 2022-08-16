package com.hjh.practice1;

public class CountOfSumSpecificTargetNum {

  /**
   * 자연수 N은 몇 개의 연속된 자연수의 합으로 나타낼수 있는가 ?
   * <p>
   * EX) 15 [{1,2,3,4,5}, {4,5,6}, {7,8}, {10}] = 4개
   */
  public static void main(String[] args) {
    int num = 20;
    countSums(num);
  }


  public static void countSums(int num) {
    int count = 1;
    int sum = 0;
    int[] arr = new int[num];

    for (int i = 1; i <arr.length; i++) {
      int j = i;
      while (sum <= num) {
          if(num == sum){
            count++;
            break;

          }
        sum+=j;
          j++;
      }
      sum = 0;
    }
      System.out.println(count);
    }
  }
