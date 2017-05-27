package ua.km.khnu.virtual.university.refrence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * @author Igor Rybak
 */
@RunWith(JUnit4.class)
public class SemesterDateRangeTest {
    @Test
    public void testFirstSemesterDateRange() throws Exception {
        SemesterDateRange dateRange = new SemesterDateRange(2016, 1);
        assertEquals(LocalDate.of(2016, 9, 1), dateRange.getDateBegin());
        assertEquals(LocalDate.of(2016, 12, 31), dateRange.getDateEnd());
    }

    @Test
    public void testSecondSemesterDateRange() throws Exception {
        SemesterDateRange dateRange = new SemesterDateRange(2017, 2);
        assertEquals(LocalDate.of(2017, 1, 1), dateRange.getDateBegin());
        assertEquals(LocalDate.of(2017, 8, 31), dateRange.getDateEnd());
    }
}