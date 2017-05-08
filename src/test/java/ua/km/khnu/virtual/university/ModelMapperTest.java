package ua.km.khnu.virtual.university;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import ua.km.khnu.virtual.university.config.GeneralConfiguration;
import ua.km.khnu.virtual.university.model.Group;
import ua.km.khnu.virtual.university.model.Specialty;
import ua.km.khnu.virtual.university.model.SubjectInstance;
import ua.km.khnu.virtual.university.transfare.CreateSpecialtyForm;
import ua.km.khnu.virtual.university.transfare.CreateSubjectInstanceForm;
import ua.km.khnu.virtual.university.transfare.GroupForm;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Year;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * @author igorek2312
 */
@RunWith(JUnit4.class)
public class ModelMapperTest {

    private ModelMapper modelMapper;

    @Before
    public void setUp() throws Exception {
        GeneralConfiguration configuration = new GeneralConfiguration();
        modelMapper = configuration.modelMapper();
    }

    @Test
    public void testMap() throws Exception {
        CreateSpecialtyForm form = new CreateSpecialtyForm();
        form.setName("foo");
        form.setFacultyId(123);
        Specialty specialty = modelMapper.map(form, Specialty.class);
        assertEquals("foo", specialty.getName());
        assertNull(specialty.getId());
    }

    @Test
    public void testMapIgnoreMultiplyIds() throws Exception {
        CreateSubjectInstanceForm form = new CreateSubjectInstanceForm();
        form.setSubjectId(1);
        form.setGroupId(2);
        form.setControlType("foo1");
        form.setDateBegin(LocalDate.of(2017, 2, 6));
        SubjectInstance subjectInstance = modelMapper.map(form, SubjectInstance.class);
        assertNull(subjectInstance.getId());
        assertEquals("foo1", subjectInstance.getControlType());
        assertEquals(LocalDate.of(2017, 2, 6), subjectInstance.getDateBegin());
    }
}

