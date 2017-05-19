package ua.km.khnu.virtual.university.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.km.khnu.virtual.university.model.Subject;
import ua.km.khnu.virtual.university.repositories.SubjectRepository;
import ua.km.khnu.virtual.university.transfare.SubjectForm;

import static ua.km.khnu.virtual.university.util.legacy.EntityUtils.retrieveOneOrThrowNotFound;
import static ua.km.khnu.virtual.university.util.legacy.EntityUtils.throwNotFoundIfNotExists;

/**
 * @author Igor Rybak
 */
@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {
    private SubjectRepository subjectRepository;
    private ModelMapper modelMapper;

    @Autowired
    public SubjectServiceImpl(
            SubjectRepository subjectRepository,
            ModelMapper modelMapper
    ) {
        this.subjectRepository = subjectRepository;
        this.modelMapper = modelMapper;
    }

     @Override
    public Subject create(SubjectForm form) {
        Subject subject = modelMapper.map(form, Subject.class);
        return subjectRepository.save(subject);
    }

    @Override
    public Page<Subject> getAll(Pageable pageable) {
        return subjectRepository.findAll(pageable);
    }

    @Override
    public Subject update(int subjectId, SubjectForm form) {
        Subject subject = retrieveOneOrThrowNotFound(subjectRepository::findOne, subjectId, Subject.class);
        modelMapper.map(form, subject);
        return subject;
    }

    @Override
    public void delete(int subjectId) {
        throwNotFoundIfNotExists(subjectRepository::exists, subjectId, Subject.class);
        subjectRepository.delete(subjectId);
    }
}
