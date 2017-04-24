package ua.km.khnu.virtual.university.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.km.khnu.virtual.university.model.Grade;
import ua.km.khnu.virtual.university.model.PeriodInstance;
import ua.km.khnu.virtual.university.repositories.GradeRepository;
import ua.km.khnu.virtual.university.repositories.PeriodInstanceRepository;
import ua.km.khnu.virtual.university.transfare.CreateGradeForm;
import ua.km.khnu.virtual.university.transfare.UpdateGradeForm;

import java.util.List;

import static ua.km.khnu.virtual.university.util.EntityUtils.retrieveOneOrThrowNotFound;
import static ua.km.khnu.virtual.university.util.EntityUtils.throwNotFoundIfNotExists;

/**
 * @author Igor Rybak
 */
@Service
@Transactional
public class GradeServiceImpl implements GradeService {
    private GradeRepository gradeRepository;
    private PeriodInstanceRepository periodInstanceRepository;
    private ModelMapper modelMapper;

    @Autowired
    public GradeServiceImpl(
            GradeRepository gradeRepository,
            PeriodInstanceRepository periodInstanceRepository,
            ModelMapper modelMapper
    ) {
        this.gradeRepository = gradeRepository;
        this.periodInstanceRepository = periodInstanceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Grade create(CreateGradeForm form) {
        throwNotFoundIfNotExists(
                gradeRepository::exists,
                form.getPeriodInstanceId(),
                Grade.class
        );
        Grade grade = modelMapper.map(form, Grade.class);
        grade.setPeriodInstance(new PeriodInstance(form.getPeriodInstanceId()));
        return grade;
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
        return grade;
    }
}
