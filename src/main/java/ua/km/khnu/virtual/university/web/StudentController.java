package ua.km.khnu.virtual.university.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.km.khnu.virtual.university.model.Student;
import ua.km.khnu.virtual.university.service.StudentService;
import ua.km.khnu.virtual.university.transfare.CreateStudentForm;

/**
 * @author Igor Rybak
 */
@RestController
public class StudentController {

    private StudentService studentService;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Student create(@RequestBody CreateStudentForm form) {
        return studentService.create(form);
    }

    @GetMapping("/students")
    public Page<Student> getByDocumentNumber(
            @RequestParam(value = "group-name", required = false) String groupName,
            Pageable pageable
    ) {
        if (groupName!=null){
           return studentService.getByGroupName(groupName, pageable);
        }
        return studentService.getAll(pageable);
    }

    @GetMapping("/groups/{groupId}/students")
    public Page<Student> getByGroup(@PathVariable int groupId, Pageable pageable) {
        return studentService.getByGroup(groupId, pageable);
    }

    @PutMapping("/students/{studentId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Student update(@PathVariable int studentId, @RequestBody CreateStudentForm student) {
        return studentService.update(studentId, student);
    }

    @DeleteMapping("/students/{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable int studentId) {
        studentService.delete(studentId);
    }
}
