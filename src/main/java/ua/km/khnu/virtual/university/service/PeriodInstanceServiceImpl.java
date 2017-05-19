package ua.km.khnu.virtual.university.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.km.khnu.virtual.university.error.legacy.ConflictException;
import ua.km.khnu.virtual.university.model.Period;
import ua.km.khnu.virtual.university.model.PeriodInstance;
import ua.km.khnu.virtual.university.model.SubjectInstance;
import ua.km.khnu.virtual.university.model.TeacherSubjectInstance;
import ua.km.khnu.virtual.university.repositories.PeriodInstanceRepository;
import ua.km.khnu.virtual.university.repositories.PeriodRepository;
import ua.km.khnu.virtual.university.transfare.CreatePeriodInstanceForm;
import ua.km.khnu.virtual.university.transfare.UpdatePeriodInstanceForm;
import ua.km.khnu.virtual.university.util.EveryDayOfWeekRange;

import java.time.LocalDate;

import static ua.km.khnu.virtual.university.util.legacy.EntityUtils.retrieveOneOrThrowNotFound;
import static ua.km.khnu.virtual.university.util.legacy.EntityUtils.throwNotFoundIfNotExists;

/**
 * @author Igor Rybak
 */
@Service
@Transactional
public class PeriodInstanceServiceImpl implements PeriodInstanceService {
    private PeriodRepository periodRepository;
    private PeriodInstanceRepository periodInstanceRepository;
    private ModelMapper modelMapper;

    @Autowired
    public PeriodInstanceServiceImpl(
            PeriodRepository periodRepository,
            PeriodInstanceRepository periodInstanceRepository,
            ModelMapper modelMapper
    ) {
        this.periodRepository = periodRepository;
        this.periodInstanceRepository = periodInstanceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PeriodInstance create(CreatePeriodInstanceForm form) {
        throwNotFoundIfNotExists(
                periodRepository::exists,
                form.getPeriodId(),
                Period.class
        );

        Period period = new Period(form.getPeriodId());
        PeriodInstance periodInstance = modelMapper.map(form, PeriodInstance.class);
        periodInstance.setPeriod(period);
        return periodInstanceRepository.save(periodInstance);
    }

    @Override
    public Page<PeriodInstance> getAll(Pageable pageable) {
        return periodInstanceRepository.findAll(pageable);
    }

    @Override
    public PeriodInstance update(int periodInstanceId, UpdatePeriodInstanceForm form) {
        PeriodInstance periodInstance = retrieveOneOrThrowNotFound(
                periodInstanceRepository::findOne,
                periodInstanceId,
                PeriodInstance.class
        );
        modelMapper.map(form, periodInstance);
        return periodInstanceRepository.save(periodInstance);
    }

    @Override
    public void delete(int periodInstanceId) {
        throwNotFoundIfNotExists(
                periodRepository::exists,
                periodInstanceId,
                PeriodInstance.class
        );
        periodInstanceRepository.delete(periodInstanceId);
    }

    @Override
    public void createAll(int periodId) {
        Period period = retrieveOneOrThrowNotFound(
                periodRepository::findOne,
                periodId,
                Period.class
        );
        TeacherSubjectInstance teacherSubjectInstance = period.getTeacherSubjectInstance();
        SubjectInstance subjectInstance = teacherSubjectInstance.getSubjectInstance();
        LocalDate dateBegin = subjectInstance.getDateBegin();
        LocalDate dateEnd = subjectInstance.getDateEnd();

        EveryDayOfWeekRange range = new EveryDayOfWeekRange(dateBegin, dateEnd, period.getOddEven(), period.getDayOfWeek());
        for (LocalDate current : range){
            if (periodInstanceRepository.existsByDateAndPeriodId(current,periodId))
                throw new ConflictException("Such period instance already exists");

            PeriodInstance periodInstance = new PeriodInstance();
            periodInstance.setDate(current);
            periodInstance.setType(teacherSubjectInstance.getPeriodType());
            periodInstance.setPeriod(period);
            periodInstanceRepository.save(periodInstance);
        }
    }

    @Override
    public void deleteAll(int periodId) {
        periodInstanceRepository.deleteAllByPeriodId(periodId);
    }

}
