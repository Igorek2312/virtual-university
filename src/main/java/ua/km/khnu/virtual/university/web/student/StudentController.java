package ua.km.khnu.virtual.university.web.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.km.khnu.virtual.university.model.Account;
import ua.km.khnu.virtual.university.model.Group;
import ua.km.khnu.virtual.university.model.Role;
import ua.km.khnu.virtual.university.model.Student;
import ua.km.khnu.virtual.university.repositories.AccountRepository;
import ua.km.khnu.virtual.university.repositories.GroupRepository;
import ua.km.khnu.virtual.university.repositories.RoleRepository;
import ua.km.khnu.virtual.university.repositories.StudentRepository;

import java.util.List;

import static ua.km.khnu.virtual.university.util.EntityUtils.retrieveOneOrThrowNotFound;

/**
 * @author Igor Rybak
 */
@Controller
public class StudentController {
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public StudentController(GroupRepository groupRepository, StudentRepository studentRepository, RoleRepository roleRepository, AccountRepository accountRepository) {
        this.groupRepository = groupRepository;
        this.studentRepository = studentRepository;
        this.roleRepository = roleRepository;
        this.accountRepository = accountRepository;
    }

    @ModelAttribute("group")
    public Group group(@PathVariable int groupId) {
        return retrieveOneOrThrowNotFound(groupRepository::findOne, groupId, Group.class);
    }

    @ModelAttribute("student")
    public Student student(@PathVariable int groupId) {
        return new Student();
    }

    private void initModel(@PathVariable int groupId, Sort sort, Model model) {
        List<Student> students = studentRepository.findByGroupId(groupId, sort);
        model.addAttribute("students", students);
    }

    @GetMapping("/groups/{groupId}/students")
    public String getStudentsByGroup(
            @PathVariable int groupId,
            @SortDefault(value = {"account.lastName","account.firstName"}) Sort sort,
            Model model
    ) {
        initModel(groupId, sort, model);
        return "student/students";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/groups/{groupId}/students")
    public String postStudent(
            @ModelAttribute("student") @Validated Student student,
            BindingResult result,
            @PathVariable int groupId,
            @SortDefault(value = {"account.lastName","account.firstName"}) Sort sort,
            Model model
    ) {
        if (result.hasErrors()) {
            initModel(groupId, sort, model);
            return "student/students";
        }

        Group group = retrieveOneOrThrowNotFound(groupRepository::findOne, groupId, Group.class);
        student.setGroup(group);
        Role role = roleRepository.findByName("ROLE_STUDENT");
        student.getAccount().getRoles().add(role);

        studentRepository.save(student);
        return "redirect:/groups/{groupId}/students";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/groups/{groupId}/delete-student/{studentId}")
    public String deleteStudent(
            @PathVariable int groupId,
            @PathVariable int studentId
    ) {
        Student student = retrieveOneOrThrowNotFound(studentRepository::findOne, studentId, Student.class);
        Account account = student.getAccount();
        account.getRoles().clear();
        accountRepository.save(account);
        studentRepository.delete(studentId);

        return "redirect:/groups/{groupId}/students";
    }
}
