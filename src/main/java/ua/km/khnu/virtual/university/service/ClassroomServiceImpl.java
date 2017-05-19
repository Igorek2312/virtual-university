package ua.km.khnu.virtual.university.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.km.khnu.virtual.university.model.Classroom;
import ua.km.khnu.virtual.university.repositories.ClassroomRepository;
import ua.km.khnu.virtual.university.transfare.ClassroomForm;

import static ua.km.khnu.virtual.university.util.legacy.EntityUtils.retrieveOneOrThrowNotFound;
import static ua.km.khnu.virtual.university.util.legacy.EntityUtils.throwNotFoundIfNotExists;

/**
 * @author Igor Rybak
 */
@Service
@Transactional
public class ClassroomServiceImpl implements ClassroomService {
    private ClassroomRepository classroomRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ClassroomServiceImpl(ClassroomRepository classroomRepository, ModelMapper modelMapper) {
        this.classroomRepository = classroomRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Classroom create(ClassroomForm form) {
        Classroom classroom = modelMapper.map(form, Classroom.class);
        return classroomRepository.save(classroom);
    }

    @Override
    public Page<Classroom> getAll(Pageable pageable) {
        return classroomRepository.findAll(pageable);
    }

    @Override
    public Classroom update(int classroomId, ClassroomForm form) {
        Classroom classroom = retrieveOneOrThrowNotFound(
                classroomRepository::findOne,
                classroomId,
                Classroom.class
        );

        return classroomRepository.save(classroom);
    }

    @Override
    public void delete(int classroomId) {
        throwNotFoundIfNotExists(classroomRepository::exists, classroomId, Classroom.class);
        classroomRepository.delete(classroomId);
    }
}
