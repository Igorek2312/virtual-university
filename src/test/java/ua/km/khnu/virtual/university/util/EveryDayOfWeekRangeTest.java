package ua.km.khnu.virtual.university.util;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ua.km.khnu.virtual.university.refrence.OddEvenWeek;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Igor Rybak
 */
@RunWith(value = JUnit4.class)
public class EveryDayOfWeekRangeTest {
    private LocalDate begin;
    private LocalDate end;
    private DayOfWeek dayOfWeek;

    @Before
    public void setUp() throws Exception {
        begin = LocalDate.of(2017, 2, 6);
        end = LocalDate.of(2017, 3, 6);
        dayOfWeek = DayOfWeek.WEDNESDAY;
    }

    @Test
    public void testBoth() throws Exception {
        List<LocalDate> dates = new ArrayList<>();
        EveryDayOfWeekRange range = new EveryDayOfWeekRange(begin, end, OddEvenWeek.BOTH, dayOfWeek);
        for (LocalDate current : range) {
            dates.add(current);
        }

        assertThat(dates)
                .hasSize(4)
                .containsExactly(
                        LocalDate.of(2017, 2, 8),
                        LocalDate.of(2017, 2, 15),
                        LocalDate.of(2017, 2, 22),
                        LocalDate.of(2017, 3, 1)
                );
    }

    @Test
    public void testEven() throws Exception {

        List<LocalDate> dates = new ArrayList<>();
        EveryDayOfWeekRange range = new EveryDayOfWeekRange(begin, end, OddEvenWeek.EVEN, dayOfWeek);
        for (LocalDate current : range) {
            dates.add(current);
        }

        assertThat(dates)
                .hasSize(2)
                .containsExactly(
                        LocalDate.of(2017, 2, 15),
                        LocalDate.of(2017, 3, 1)
                );
    }

    @Test
    public void testOdd() throws Exception {
        List<LocalDate> dates = new ArrayList<>();
        EveryDayOfWeekRange range = new EveryDayOfWeekRange(begin, end, OddEvenWeek.ODD, dayOfWeek);
        for (LocalDate current : range) {
            dates.add(current);
        }

        assertThat(dates)
                .hasSize(2)
                .containsExactly(
                        LocalDate.of(2017, 2, 8),
                        LocalDate.of(2017, 2, 22)
                );
    }
}