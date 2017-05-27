package ua.km.khnu.virtual.university.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;
import ua.km.khnu.virtual.university.model.SubjectInstance;
import ua.km.khnu.virtual.university.refrence.Semester;
import ua.km.khnu.virtual.university.repositories.SubjectInstanceRepository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * @author Igor Rybak
 */
@RunWith(MockitoJUnitRunner.class)
public class SubjectInstanceServiceTest {
    private SubjectInstanceService subjectInstanceService;

    @Mock
    private SubjectInstanceRepository subjectInstanceRepository;

    @Before
    public void setUp() throws Exception {
        subjectInstanceService = new SubjectInstanceServiceImpl(
                subjectInstanceRepository,
                null,
                null
        );
    }

    @Test
    public void getSemesters() throws Exception {
        SubjectInstance subjectInstance1 = new SubjectInstance(LocalDate.of(2016, 9, 1));
        SubjectInstance subjectInstance2 = new SubjectInstance(LocalDate.of(2017, 2, 10));
        SubjectInstance subjectInstance3 = new SubjectInstance(LocalDate.of(2017, 2, 5));
        List<SubjectInstance> subjectInstances = Arrays.asList(subjectInstance3, subjectInstance2, subjectInstance1);
        when(subjectInstanceRepository.findByGroupId(anyInt(), any(Sort.class))).thenReturn(subjectInstances);
        List<Semester> semesters = subjectInstanceService.getSemesters(1);

        assertThat(semesters)
                .extracting(Semester::getYear)
                .containsExactly(2017, 2016);

        assertThat(semesters)
                .extracting(Semester::getSemesterNumber)
                .containsExactly(2, 1);

    }

}