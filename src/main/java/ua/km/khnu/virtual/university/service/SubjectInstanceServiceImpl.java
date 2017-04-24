package ua.km.khnu.virtual.university.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.km.khnu.virtual.university.model.Group;
import ua.km.khnu.virtual.university.model.Subject;
import ua.km.khnu.virtual.university.model.SubjectInstance;
import ua.km.khnu.virtual.university.repositories.GroupRepository;
import ua.km.khnu.virtual.university.repositories.SubjectInstanceRepository;
import ua.km.khnu.virtual.university.repositories.SubjectRepository;
import ua.km.khnu.virtual.university.transfare.CreateSubjectInstanceForm;
import ua.km.khnu.virtual.university.transfare.UpdateSubjectInstanceForm;

import static ua.km.khnu.virtual.university.util.EntityUtils.retrieveOneOrThrowNotFound;
import static ua.km.khnu.virtual.university.util.EntityUtils.throwNotFoundIfNotExists;

/**
 * @author Igor Rybak
 */
@Service
@Transactional
public class SubjectInstanceServiceImpl implements SubjectInstanceService {
    private SubjectInstanceRepository subjectInstanceRepository;
    private SubjectRepository subjectRepository;
    private GroupRepository groupRepository;
    private ModelMapper modelMapper;

    @Autowired
    public SubjectInstanceServiceImpl(
            SubjectInstanceRepository subjectInstanceRepository,
            SubjectRepository subjectRepository,
            GroupRepository groupRepository,
            ModelMapper modelMapper
    ) {
        this.subjectInstanceRepository = subjectInstanceRepository;
        this.subjectRepository = subjectRepository;
        this.groupRepository = groupRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SubjectInstance create(CreateSubjectInstanceForm form) {
        SubjectInstance subjectInstance = modelMapper.map(form, SubjectInstance.class);
        throwNotFoundIfNotExists(subjectRepository::exists,form.getSubjectId(), Subject.class);
        throwNotFoundIfNotExists(groupRepository::exists,form.getGroupId(), Group.class);
        Subject subject = new Subject(form.getSubjectId());
        Group group = new Group(form.getGroupId());
        subjectInstance.setSubject(subject);
        subjectInstance.setGroup(group);
        subjectInstanceRepository.save(subjectInstance);
        return subjectInstance;
    }

    @Override
    public Page<SubjectInstance> getAll(Pageable pageable) {
        return subjectInstanceRepository.findAll(pageable);
    }

    @Override
    public SubjectInstance update(int subjectInstanceId, UpdateSubjectInstanceForm form) {
        SubjectInstance subjectInstance = retrieveOneOrThrowNotFound(
                subjectInstanceRepository::findOne,
                subjectInstanceId,
                SubjectInstance.class
        );
        modelMapper.map(form, subjectInstance);
        return subjectInstance;
    }

    @Override
    public void delete(int subjectInstanceId) {
        throwNotFoundIfNotExists(subjectInstanceRepository::exists, subjectInstanceId, SubjectInstance.class);
        subjectInstanceRepository.delete(subjectInstanceId);
    }
}
