package com.aaa.provider.sort;

import org.bouncycastle.math.ec.FixedPointPreCompInfo;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/7/20
 */
public class MaoPao {
    public static void main(String[] args) {
        Integer[] arr={9, 4, 7, 6, 8, 5, 9};
        for (Integer integer : arr) {
            System.out.print(integer);
        }
        System.out.println("排序后");
        //外层循环 倒序（只是控制次数而已）
        for (int i = arr.length-1; i >0 ; i--) {
            // 同等于正序的 j < arr.length -i -1
            for (int j = 0; j < i; j++) {
                if(arr[j]<arr[j+1]){
                    //定义临时变量
                    int temp=arr[j];
                    //交换位置
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
        for (Integer integer : arr) {
            System.out.print(integer);
        }
        System.out.println();
    }

}
