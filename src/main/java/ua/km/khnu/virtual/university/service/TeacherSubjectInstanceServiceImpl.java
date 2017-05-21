package ua.km.khnu.virtual.university.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.km.khnu.virtual.university.model.SubjectInstance;
import ua.km.khnu.virtual.university.model.Teacher;
import ua.km.khnu.virtual.university.model.TeacherSubjectInstance;
import ua.km.khnu.virtual.university.repositories.SubjectInstanceRepository;
import ua.km.khnu.virtual.university.repositories.TeacherRepository;
import ua.km.khnu.virtual.university.repositories.TeacherSubjectInstanceRepository;
import ua.km.khnu.virtual.university.transfare.legacy.CreateTeacherSubjectInstanceForm;
import ua.km.khnu.virtual.university.transfare.legacy.UpdateTeacherSubjectInstanceForm;

import static ua.km.khnu.virtual.university.util.legacy.EntityUtils.retrieveOneOrThrowNotFound;
import static ua.km.khnu.virtual.university.util.legacy.EntityUtils.throwNotFoundIfNotExists;

/**
 * @author Igor Rybak
 */
@Service
@Transactional
public class TeacherSubjectInstanceServiceImpl implements TeacherSubjectInstanceService {
    private TeacherRepository teacherRepository;
    private SubjectInstanceRepository subjectInstanceRepository;
    private TeacherSubjectInstanceRepository teacherSubjectInstanceRepository;
    private ModelMapper modelMapper;

    @Autowired
    public TeacherSubjectInstanceServiceImpl(
            TeacherRepository teacherRepository,
            SubjectInstanceRepository subjectInstanceRepository,
            TeacherSubjectInstanceRepository teacherSubjectInstanceRepository,
            ModelMapper modelMapper
    ) {
        this.teacherRepository = teacherRepository;
        this.subjectInstanceRepository = subjectInstanceRepository;
        this.teacherSubjectInstanceRepository = teacherSubjectInstanceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TeacherSubjectInstance create(CreateTeacherSubjectInstanceForm form) {
        TeacherSubjectInstance teacherSubjectInstance = modelMapper.map(form, TeacherSubjectInstance.class);

        throwNotFoundIfNotExists(
                subjectInstanceRepository::exists,
                form.getSubjectInstanceId(),
                SubjectInstance.class
        );
        throwNotFoundIfNotExists(
                teacherRepository::exists,
                form.getTeacherId(),
                Teacher.class
        );

        teacherSubjectInstance.setSubjectInstance(new SubjectInstance(form.getSubjectInstanceId()));
        teacherSubjectInstance.setTeacher(new Teacher(form.getTeacherId()));
        return teacherSubjectInstanceRepository.save(teacherSubjectInstance);
    }

    @Override
    public Page<TeacherSubjectInstance> getAll(Pageable pageable) {
        return teacherSubjectInstanceRepository.findAll(pageable);
    }

    @Override
    public TeacherSubjectInstance update(
            int teacherSubjectInstancesId,
            UpdateTeacherSubjectInstanceForm form
    ) {
        TeacherSubjectInstance teacherSubjectInstance = retrieveOneOrThrowNotFound(
                teacherSubjectInstanceRepository::findOne,
                teacherSubjectInstancesId,
                TeacherSubjectInstance.class
        );
        modelMapper.map(form, teacherSubjectInstance);
        return teacherSubjectInstanceRepository.save(teacherSubjectInstance);
    }

    @Override
    public void delete(int teacherSubjectInstancesId) {
        throwNotFoundIfNotExists(
                teacherSubjectInstanceRepository::exists,
                teacherSubjectInstancesId,
                TeacherSubjectInstance.class
        );
        teacherSubjectInstanceRepository.delete(teacherSubjectInstancesId);
    }
}
