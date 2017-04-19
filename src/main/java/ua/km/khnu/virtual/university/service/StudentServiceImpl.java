package ua.km.khnu.virtual.university.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.km.khnu.virtual.university.model.Account;
import ua.km.khnu.virtual.university.model.Group;
import ua.km.khnu.virtual.university.model.Role;
import ua.km.khnu.virtual.university.model.Student;
import ua.km.khnu.virtual.university.repository.AccountRepository;
import ua.km.khnu.virtual.university.repository.GroupRepository;
import ua.km.khnu.virtual.university.repository.RoleRepository;
import ua.km.khnu.virtual.university.repository.StudentRepository;
import ua.km.khnu.virtual.university.transfare.CreateStudentForm;

import static ua.km.khnu.virtual.university.util.EntityUtils.*;

/**
 * @author igorek2312
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    private AccountRepository accountRepository;
    private RoleRepository roleRepository;
    private StudentRepository studentRepository;
    private GroupRepository groupRepository;
    private ModelMapper modelMapper;

    @Autowired
    public StudentServiceImpl(
            AccountRepository accountRepository,
            RoleRepository roleRepository,
            StudentRepository studentRepository,
            GroupRepository groupRepository,
            ModelMapper modelMapper
    ) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
        this.modelMapper = modelMapper;
    }

    private Account mapToAccount(CreateStudentForm form) {
        Account account = modelMapper.map(form, Account.class);
        account.setId(null);
        return account;
    }

    private Student mapToStudent(CreateStudentForm form) {
        Student student = modelMapper.map(form, Student.class);
        student.setId(null);
        return student;
    }

    @Override
    public Student create(CreateStudentForm form) {
        throwNotFoundIfNotExists(groupRepository::exists, form.getGroupId(), Group.class);
        Group group = groupRepository.getOne(form.getGroupId());

        Role role = roleRepository.findByName("ROLE_STUDENT");
        Account account = mapToAccount(form);
        account.getRoles().add(role);
        account.setEnabled(false);
        accountRepository.save(account);

        Student student = mapToStudent(form);
        student.setAccount(account);
        student.setGroup(group);
        studentRepository.save(student);

        return student;
    }

    @Override
    public Page<Student> getAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Page<Student> getByGroup(int groupId, Pageable pageable) {
        return studentRepository.findByGroupId(groupId, pageable);
    }

    @Override
    public Student update(int studentId, CreateStudentForm form) {
        Student student = retrieveOneOrThrowNotFound(
                studentRepository::findOne,
                form.getGroupId(),
                Student.class);

        modelMapper.map(form, student);
        modelMapper.map(form, student.getAccount());

        return student;
    }

    @Override
    public void delete(int studentId) {
        throwNotFoundIfNotExists(studentRepository::exists, studentId, Student.class);
        studentRepository.delete(studentId);
    }
}
