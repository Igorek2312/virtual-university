package ua.km.khnu.virtual.university.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.km.khnu.virtual.university.model.Classroom;
import ua.km.khnu.virtual.university.model.Period;
import ua.km.khnu.virtual.university.model.TeacherSubjectInstance;
import ua.km.khnu.virtual.university.repositories.ClassroomRepository;
import ua.km.khnu.virtual.university.repositories.PeriodRepository;
import ua.km.khnu.virtual.university.repositories.TeacherSubjectInstanceRepository;
import ua.km.khnu.virtual.university.transfare.legacy.CreatePeriodForm;
import ua.km.khnu.virtual.university.transfare.legacy.UpdatePeriodForm;

import static ua.km.khnu.virtual.university.util.legacy.EntityUtils.retrieveOneOrThrowNotFound;
import static ua.km.khnu.virtual.university.util.legacy.EntityUtils.throwNotFoundIfNotExists;

/**
 * @author Igor Rybak
 */
@Service
@Transactional
public class PeriodServiceImpl implements PeriodService {
    private PeriodRepository periodRepository;
    private ModelMapper modelMapper;
    private TeacherSubjectInstanceRepository teacherSubjectInstanceRepository;
    private ClassroomRepository classroomRepository;

    @Autowired
    public PeriodServiceImpl(
            PeriodRepository periodRepository,
            ModelMapper modelMapper,
            TeacherSubjectInstanceRepository teacherSubjectInstanceRepository,
            ClassroomRepository classroomRepository
    ) {
        this.periodRepository = periodRepository;
        this.modelMapper = modelMapper;
        this.teacherSubjectInstanceRepository = teacherSubjectInstanceRepository;
        this.classroomRepository = classroomRepository;
    }

    @Override
    public Period create(CreatePeriodForm form) {
        TeacherSubjectInstance teacherSubjectInstance = retrieveOneOrThrowNotFound(
                teacherSubjectInstanceRepository::findOne,
                form.getTeacherSubjectInstanceId(),
                TeacherSubjectInstance.class
        );
        throwNotFoundIfNotExists(
                classroomRepository::exists,
                form.getClassroomId(),
                Classroom.class
        );

        Classroom classroom = new Classroom(form.getClassroomId());
        Period period = modelMapper.map(form, Period.class);
        period.setClassroom(classroom);
        period.setTeacherSubjectInstance(teacherSubjectInstance);

        return periodRepository.save(period);
    }

    @Override
    public Page<Period> getAll(Pageable pageable) {
        return periodRepository.findAll(pageable);
    }

    @Override
    public Page<Period> getByGroup(int groupId, Pageable pageable) {
        return periodRepository.findByGroupId(groupId, pageable);
    }

    @Override
    public Page<Period> getByTeacher(int teacherId, Pageable pageable) {
        return periodRepository.findByTeacherId(teacherId, pageable);
    }

    @Override
    public Period update(int periodId, UpdatePeriodForm form) {
        Period period = retrieveOneOrThrowNotFound(
                periodRepository::findOne,
                periodId,
                Period.class
        );
        modelMapper.map(form,period);
        return periodRepository.save(period);
    }

    @Override
    public void delete(int periodId) {
        throwNotFoundIfNotExists(
                periodRepository::exists,
                periodId,
                Period.class
        );
        periodRepository.delete(periodId);
    }
}
