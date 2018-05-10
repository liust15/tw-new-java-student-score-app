package com.tw;

import java.util.List;
import java.util.Map;

/**
 * @program: student-grade-command-basic-java
 * @description: 命令行输入处的逻辑处理
 * @author: liust
 * @create: 2018-05-08 20:12
 **/
public class CommandInput {
    Service service = new Service();

    //主要控制器
    public void mainController(String controllerInput, Map<String, Object> mapStudentsInfo) {
        buildInfoPromptString(controllerInput);
        boolean flag;
        do {
            flag = buildEventController(controllerInput, mapStudentsInfo);
        } while (!flag);

    }

    //信息提示
    public void buildInfoPromptString(String controllerInput) {
        if ("1".equals(controllerInput)) buildStudentInfoPromptString();
        else if ("2".equals(controllerInput)) buildStudentSeqencePromptString();
        else if ("3".equals(controllerInput)) System.exit(0);
        else return;
    }

    //事件控制
    public boolean buildEventController(String controllerInput, Map<String, Object> mapStudentsInfo) {
        String studentInfoOrIDs = Utils.getInstance().scannerInput();
        if (controllerInput.equals("1"))
            return addStudentInfoInput(studentInfoOrIDs, mapStudentsInfo);
        else
            return generatorReport(studentInfoOrIDs, mapStudentsInfo);
    }

    public void buildStudentInfoPromptString() {
        System.out.println("请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：");
    }

    public boolean addStudentInfoInput(String studentInfo, Map<String, Object> mapStudentsInfo) {
        Student student = service.judgeStudentInfo(studentInfo);
        if (student != null) {
            service.addStudentInfo(student, mapStudentsInfo);
            Utils.getInstance().addToList(student.getSummary(), (List<Double>) mapStudentsInfo.get("sumList"));
            System.out.println("学生" + student.getName() + "的成绩被添加");
            return true;
        }
            System.out.println("请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：");
            return false;
    }

    public void buildStudentSeqencePromptString() {
        System.out.println("请输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：");
    }

    public boolean generatorReport(String IDs, Map<String, Object> mapStudentsInfo) {
        if(!service.showStudentsByIdIsRight(IDs, mapStudentsInfo)){
            System.out.println("请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：");
            return false;
        }
        return true;
    }
}
