package ua.km.khnu.virtual.university;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ua.km.khnu.virtual.university.transfare.CreateSubjectInstanceForm;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

/**
 * @author Igor Rybak
 */
@RunWith(JUnit4.class)
public class JSR310Test {

    @Test
    public void test() throws Exception {
        String s = LocalDate.parse("2017-06-12").toString();

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        String content = "{\n" +
                "  \"controlType\": \"курсовий проект\",\n" +
                "  \"dateBegin\": \"2017-02-06\",\n" +
                "  \"dateEnd\": \"2017-06-12\",\n" +
                "  \"groupId\": 1,\n" +
                "  \"hours\": 72,\n" +
                "  \"subjectId\": 1,\n" +
                "  \"subjectType\": \"звичайна\"\n" +
                "}";

        CreateSubjectInstanceForm form = mapper.readValue(content, CreateSubjectInstanceForm.class);
        assertEquals(2017, form.getDateBegin().getYear());
        assertEquals(2, form.getDateBegin().getMonth().getValue());
        assertEquals(6, form.getDateBegin().getDayOfMonth());
    }
}
