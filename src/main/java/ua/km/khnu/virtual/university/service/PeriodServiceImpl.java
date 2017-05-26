package ua.km.khnu.virtual.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.km.khnu.virtual.university.model.Period;
import ua.km.khnu.virtual.university.refrence.Semester;
import ua.km.khnu.virtual.university.repositories.GroupRepository;
import ua.km.khnu.virtual.university.repositories.PeriodRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Igor Rybak
 */
@Service
@Transactional
public class PeriodServiceImpl implements PeriodService {
    private final PeriodRepository periodRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public PeriodServiceImpl(PeriodRepository periodRepository, GroupRepository groupRepository) {
        this.periodRepository = periodRepository;
        this.groupRepository = groupRepository;
    }


    @Override
    public Period get(int periodId) {
        return periodRepository.findOne(periodId);
    }

    @Override
    public void save(Period period) {
        periodRepository.save(period);
    }

    @Override
    public List<Period> periodsSchedule(int groupId, int year, int semesterNumber) {
        Semester semester = new Semester(year, semesterNumber);
        LocalDate dateBegin = semester.getDateBegin();
        LocalDate dateEnd = semester.getDateEnd();
        return periodRepository.findByGroupAndSemester(groupId, dateBegin, dateEnd);
    }

    @Override
    public void delete(int periodId) {
        periodRepository.delete(periodId);
    }

}
