package com.aaa.provider.sort;

import java.util.Arrays;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/7/20
 */
public class Demo01 {
    public static void main(String[] args)
    {
        int[] ins = {2,3,5,1,23,6,78,34};
        int[] ins2 = sort(ins);
        for(int in: ins2){
            System.out.println(in);
        }
    }
    public static int[] sort(int[] ins){

        for(int i=0; i<ins.length-1; i++){
            for(int j=i; j>0; j--){
                if(ins[j]<ins[j-1]){
                    int temp = ins[j-1];
                    ins[j-1] = ins[j];
                    ins[j] = temp;
                }
            }
        }
        return ins;
    }
}