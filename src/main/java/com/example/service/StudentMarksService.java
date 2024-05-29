package com.example.service;



import com.example.studentmarks.exception.CustomException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentMarksService {

    public Map<Integer, Integer> rankStudents(int[] marks) {
        if (marks == null || marks.length == 0) {
            throw new CustomException("Marks array cannot be null or empty");
        }

        int[] sortedMarks = marks.clone();
        Arrays.sort(sortedMarks);
        Map<Integer, Integer> rankMap = new HashMap<>();

        for (int i = 0; i < marks.length; i++) {
            for (int j = 0; j < sortedMarks.length; j++) {
                if (marks[i] == sortedMarks[j]) {
                    rankMap.put(i, marks.length - j);
                    break;
                }
            }
        }

        return rankMap;
    }

    public boolean isPass(int[] marks, int studentIndex) {
        if (marks == null || marks.length == 0) {
            throw new CustomException("Marks array cannot be null or empty");
        }
        if (studentIndex < 0 || studentIndex >= marks.length) {
            throw new CustomException("Invalid student index");
        }

        int totalMarks = 0;
        int minMarks = Integer.MAX_VALUE;
        int maxMarks = Integer.MIN_VALUE;

        for (int mark : marks) {
            totalMarks += mark;
            if (mark < minMarks) {
                minMarks = mark;
            }
            if (mark > maxMarks) {
                maxMarks = mark;
            }
        }

        double averageMarks = (totalMarks - minMarks - maxMarks) / (double) (marks.length - 2);

        return marks[studentIndex] >= averageMarks;
    }
}
