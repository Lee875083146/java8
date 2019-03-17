package com.nopainanymore.java8.Stream;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lw875
 * @create 2019-03-13 23:26
 */
public class StreamOperation {

    private static final Logger log = LoggerFactory.getLogger(StreamOperation.class);

    private static final Gson gson = new Gson();

    private final static String MALE = "男";

    private final static String FEMALE = "女";


    public static void main(String[] args) {
        List<Student> studentList = generateStudentList(1);
//        filter(studentList);
//        map(studentList);
//        mapToInt(studentList);


//        List<Student> studentList1 = generateStudentList(2);
//        List<List<Student>> classList = new ArrayList<>();
//        classList.add(studentList);
//        classList.add(studentList1);
//        flatMap(classList);

//        peek(studentList);

//        studentList.addAll(studentList1);
//        distinct(studentList);

//        sorted(studentList);

//        limit(studentList);

        skip(studentList);
    }

    private static List<Student> generateStudentList(Integer classId) {
        List<Student> studentList = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            Student student;
            if (i % 2 == 0) {
                student = new Student("a" + String.valueOf(i), 20, MALE, 1400 + i, classId);
            } else {
                student = new Student("a" + String.valueOf(i), 21, FEMALE, 1400 + i, classId);
            }
            studentList.add(student);
        }
        return studentList;
    }

    private static void filter(List<Student> studentList) {
        log.info("StreamOperation- filter- before :{}", gson.toJson(studentList));
        List<Student> afterFilter = studentList
                .stream()
                .filter(student -> MALE.equals(student.getSex()))
                .collect(Collectors.toList());
        log.info("StreamOperation- filter- after:{}", gson.toJson(afterFilter));
    }

    private static void map(List<Student> studentList) {
        log.info("StreamOperation- map- before:{}", gson.toJson(studentList));
        List<String> nameString = studentList
                .stream()
                .map(Student::getName)
                .collect(Collectors.toList());
        log.info("StreamOperation- map- after:{}", gson.toJson(nameString));
    }

    private static void mapToInt(List<Student> studentList) {
        log.info("StreamOperation- mapToInt- before:{}", gson.toJson(studentList));
        Double avgAge = studentList
                .stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0);
        log.info("StreamOperation- mapToInt- result:{}", avgAge);
    }

    private static void flatMap(List<List<Student>> classList) {
        log.info("StreamOperation- flatMap- before:{}", gson.toJson(classList));
        List<Student> students = classList
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        log.info("StreamOperation- flatMap- after:{}", gson.toJson(students));
    }

    private static void peek(List<Student> studentList) {
        log.info("StreamOperation- peek- before:{}", gson.toJson(studentList));
        Long numOfMale = studentList
                .stream()
                .peek(student -> log.info("first peek Student:{}", gson.toJson(student)))
                .filter(student -> MALE.equals(student.getSex()))
                .unordered()
                .peek(student -> log.info("second peek Student:{}", gson.toJson(student)))
                .count();
        log.info("StreamOperation- peek- numOfMale:{}", numOfMale);
    }

    private static void distinct(List<Student> studentList) {
        log.info("StreamOperation- distinct- before :{}", gson.toJson(studentList));
        List<Integer> classIdList = studentList
                .stream()
                .map(Student::getClassId)
                .distinct()
                .collect(Collectors.toList());
        log.info("StreamOperation- distinct- classIdList :{}", gson.toJson(classIdList));
    }

    private static void sorted(List<Student> studentList) {
        log.info("StreamOperation- sorted- before:{}", gson.toJson(studentList));
        List<Student> sorted = studentList
                .stream()
                .sorted((o1, o2) -> o2.getStuId() - o1.getStuId())
                .collect(Collectors.toList());
        log.info("StreamOperation- sorted- :{}", gson.toJson(sorted));
    }

    private static void limit(List<Student> studentList) {
        log.info("StreamOperation- limit- before:{}", gson.toJson(studentList));
        List<Student> limit = studentList
                .stream()
                .limit(2)
                .collect(Collectors.toList());
        log.info("StreamOperation- limit- after:{}", gson.toJson(limit));
    }

    private static void skip(List<Student> studentList) {
        log.info("StreamOperation- skip- before:{}", gson.toJson(studentList));
        List<Student> skip = studentList
                .stream()
                .skip(1)
                .collect(Collectors.toList());
        log.info("StreamOperation- skip- after:{}", gson.toJson(skip));
    }

}
