package ua.km.khnu.virtual.university;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import ua.km.khnu.virtual.university.config.GeneralConfiguration;
import ua.km.khnu.virtual.university.model.Specialty;
import ua.km.khnu.virtual.university.transfare.CreateSpecialtyForm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
}

