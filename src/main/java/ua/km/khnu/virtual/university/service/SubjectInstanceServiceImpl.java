package ua.km.khnu.virtual.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.km.khnu.virtual.university.model.Group;
import ua.km.khnu.virtual.university.model.Subject;
import ua.km.khnu.virtual.university.model.SubjectInstance;
import ua.km.khnu.virtual.university.refrence.Semester;
import ua.km.khnu.virtual.university.refrence.SemesterDateRange;
import ua.km.khnu.virtual.university.repositories.GroupRepository;
import ua.km.khnu.virtual.university.repositories.SubjectInstanceRepository;
import ua.km.khnu.virtual.university.repositories.SubjectRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static ua.km.khnu.virtual.university.util.EntityUtils.retrieveOneOrThrowNotFound;

/**
 * @author Igor Rybak
 */
@Service
@Transactional
public class SubjectInstanceServiceImpl implements SubjectInstanceService {
    private final SubjectInstanceRepository subjectInstanceRepository;
    private final SubjectRepository subjectRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public SubjectInstanceServiceImpl(
            SubjectInstanceRepository subjectInstanceRepository,
            SubjectRepository subjectRepository,
            GroupRepository groupRepository
    ) {
        this.subjectInstanceRepository = subjectInstanceRepository;
        this.subjectRepository = subjectRepository;
        this.groupRepository = groupRepository;
    }


    @Override
    public SubjectInstance prepareNew() {
        SubjectInstance subjectInstance = new SubjectInstance();
        subjectInstance.setDateBegin(LocalDate.now());
        subjectInstance.setDateEnd(LocalDate.now());
        subjectInstance.setSubject(new Subject());
        return subjectInstance;
    }

    @Override
    public SubjectInstance create(SubjectInstance subjectInstance, int groupId, int subjectId) {
        Group group = retrieveOneOrThrowNotFound(groupRepository::findOne, groupId, Group.class);
        Subject subject = retrieveOneOrThrowNotFound(subjectRepository::findOne, subjectId, Subject.class);
        subjectInstance.setGroup(group);
        subjectInstance.setSubject(subject);
        return subjectInstanceRepository.save(subjectInstance);
    }

    @Override
    public Page<SubjectInstance> getByGroupAndSemester(Pageable pageable, int year, int semester) {
        return null;
    }

    @Override
    public List<Semester> getSemesters(int groupId) {
        Sort dateBegin = new Sort(new Sort.Order(Sort.Direction.DESC, "dateBegin"));
        return subjectInstanceRepository.findByGroupId(groupId, dateBegin)
                .stream()
                .map(SubjectInstance::getSemester)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public void delete(int subjectInstanceId) {
        subjectInstanceRepository.delete(subjectInstanceId);
    }

    @Override
    public List<SubjectInstance> getBySemester(int groupId, int year, int semesterNumber) {
        SemesterDateRange semesterDateRange = new SemesterDateRange(year, semesterNumber);
        LocalDate dateBegin = semesterDateRange.getDateBegin();
        LocalDate dateEnd = semesterDateRange.getDateEnd();
        return subjectInstanceRepository.findByGroupAndSemester(groupId, dateBegin, dateEnd);
    }

    @Override
    public SubjectInstance get(int subjectInstanceId) {
        return subjectInstanceRepository.findOne(subjectInstanceId);
    }
}
