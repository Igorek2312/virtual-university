package ua.km.khnu.virtual.university.web.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
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

    @Autowired
    public StudentController(GroupRepository groupRepository, StudentRepository studentRepository, RoleRepository roleRepository) {
        this.groupRepository = groupRepository;
        this.studentRepository = studentRepository;
        this.roleRepository = roleRepository;
    }

    @ModelAttribute("group")
    public Group group(@PathVariable int groupId) {
        return retrieveOneOrThrowNotFound(groupRepository::findOne, groupId, Group.class);
    }

    @ModelAttribute("student")
    public Student student(@PathVariable int groupId) {
        return new Student();
    }

    @ModelAttribute("students")
    public List<Student> students(@PathVariable int groupId, Sort sort) {
        return studentRepository.findByGroupId(groupId, sort);
    }

    @GetMapping("/groups/{groupId}/students")
    public String getStudentsByGroup() {
        return "student/students";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/groups/{groupId}/students")
    public String postStudent(
            @ModelAttribute("student") @Validated Student student,
            BindingResult result,
            @PathVariable int groupId
    ) {
        if (result.hasErrors()) {
            return "student/students";
        }

        Group group = retrieveOneOrThrowNotFound(groupRepository::findOne, groupId, Group.class);
        student.setGroup(group);
        Role role = roleRepository.findByName("ROLE_STUDENT");
        Account account = new Account();
        account.getRoles().add(role);
        student.setAccount(account);

        studentRepository.save(student);
        return "redirect:/groups/" + groupId + "/students";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/groups/{groupId}/delete-student/{studentId}")
    public String deleteStudent(
            @PathVariable int groupId,
            @PathVariable int studentId
    ) {
        studentRepository.delete(studentId);
        return "redirect:/groups/" + groupId + "/students";
    }
}
