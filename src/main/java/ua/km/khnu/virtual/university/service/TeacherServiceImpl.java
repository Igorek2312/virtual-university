package ua.km.khnu.virtual.university.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.km.khnu.virtual.university.model.Account;
import ua.km.khnu.virtual.university.model.Role;
import ua.km.khnu.virtual.university.model.Teacher;
import ua.km.khnu.virtual.university.repositories.AccountRepository;
import ua.km.khnu.virtual.university.repositories.RoleRepository;
import ua.km.khnu.virtual.university.repositories.TeacherRepository;
import ua.km.khnu.virtual.university.transfare.TeacherForm;

import static ua.km.khnu.virtual.university.util.legacy.EntityUtils.retrieveOneOrThrowNotFound;
import static ua.km.khnu.virtual.university.util.legacy.EntityUtils.throwNotFoundIfNotExists;

/**
 * @author Igor Rybak
 */
@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
    private TeacherRepository teacherRepository;
    private AccountRepository accountRepository;
    private RoleRepository roleRepository;
    private ModelMapper modelMapper;

    public TeacherServiceImpl(
            TeacherRepository teacherRepository,
            AccountRepository accountRepository,
            RoleRepository roleRepository,
            ModelMapper modelMapper
    ) {
        this.teacherRepository = teacherRepository;
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Teacher create(TeacherForm form) {
        Account account = modelMapper.map(form, Account.class);
        Role role = roleRepository.findByName("ROLE_TEACHER");
        account.getRoles().add(role);
        accountRepository.save(account);
        Teacher teacher = new Teacher();
        teacher.setAccount(account);
        teacherRepository.save(teacher);
        return teacher;
    }

    @Override
    public Page<Teacher> getAll(Pageable pageable) {
        return teacherRepository.findAll(pageable);
    }

    @Override
    public Teacher update(int teacherId, TeacherForm form) {
        Teacher teacher = retrieveOneOrThrowNotFound(
                teacherRepository::findOne,
                teacherId,
                Teacher.class
        );

        modelMapper.map(form, teacher.getAccount());
        teacherRepository.save(teacher);
        return teacher;
    }

    @Override
    public void delete(int teacherId) {
        throwNotFoundIfNotExists(teacherRepository::exists, teacherId, Teacher.class);
        teacherRepository.delete(teacherId);
    }

}
