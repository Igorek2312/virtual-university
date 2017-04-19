package ua.km.khnu.virtual.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.km.khnu.virtual.university.model.Faculty;
import ua.km.khnu.virtual.university.repository.FacultyRepository;
import ua.km.khnu.virtual.university.transfare.FacultyForm;

import javax.transaction.Transactional;

import static ua.km.khnu.virtual.university.util.EntityUtils.retrieveOneOrThrowNotFound;
import static ua.km.khnu.virtual.university.util.EntityUtils.throwNotFoundIfNotExists;

/**
 * @author Igor Rybak
 */
@Service
@Transactional
public class FacultyServiceImpl implements FacultyService {

    private FacultyRepository facultyRepository;

    @Autowired
    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty createFaculty(FacultyForm facultyForm) {
        Faculty faculty = new Faculty();
        faculty.setName(facultyForm.getName());
        facultyRepository.save(faculty);
        return faculty;
    }

    @Override
    public Page<Faculty> getAll(Pageable pageable) {
        return facultyRepository.findAll(pageable);
    }

    @Override
    public Faculty get(int id) {
        return retrieveOneOrThrowNotFound(facultyRepository::findOne, id, Faculty.class);
    }

    @Override
    public Faculty update(FacultyForm facultyForm, int id) {
        Faculty faculty = retrieveOneOrThrowNotFound(facultyRepository::findOne, id, Faculty.class);
        faculty.setName(facultyForm.getName());
        facultyRepository.save(faculty);
        return faculty;
    }

    @Override
    public void delete(int id) {
        throwNotFoundIfNotExists(facultyRepository::exists, id, Faculty.class);
        facultyRepository.delete(id);
    }
}
