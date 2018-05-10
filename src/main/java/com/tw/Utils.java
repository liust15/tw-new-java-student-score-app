package com.tw;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @program: student-grade-command-basic-java
 * @description: 工具类
 * @author: liust
 * @create: 2018-05-08 20:07
 **/
public class Utils {
    private static Utils utils = null;

    //静态工厂方法
    public static Utils getInstance() {
        if (utils == null) {
            utils = new Utils();
        }
        return utils;
    }

    public String scannerInput() {
        return new Scanner(System.in).nextLine();
    }

    public Double GetMedian(List<Double> list) {
        double middle = 0;
        int size = list.size();
        if (size != 0) {
            Double[] array = (Double[]) list.toArray(new Double[size]);
            Arrays.sort(array);
            if (size % 2 == 0) {
                middle = (array[size / 2 - 1] + array[size / 2]) / 2.0;
            } else {
                int inx = size / 2;
                middle = array[inx];
            }
        }
        return middle;
    }
    public List<Double> addToList(double dou,List<Double> doubleList){
        doubleList.add(dou);
        return doubleList;
    }
    public Double listToAveraging(List<Double> doubleList){
        int count=doubleList.size();
        if(count==0)return 0D;
        double sum=0;
        for (double dou:doubleList) {
            sum+=dou;
        }
        return sum/count;
    }
}
