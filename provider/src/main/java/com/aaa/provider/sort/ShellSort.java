package com.aaa.provider.sort;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/7/22
 */

import java.util.Arrays;

/**
 * 希尔排序（最小增量排序）
 * 基本思想：
 * 算法先将要排序的一组数按某个增量 d（n/2,n为要排序数的个数）分成若干组，
 * 每组中记录的下标相差 d.对每组中全部元素进行直接插入排序，
 * 然后再用一个较小的增量（d/2）对它进行分组，
 * 在每组中再进行直接插入排序。
 * 当增量减到 1时，进行直接插入排序后，排序完成。
 *
 * @author 王俊
 *
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] a={49,38,65,95,25,53,51};
        System.out.println(Arrays.toString(a));
        System.out.println("=======================");
        sort(a);
    }
    /**
     * 用希尔算法进行排序
     * @param a 需要排序的数组
     */
    private static void sort(int[] a) {
        double d1=a.length;
        int temp=0
                ;

        //开始循环
        while(true){
            //ceil 则是不小于他的最小整数
            //每循环一次,增量就减半向上取整
            d1=Math.ceil(d1/2);
            int d=(int)d1;
            //开始进行遍历数组分割出来的第一部分
            for(int x=0;x<d;x++){
                //
                System.out.println("-------"+d+"--------");
                for(int i=(x+d);i<a.length;i+=d){
                    int j=(i-d);
                    temp=a[i];
                    System.out.println("i=="+i);
                    System.out.println("temp=="+temp);
                    System.out.println("j=="+j);
                    System.out.println("a[j]=="+a[j]);
                    // TODO: 2019/7/22  但是采用if也是可以的 j-=d
                    for(;j>=0 && temp<a[j];j-=d){
                        a[j+d]=a[j];
                    }
                    a[j+d]=temp;
                }
                System.out.println(Arrays.toString(a));
            }
            //当d==1的时候跳出死循环
            if(d==1){
                break;
            }
        }

        //System.out.println(Arrays.toString(a));
    }

}

