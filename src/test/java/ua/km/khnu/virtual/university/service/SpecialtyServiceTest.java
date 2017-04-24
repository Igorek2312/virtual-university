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
import ua.km.khnu.virtual.university.model.Specialty;
import ua.km.khnu.virtual.university.repositories.FacultyRepository;
import ua.km.khnu.virtual.university.repositories.SpecialtyRepository;
import ua.km.khnu.virtual.university.transfare.CreateSpecialtyForm;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Igor Rybak
 */
@RunWith(MockitoJUnitRunner.class)
public class SpecialtyServiceTest {
    private SpecialtyService specialtyService;

    @Mock
    private SpecialtyRepository specialtyRepository;

    @Mock
    private FacultyRepository facultyRepository;

    @Before
    public void setUp() throws Exception {
        specialtyService = new SpecialtyServiceImpl(specialtyRepository, facultyRepository);
    }

    @Test
    public void create() throws Exception {
        when(facultyRepository.exists(2)).thenReturn(true);
        when(specialtyRepository.save(any(Specialty.class))).thenAnswer(invocation -> {
            Specialty specialty = invocation.getArgumentAt(0, Specialty.class);
            specialty.setId(1);
            return specialty;
        });

        CreateSpecialtyForm form = new CreateSpecialtyForm();
        form.setName("foo");
        form.setFacultyId(2);
        Specialty specialty = specialtyService.create(form);

        assertEquals(Integer.valueOf(1), specialty.getId());
        assertEquals("foo", specialty.getName());
        assertEquals(Integer.valueOf(2), specialty.getFaculty().getId());
    }

    @Test(expected = NoEntityWithSuchIdCustomException.class)
    public void createWithNotExistingFaculty() throws Exception {
        when(facultyRepository.exists(2)).thenReturn(false);
        CreateSpecialtyForm form = new CreateSpecialtyForm();
        form.setFacultyId(2);
        specialtyService.create(form);
    }

    @Test
    public void getByFaculty() throws Exception {
        when(facultyRepository.exists(1)).thenReturn(true);
        PageRequest pageable = new PageRequest(1, 20);
        Specialty specialty1 = new Specialty();
        specialty1.setName("foo1");
        Specialty specialty2 = new Specialty();
        specialty2.setName("foo2");
        PageImpl<Specialty> page = new PageImpl<>(Arrays.asList(specialty1, specialty2));
        when(specialtyRepository.findByFacultyId(1, pageable)).thenReturn(page);

        Page<Specialty> result = specialtyService.getByFaculty(1, pageable);

        assertThat(result.getContent())
                .extracting(Specialty::getName)
                .contains("foo1", "foo2");
    }

    @Test
    public void get() throws Exception {
        Specialty specialty = new Specialty();
        specialty.setId(1);
        specialty.setName("foo");
        when(specialtyRepository.findOne(1)).thenReturn(specialty);
        Specialty result = specialtyService.get(1);

        assertEquals(Integer.valueOf(1), result.getId());
        assertEquals("foo", result.getName());
    }

    @Test
    public void getAll() throws Exception {
        PageRequest pageable = new PageRequest(1, 20);
        Specialty specialty1 = new Specialty();
        specialty1.setName("foo1");
        Specialty specialty2 = new Specialty();
        specialty2.setName("foo2");
        PageImpl<Specialty> page = new PageImpl<>(Arrays.asList(specialty1, specialty2));
        when(specialtyRepository.findAll(pageable)).thenReturn(page);

        Page<Specialty> result = specialtyService.getAll(pageable);

        assertThat(result.getContent())
                .extracting(Specialty::getName)
                .contains("foo1", "foo2");

    }

    @Test
    public void update() throws Exception {
        Specialty specialty = new Specialty();
        specialty.setName("foo");
        when(specialtyRepository.findOne(1)).thenReturn(specialty);

        CreateSpecialtyForm form = new CreateSpecialtyForm();
        form.setName("foo1");
        Specialty result = specialtyService.update(form, 1);

        verify(specialtyRepository).save(specialty);

        assertEquals("foo1", result.getName());
    }

    @Test
    public void delete() throws Exception {
        when(specialtyRepository.exists(1)).thenReturn(true);
        specialtyService.delete(1);
        verify(specialtyRepository).delete(1);
    }

}