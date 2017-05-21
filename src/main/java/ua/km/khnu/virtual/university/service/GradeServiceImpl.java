package ua.km.khnu.virtual.university.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.km.khnu.virtual.university.model.Grade;
import ua.km.khnu.virtual.university.model.PeriodInstance;
import ua.km.khnu.virtual.university.model.Student;
import ua.km.khnu.virtual.university.repositories.GradeRepository;
import ua.km.khnu.virtual.university.repositories.PeriodInstanceRepository;
import ua.km.khnu.virtual.university.repositories.StudentRepository;
import ua.km.khnu.virtual.university.transfare.legacy.CreateGradeForm;
import ua.km.khnu.virtual.university.transfare.legacy.UpdateGradeForm;

import java.util.List;

import static ua.km.khnu.virtual.university.util.legacy.EntityUtils.retrieveOneOrThrowNotFound;
import static ua.km.khnu.virtual.university.util.legacy.EntityUtils.throwNotFoundIfNotExists;

/**
 * @author Igor Rybak
 */
@Service
@Transactional
public class GradeServiceImpl implements GradeService {
    private GradeRepository gradeRepository;
    private PeriodInstanceRepository periodInstanceRepository;
    private final StudentRepository studentRepository;
    private ModelMapper modelMapper;

    @Autowired
    public GradeServiceImpl(
            GradeRepository gradeRepository,
            PeriodInstanceRepository periodInstanceRepository,
            ModelMapper modelMapper,
            StudentRepository studentRepository
    ) {
        this.gradeRepository = gradeRepository;
        this.periodInstanceRepository = periodInstanceRepository;
        this.modelMapper = modelMapper;
        this.studentRepository = studentRepository;
    }

    @Override
    public Grade create(CreateGradeForm form) {
        throwNotFoundIfNotExists(
                periodInstanceRepository::exists,
                form.getPeriodInstanceId(),
                Grade.class
        );
        throwNotFoundIfNotExists(
                studentRepository::exists,
                form.getStudentId(),
                Student.class
        );
        Grade grade = modelMapper.map(form, Grade.class);
        grade.setPeriodInstance(new PeriodInstance(form.getPeriodInstanceId()));
        grade.setStudent(new Student(form.getStudentId()));
        return gradeRepository.save(grade);
    }

    @Override
    public Page<Grade> getAll(Pageable pageable) {
        return gradeRepository.findAll(pageable);
    }

    @Override
    public List<Grade> getBySubjectInstance(int subjectInstanceId) {
        return gradeRepository.findBySubjectInstanceId(subjectInstanceId);
    }

    @Override
    public Grade update(int gradeId, UpdateGradeForm form) {
        Grade grade = retrieveOneOrThrowNotFound(
                gradeRepository::findOne,
                gradeId,
                Grade.class
        );
        modelMapper.map(form, grade);
        gradeRepository.save(grade);
        return grade;
    }
}
