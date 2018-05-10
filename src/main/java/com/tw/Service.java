package com.tw;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: student-grade-command-basic-java
 * @description: 服务类 具体数据的处理
 * @author: liust
 * @create: 2018-05-08 22:35
 **/
public class Service {
    //添加学生信息具体操作
    public Map<String, Object> addStudentInfo(Student student, Map<String, Object> mapStudentsInfo) {
        mapStudentsInfo.put(student.getId(), student);
        List<Double> doubleList = (List<Double>) mapStudentsInfo.get("sumList");
        mapStudentsInfo.put("sumList", Utils.getInstance().addToList(student.getSummary(), doubleList));
        return mapStudentsInfo;
    }

    public Student judgeStudentInfo(String studentInfo) {
        Student student = new Student();
        String[] studentArray = studentInfo.split(",");
        if (studentArray.length != 6) return null;
        if (judgeStuNameAndIdIsRight(studentArray, student) != null) return judgeStuScoreIsRight(studentArray, student);
        else return null;
    }

    //学生姓名和id处理
    public Student judgeStuNameAndIdIsRight(String[] studentArray, Student student) {
        if (judgeNameOrId("^[\u4e00-\u9fa5_a-zA-Z]+$", studentArray[0]))
            student.setName(studentArray[0]);
        else return null;
        if (judgeNameOrId("^[0-9]{10}$", studentArray[1])) student.setId(studentArray[1]);
        else return null;
        return student;
    }

    //学生分数处理
    public Student judgeStuScoreIsRight(String[] studentArray, Student student) {
        Set<String> stringSet = new HashSet<>();//判断是否重复添加或者少添加
        for (int i = 2; i < 6; i++) {
            String[] studentScore = studentArray[i].split(":");
            double score = Double.parseDouble(studentScore[1]);
            if(score<0||score>100)return null;
            switch (studentScore[0]) {
                case "math":
                    student.setMath(score);
                    stringSet.add(studentScore[0]);
                    break;
                case "chinese":
                    student.setChinese(score);
                    stringSet.add(studentScore[0]);
                    break;
                case "english":
                    student.setEnglish(score);
                    stringSet.add(studentScore[0]);
                    break;
                case "programming":
                    student.setProgramming(score);
                    stringSet.add(studentScore[0]);
                    break;
                default:
                    return null;
            }
        }
        if (stringSet.size() != 4) return null;
        return student;
    }

    public boolean showStudentsByIdIsRight(String IDs, Map<String, Object> mapStudentsInfo) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\n成绩单\n" +
                "姓名|数学|语文|英语|编程|平均分|总分\n" +
                "========================\n");
        String[] strings = IDs.split(",");
        for (String id : strings) {
            if (!judgeNameOrId("^[0-9]{10}$", id)) return false;
            Student student = (Student) mapStudentsInfo.get(id);
            stringBuffer.append(student.toString());
        }
        List<Double> sumList = (List<Double>) mapStudentsInfo.get("sumList");
        stringBuffer.append("========================\n" +
                "全班总分平均数：" + Utils.getInstance().listToAveraging(sumList) +
                "\n全班总分中位数：" + Utils.getInstance().GetMedian(sumList));
        System.out.println(stringBuffer);
        return true;
    }

    //判断姓名或者id是否正确
    public boolean judgeNameOrId(String regex, String NameOrId) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(NameOrId);
        return m.find();
    }
}
