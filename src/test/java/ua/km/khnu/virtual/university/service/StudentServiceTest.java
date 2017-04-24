package ua.km.khnu.virtual.university.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import ua.km.khnu.virtual.university.config.GeneralConfiguration;
import ua.km.khnu.virtual.university.model.Account;
import ua.km.khnu.virtual.university.model.Group;
import ua.km.khnu.virtual.university.model.Role;
import ua.km.khnu.virtual.university.model.Student;
import ua.km.khnu.virtual.university.repositories.AccountRepository;
import ua.km.khnu.virtual.university.repositories.GroupRepository;
import ua.km.khnu.virtual.university.repositories.RoleRepository;
import ua.km.khnu.virtual.university.repositories.StudentRepository;
import ua.km.khnu.virtual.university.transfare.CreateStudentForm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * @author igorek2312
 */
@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private GroupRepository groupRepository;

    private StudentService studentService;

    @Before
    public void setUp() throws Exception {
        GeneralConfiguration configuration = new GeneralConfiguration();
        ModelMapper modelMapper = configuration.modelMapper();
        studentService = new StudentServiceImpl(
                accountRepository,
                roleRepository,
                studentRepository,
                groupRepository,
                modelMapper
        );
    }

    @Test
    public void create() throws Exception {
        CreateStudentForm form = new CreateStudentForm();
        form.setFirstName("f");
        form.setMiddleName("m");
        form.setLastName("l");
        form.setDocumentNumber("123456");
        form.setRecordBookNumber("111111");
        form.setFinanceType("budget");
        form.setGroupId(1);

        when(groupRepository.exists(1)).thenReturn(true);
        when(groupRepository.getOne(1)).thenReturn(new Group());
        when(accountRepository.save(any(Account.class))).thenAnswer(invocationOnMock -> {
            Account account = invocationOnMock.getArgumentAt(0, Account.class);
            assertNull(account.getId());
            account.setId(2);
            return account;
        });
        Role role = new Role();
        role.setName("ROLE_STUDENT");
        when(roleRepository.findByName("ROLE_STUDENT")).thenReturn(role);

        when(studentRepository.save(any(Student.class))).thenAnswer(invocationOnMock -> {
            Student student = invocationOnMock.getArgumentAt(0, Student.class);
            assertNull(student.getId());
            student.setId(3);
            return student;
        });

        Student result = studentService.create(form);

        assertEquals(Integer.valueOf(2), result.getAccount().getId());
        assertEquals("f", result.getAccount().getFirstName());
        assertEquals("m", result.getAccount().getMiddleName());
        assertEquals("l", result.getAccount().getLastName());
        assertThat(result.getAccount().getRoles())
                .extracting(Role::getName)
                .contains("ROLE_STUDENT");
        assertNotNull(result.getGroup());
        assertEquals(Integer.valueOf(3), result.getId());
        assertEquals("budget", result.getFinanceType());
        assertEquals("111111", result.getRecordBookNumber());
    }
}