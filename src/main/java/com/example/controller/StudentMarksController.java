package com.example.controller;


import com.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentMarksController {

    @Autowired
    private StudentMarksService studentMarksService;

    @PostMapping("/rank")
    public Map<Integer, Integer> rankStudents(@RequestBody int[] marks) {
        return studentMarksService.rankStudents(marks);
    }

    @GetMapping("/pass/{studentIndex}")
    public boolean isPass(@RequestBody int[] marks, @PathVariable int studentIndex) {
        return studentMarksService.isPass(marks, studentIndex);
    }
}

