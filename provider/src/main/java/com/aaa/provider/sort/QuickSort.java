package com.aaa.provider.sort;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/7/20
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr={9,8,1,3,5,6,1,0};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序后:");
        for (int i : arr) {
            System.out.print(i);
        }
    }
    public static void quickSort(int[] arr,int low,int high){
         if(low<high){
              // 找寻基准数据的正确索引
             int index = getIndex(arr, low, high);
             // 进行迭代对index之前和之后的数组进行相同的操作使整个数组变成有序

             //递归调用左半数组
             quickSort(arr, 0, index - 1);
             //递归调用右半数组
             quickSort(arr, index + 1, high);
         }
    }
    public static int  getIndex(int[] arr,int low,int high){
        //设置比较基数
        int temp=arr[low];
        while(low<high){
            //首先是队尾开始 <---、若是大于基数 则 下标往前走
             while (low<high && arr[high]>=temp){
                 high--;
             }
             //队尾元素小于基数则交换位置
             arr[low]=arr[high];
            //其次是队头开始 --->、若是大于基数 则 下标往后走
            while (low<high && arr[low]<=temp){
                low++;
            }
            //队头元素大于基数则交换位置
            arr[high]=arr[low];
        }
       // 跳出循环时low和high相等,此时的low或high就是tmp的正确索引位置
        // 由原理部分可以很清楚的知道low位置的值并不是tmp,所以需要将tmp赋值给arr[low]
         arr[low] = temp;
        // 返回tmp的正确位置
        return low;
    }
}