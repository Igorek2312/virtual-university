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
import ua.km.khnu.virtual.university.repositories.GroupRepository;
import ua.km.khnu.virtual.university.repositories.SubjectInstanceRepository;
import ua.km.khnu.virtual.university.repositories.SubjectRepository;
import ua.km.khnu.virtual.university.transfare.SemesterDto;

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
    private SubjectInstanceRepository subjectInstanceRepository;
    private SubjectRepository subjectRepository;
    private GroupRepository groupRepository;

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

    private SemesterDto mapToSemesterDto(SubjectInstance subjectInstance) {
        SemesterDto semesterDto = new SemesterDto();
        semesterDto.setYear(subjectInstance.getDateBegin().getYear());
        int number = subjectInstance.getDateBegin().getMonth().getValue() > 6 ? 1 : 2;
        semesterDto.setSemesterNumber(number);
        return semesterDto;
    }

    @Override
    public List<SemesterDto> getSemesters(int groupId) {
        Sort dateBegin = new Sort(new Sort.Order(Sort.Direction.DESC, "dateBegin"));
        return subjectInstanceRepository.findByGroupId(groupId, dateBegin)
                .stream()
                .map(this::mapToSemesterDto)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public void delete(int subjectInstanceId) {
        subjectInstanceRepository.delete(subjectInstanceId);
    }

    @Override
    public List<SubjectInstance> getBySemester(int groupId, int year, int semesterNumber) {
        int mouthBegin = semesterNumber == 1 ? 9 : 1;//january or september
        int mouthEnd = semesterNumber == 1 ? 12 : 8;//december or august
        LocalDate dateBegin = LocalDate.of(year, mouthBegin, 1);
        LocalDate dateEnd = LocalDate.of(year, mouthEnd, 31);
        return subjectInstanceRepository.findInDateRange(groupId, dateBegin, dateEnd);
    }
}
