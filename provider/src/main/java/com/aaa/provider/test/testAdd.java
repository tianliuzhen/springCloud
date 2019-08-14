package com.aaa.provider.test;

import java.util.*;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/7/18
 */
public class testAdd {
    public static void main(String[] args) {
     /*   List<String> actids=new ArrayList<>();
        add(actids);
        paiXu(actids);
        System.out.println(actids);*/

        List<GiftFullMonDTO> list = new ArrayList<>();
        GiftFullMonDTO giftFullMonDTO=new GiftFullMonDTO();
        giftFullMonDTO.setMon(29.00);
        giftFullMonDTO.setPid("2");
        list.add(giftFullMonDTO);
        GiftFullMonDTO giftFullMonDTO2=new GiftFullMonDTO();
        giftFullMonDTO2.setMon(22.00);
        giftFullMonDTO2.setPid("3");
        list.add(giftFullMonDTO2);

        GiftFullMonDTO giftFullMonDTO3=new GiftFullMonDTO();
        giftFullMonDTO3.setMon(27.00);
        giftFullMonDTO3.setPid("4");
        list.add(giftFullMonDTO3);
        Collection<Map> userIds =new ArrayList<>();
        getPidList(list);
//        int m = Collections.binarySearch(list, giftFullMonDTO3);
        System.out.println(list);

    }
    public static List<String>    add(List<String> actids){
        actids.add("7");
        actids.add("3");
        actids.add("2");
        actids.add("5");
        int m = Collections.binarySearch(actids, "5");
        System.out.println(m);
       return actids;
    }
    public static void paiXu(List<String> actids){

        Collections.sort(actids);
    }
    //根据mon排序
    public static    List<GiftFullMonDTO>  getPidList( List<GiftFullMonDTO> list){

        Collections.sort(list, new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                GiftFullMonDTO stu1=(GiftFullMonDTO)o1;
                GiftFullMonDTO stu2=(GiftFullMonDTO)o2;
                //按age排序
                if(stu1.getMon()>stu2.getMon()){
                    return 1;
                }else if(stu1.getMon().equals(stu2.getMon())){
                    return 0;
                }else{
                    return -1;
                }
            }
        });
        return  list;
    }
}
