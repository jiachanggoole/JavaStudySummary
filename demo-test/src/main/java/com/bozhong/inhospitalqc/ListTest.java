package com.bozhong.inhospitalqc;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JiaChang
 * @date 2019-04-19
 */
public class ListTest {

    public static void main(String[] args) {

        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);

        List<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(3);

        list1.retainAll(list2);


        System.out.println(JSON.toJSONString(list1));
        System.out.println(JSON.toJSONString(list2));

    }

}
