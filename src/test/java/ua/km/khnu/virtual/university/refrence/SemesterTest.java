package ua.km.khnu.virtual.university.refrence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

/**
 * @author Igor Rybak
 */
@RunWith(JUnit4.class)
public class SemesterTest {

    @Test
    public void testFirstSemester() throws Exception {
        Semester semester = new Semester(LocalDate.of(2016, 9, 15));
        assertEquals(2016, semester.getYear());
        assertEquals(1, semester.getSemesterNumber());
    }

    @Test
    public void testSecondSemester() throws Exception {
        Semester semester = new Semester(LocalDate.of(2017, 1, 15));
        assertEquals(2017, semester.getYear());
        assertEquals(2, semester.getSemesterNumber());
    }
}