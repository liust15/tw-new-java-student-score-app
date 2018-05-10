package com.tw;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.doubleThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LibraryTest {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    Service service = new Service();
    CommandInput commandInput = new CommandInput();

    @Test
    public void testSomeLibraryMethod() {
        Library classUnderTest = new Library();
        assertTrue("someLibraryMethod should return 'true'", classUnderTest.someLibraryMethod());
    }

    @Test
    public void testMockClass() throws Exception {
        // you can mock concrete classes, not only interfaces
        LinkedList mockedList = mock(LinkedList.class);
        // stubbing appears before the actual execution
        String value = "first";
        when(mockedList.get(0)).thenReturn(value);
        assertEquals(mockedList.get(0), value);
    }

    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

    public String systemOut() {
        return outContent.toString();
    }

    public Map<String, Object> getDate() {
        String[] studentsInfo = {
                "张三,1507270110,math:89,english:100,chinese:80,programming:79",
                "李四,1507270111,math:89,english:90,chinese:91,programming:90",
                "王五,1507270112,math:80,english:81,chinese:82,programming:81",
                "王六,1507270113,math:78,english:80,chinese:82,programming:80"};
        Map<String, Object> studentList = new HashMap<>();
        List<Double> doubleList = new ArrayList<>();
        studentList.put("sumList", doubleList);//学生总分数
        for (String s : studentsInfo) {
            service.addStudentInfo(service.judgeStudentInfo(s), studentList);
        }
        return studentList;
    }

    @Test
    public void showMainMenuTest() {
        Library.buildMainMenuString();
        assertThat(systemOut(), is("1. 添加学生\n" +
                "2. 生成成绩单\n" +
                "3. 退出\n" +
                "请输入你的选择（1～3）：" + "\n"));
    }


    @Test
    public void addStudentInfoInputTest() {
        String rightStudentInfo = "张三,1507270110,math:90,english:79,chinese:80,programming:79";
        String expectedResult1 = "学生张三的成绩被添加\n";
        Map<String, Object> studentList = new HashMap<>();
        List<Double> doubleList = new ArrayList<>();
        studentList.put("sumList", doubleList);//学生总分数
        commandInput.addStudentInfoInput(rightStudentInfo, studentList);
        assertThat(systemOut().endsWith(expectedResult1), is(true));

        String inputFalse1 = "张三,1507270110,";
        String inputFalse2 = "张三,1507270110,math:90";
        String inputFalse3 = "张三,1507270110,math:90,english:79,chinese:80,programming:179";
        String inputFalse4 = "张三,150727011,math:90,english:79,chinese:80,programming:79";
        String inputFalse5 = "122,1507270110,math:90,english:79,chinese:80,programming:79";
        String expectedResult2 = "请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：\n";
        commandInput.addStudentInfoInput(inputFalse1, studentList);
        assertThat(systemOut().endsWith(expectedResult2), is(true));
        commandInput.addStudentInfoInput(inputFalse2, studentList);
        assertThat(systemOut().endsWith(expectedResult2), is(true));
        commandInput.addStudentInfoInput(inputFalse3, studentList);
        assertThat(systemOut().endsWith(expectedResult2), is(true));
        commandInput.addStudentInfoInput(inputFalse4, studentList);
        assertThat(systemOut().endsWith(expectedResult2), is(true));
        commandInput.addStudentInfoInput(inputFalse5, studentList);
        assertThat(systemOut().endsWith(expectedResult2), is(true));
    }

    @Test
    public void judgeStudentInfoTest() {
        String studentInfo = "张三,1507270110,math:90,english:79,chinese:80,programming:79";
        Student student = new Student();
        student.setName("张三");
        student.setId("1507270110");
        student.setMath(90);
        student.setEnglish(79);
        student.setChinese(80);
        student.setProgramming(79);
        student.setSummary(328);
        assertEquals(student, service.judgeStudentInfo(studentInfo));
    }

    @Test
    public void generatorReportTest() {
        String studentsId1 = "1507270110,1507270111,1507270112";
        commandInput.generatorReport(studentsId1, getDate());
        String expectedResult1 = "\n成绩单\n" +
                "姓名|数学|语文|英语|编程|平均分|总分\n" +
                "========================\n" +
                "张三|89.0|80.0|100.0|79.0|87.0|348.0\n" +
                "李四|89.0|91.0|90.0|90.0|90.0|360.0\n" +
                "王五|80.0|82.0|81.0|81.0|81.0|324.0\n" +
                "========================\n" +
                "全班总分平均数：338.0\n" +
                "全班总分中位数：336.0\n";
        assertThat(systemOut(), is(expectedResult1));

        String studentsId2 = "1507270110,15072";
        commandInput.generatorReport(studentsId2, getDate());
        String expectedResult2 = "请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：\n";
        assertThat(systemOut().endsWith(expectedResult2), is(true));
    }
}
