package ua.km.khnu.virtual.university.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.km.khnu.virtual.university.model.Student;
import ua.km.khnu.virtual.university.service.StudentService;
import ua.km.khnu.virtual.university.transfare.CreateStudentForm;
import ua.km.khnu.virtual.university.transfare.EnableStudentForm;

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
    public ResponseEntity<Object> getByDocumentNumber(
            @RequestParam(value = "document-number", required = false) String documentNumber,
            Pageable pageable
    ) {
        if (documentNumber != null) {
            Student student = studentService.getByDocumentNumber(documentNumber);
            return new ResponseEntity<>(student, HttpStatus.OK);
        }

        Page<Student> students = studentService.getAll(pageable);
        return new ResponseEntity<>(students, HttpStatus.OK);
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

    @PatchMapping("/students/{studentId}/enabled")
    public Student enableStudent(@PathVariable int studentId, EnableStudentForm form) {
        return studentService.enableStudent(studentId, form);
    }

    @DeleteMapping("/students/{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable int studentId) {
        studentService.delete(studentId);
    }
}
