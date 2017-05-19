package ua.km.khnu.virtual.university.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ua.km.khnu.virtual.university.model.Student;
import ua.km.khnu.virtual.university.transfare.CreateStudentForm;

import java.util.List;

/**
 * @author Igor Rybak
 */
public interface StudentService {
    Student create(CreateStudentForm form);

    Page<Student> getAll(Pageable pageable);

    List<Student> getByGroup(int groupId, Sort sort);

    Student update(int studentId, CreateStudentForm form);

    void delete(int studentId);

    Page<Student> getByGroupName(String groupName, Pageable pageable);
}
