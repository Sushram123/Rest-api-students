package com.example.studentmarks;

import com.example.studentmarks.exception.CustomException;
import com.example.service.StudentMarksService;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class StudentMarksServiceTest {

    private final StudentMarksService studentMarksService = new StudentMarksService();

    @Test
    public void testRankStudents() {
        int[] marks = {85, 75, 95, 60, 70};
        Map<Integer, Integer> ranks = studentMarksService.rankStudents(marks);
        assertEquals(3, ranks.get(0));
        assertEquals(4, ranks.get(1));
        assertEquals(1, ranks.get(2));
        assertEquals(5, ranks.get(3));
        assertEquals(2, ranks.get(4));
    }

    @Test
    public void testRankStudentsEmptyArray() {
        int[] marks = {};
        Exception exception = assertThrows(CustomException.class, () -> {
            studentMarksService.rankStudents(marks);
        });
        assertEquals("Marks array cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testIsPass() {
        int[] marks = {85, 75, 95, 60, 70};
        assertTrue(studentMarksService.isPass(marks, 0));
        assertTrue(studentMarksService.isPass(marks, 2));
        assertFalse(studentMarksService.isPass(marks, 3));
    }

    @Test
    public void testIsPassInvalidStudentIndex() {
        int[] marks = {85, 75, 95, 60, 70};
        Exception exception = assertThrows(CustomException.class, () -> {
            studentMarksService.isPass(marks, -1);
        });
        assertEquals("Invalid student index", exception.getMessage());

        exception = assertThrows(CustomException.class, () -> {
            studentMarksService.isPass(marks, 5);
        });
        assertEquals("Invalid student index", exception.getMessage());
    }

    @Test
    public void testIsPassEmptyArray() {
        int[] marks = {};
        Exception exception = assertThrows(CustomException.class, () -> {
            studentMarksService.isPass(marks, 0);
        });
        assertEquals("Marks array cannot be null or empty", exception.getMessage());
    }
}
