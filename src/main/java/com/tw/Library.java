package com.tw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {

    public boolean someLibraryMethod() {
        return true;
    }

    public static void buildMainMenuString() {
        System.out.println("1. 添加学生\n" +
                "2. 生成成绩单\n" +
                "3. 退出\n" +
                "请输入你的选择（1～3）：");
    }
    public static void main(String[] args) {
        Map<String, Object> mapStudentsInfo = new HashMap<>();
        List<Double> doubleList=new ArrayList<>();
        mapStudentsInfo.put("sumList",doubleList);//所有学生总分数
        while (true) {
            buildMainMenuString();
            String controllerInput = Utils.getInstance().scannerInput();
            new CommandInput().mainController(controllerInput, mapStudentsInfo);
        }
    }
}
