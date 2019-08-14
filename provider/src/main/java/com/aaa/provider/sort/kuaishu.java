package com.aaa.provider.sort;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/7/20
 */
public class kuaishu {
    public static void main(String[] args) {
        Integer[] arr={9, 4, 7, 6, 8, 5, 9};
        for (Integer integer : arr) {
            System.out.print(integer);
        }
        System.out.println("排序后");

        for (int i = 0; i <arr.length -1 ; i++) {
            int  min=i;
            //找出每次数组里的最小值
            for (int j = i; j < arr.length -1; j++) {
                if(arr[j+1]<arr[min]){
                    min=j+1;
                }
            }
            if(i!=min){
                int temp=arr[i];
                arr[i]=arr[min];
                arr[min]=temp;
            }
        }


        for (Integer integer : arr) {
            System.out.print(integer);
        }
    }
}
