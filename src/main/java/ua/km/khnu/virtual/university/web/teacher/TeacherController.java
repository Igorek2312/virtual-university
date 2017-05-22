package ua.km.khnu.virtual.university.web.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.km.khnu.virtual.university.model.Account;
import ua.km.khnu.virtual.university.model.Role;
import ua.km.khnu.virtual.university.model.Teacher;
import ua.km.khnu.virtual.university.repositories.AccountRepository;
import ua.km.khnu.virtual.university.repositories.RoleRepository;
import ua.km.khnu.virtual.university.repositories.TeacherRepository;
import ua.km.khnu.virtual.university.util.EntityUtils;

import static ua.km.khnu.virtual.university.util.EntityUtils.retrieveOneOrThrowNotFound;

/**
 * @author Igor Rybak
 */
@Controller
public class TeacherController {
    private final TeacherRepository teacherRepository;
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public TeacherController(TeacherRepository teacherRepository, RoleRepository roleRepository, AccountRepository accountRepository) {
        this.teacherRepository = teacherRepository;
        this.roleRepository = roleRepository;
        this.accountRepository = accountRepository;
    }

    @ModelAttribute("teacher")
    public Teacher teacher() {
        Teacher teacher = new Teacher();
        teacher.setAccount(new Account());
        return teacher;
    }

    @ModelAttribute("teachers")
    public Page<Teacher> teachers(Pageable pageable) {
        return teacherRepository.findAll(pageable);
    }

    @GetMapping("/teachers")
    public String getTeachers() {
        return "teacher/teachers";
    }

    @PostMapping("/teachers")
    public String postTeacher(
            @ModelAttribute("teacher") @Validated Teacher teacher,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "teacher/teachers";
        }
        Role role = roleRepository.findByName("ROLE_STUDENT");
        teacher.getAccount().getRoles().add(role);
        teacherRepository.save(teacher);
        return "redirect:/teachers";
    }

    @GetMapping("/delete-teacher/{teacherId}")
    public String deleteTeacher(@PathVariable int teacherId) {
        Teacher teacher = retrieveOneOrThrowNotFound(teacherRepository::findOne, teacherId, Teacher.class);
        Account account = teacher.getAccount();
        account.getRoles().clear();
        accountRepository.save(account);
        teacherRepository.delete(teacherId);
        return "redirect:/teachers";
    }
}
