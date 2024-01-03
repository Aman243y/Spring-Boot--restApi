package com.Aman.RestDemo.Rest;

import com.Aman.RestDemo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")

public class StudentRestController {
    List<Student> students;
    //load data
    @PostConstruct
    public void loadData(){
        students= new ArrayList<>();
        students.add(new Student(101,"Aman","yadav","aman234@gmail.com"));
        students.add(new Student(102,"Amoge","asmita","amogeasmita@gmail.com"));
        students.add(new Student(103,"tanuj","tiwari","tanuj@gmail.com"));
        students.add(new Student(104,"bhalu","singh","amogeasmita@gmail.com"));
        students.add(new Student(105,"suraj","singh","suraj@gmail.com"));




    }


    @GetMapping("/hello")
    public String hello(){
        return "hello";

    }


    @GetMapping("/students")
    public List<Student> getStudents(){
//        List<Student> students= new ArrayList<>();
//        students.add(new Student(101,"Aman","yadav","aman234@gmail.com"));
//        students.add(new Student(101,"Amoge","asmita","amogeasmita@gmail.com"));
//        students.add(new Student(101,"tanuj","tiwari","tanuj@gmail.com"));
//        students.add(new Student(101,"bhalu","singh","amogeasmita@gmail.com"));
       return students;



    }
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
//        List<Student> students= new ArrayList<>();
//        students.add(new Student(101,"Aman","yadav","aman234@gmail.com"));
//        students.add(new Student(101,"Amoge","asmita","amogeasmita@gmail.com"));
//        students.add(new Student(101,"tanuj","tiwari","tanuj@gmail.com"));
//        students.add(new Student(101,"bhalu","singh","amogeasmita@gmail.com"));

        //custom exception
        if(studentId >=students.size()){
            throw new StudentNotFoundException("Student not found with studentId"+" " +studentId);
        }
        return students.get(studentId);



    }
    //create exception handler method
    @ExceptionHandler
    ResponseEntity<StudentErrorRespones> exceptionHandler(StudentNotFoundException vada){
        //error message banao, Exception ko dur bhagao
        StudentErrorRespones error = new StudentErrorRespones();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(vada.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);


    }
}
