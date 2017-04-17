package ua.km.khnu.virtual.university.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import ua.km.khnu.virtual.university.error.NoEntityWithSuchIdCustomException;
import ua.km.khnu.virtual.university.model.Faculty;
import ua.km.khnu.virtual.university.repository.FacultyRepository;
import ua.km.khnu.virtual.university.transfare.FacultyForm;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author Igor Rybak
 */
@RunWith(MockitoJUnitRunner.class)
public class FacultyServiceTest extends AbstractServiceTest {
    private FacultyService facultyService;

    @Mock
    private FacultyRepository facultyRepository;

    @Before
    public void setUp() throws Exception {
        facultyService = new FacultyServiceImpl(facultyRepository);
    }

    @Test
    public void createFaculty() throws Exception {
        FacultyForm facultyForm = new FacultyForm();
        facultyForm.setName("foo");

        when(facultyRepository.save(any(Faculty.class))).thenAnswer(invocation -> {
            Faculty faculty = (Faculty) invocation.getArguments()[0];
            faculty.setId(1);
            return faculty;
        });

        Faculty result = facultyService.createFaculty(facultyForm);

        assertEquals(Integer.valueOf(1), result.getId());
        assertEquals("foo", result.getName());
    }

    @Test
    public void getAll() throws Exception {
        PageRequest pageable = new PageRequest(0, 20);

        Faculty faculty1 = new Faculty();
        faculty1.setId(1);
        faculty1.setName("foo1");
        Faculty faculty2 = new Faculty();
        faculty2.setId(2);
        faculty2.setName("foo2");
        PageImpl<Faculty> page = new PageImpl<>(Arrays.asList(faculty1, faculty2));
        when(facultyRepository.findAll(pageable)).thenReturn(page);

        Page<Faculty> facultiesPage = facultyService.getAll(pageable);

        assertThat(facultiesPage.getContent())
                .extracting(Faculty::getId)
                .contains(1, 2);

        assertThat(facultiesPage.getContent())
                .extracting(Faculty::getName)
                .contains("foo1", "foo2");
    }

    @Test
    public void get() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(1);
        faculty.setName("foo");
        when(facultyRepository.findOne(1)).thenReturn(faculty);
        Faculty result = facultyService.get(1);

        assertEquals(Integer.valueOf(1), result.getId());
        assertEquals("foo", result.getName());
    }

    @Test(expected = NoEntityWithSuchIdCustomException.class)
    public void throwIfGetNotExiting() {
        when(facultyRepository.findOne(1)).thenReturn(null);
        Faculty result = facultyService.get(1);
    }

    @Test
    public void update() throws Exception {
        FacultyForm facultyForm = new FacultyForm();
        facultyForm.setName("newFoo");

        Faculty faculty = new Faculty();
        faculty.setId(1);
        faculty.setName("foo");
        when(facultyRepository.findOne(1)).thenReturn(faculty);

        Faculty result = facultyService.update(facultyForm, 1);

        assertEquals(Integer.valueOf(1), result.getId());
        assertEquals("newFoo", result.getName());
    }

    @Test
    public void delete() throws Exception {
        when(facultyRepository.exists(1)).thenReturn(true);
        facultyService.delete(1);
        verify(facultyRepository).delete(1);
    }

}