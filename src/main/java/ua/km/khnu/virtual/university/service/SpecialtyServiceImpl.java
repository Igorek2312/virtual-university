package ua.km.khnu.virtual.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.km.khnu.virtual.university.model.Faculty;
import ua.km.khnu.virtual.university.model.Specialty;
import ua.km.khnu.virtual.university.repository.FacultyRepository;
import ua.km.khnu.virtual.university.repository.SpecialtyRepository;
import ua.km.khnu.virtual.university.transfare.SpecialtyForm;

import static ua.km.khnu.virtual.university.util.EntityUtils.retrieveOneOrThrowNotFound;
import static ua.km.khnu.virtual.university.util.EntityUtils.throwNotFoundIfNotExists;

/**
 * @author Igor Rybak
 */
@Service
@Transactional
public class SpecialtyServiceImpl implements SpecialtyService {
    private SpecialtyRepository specialtyRepository;
    private FacultyRepository facultyRepository;

    @Autowired
    public SpecialtyServiceImpl(SpecialtyRepository specialtyRepository, FacultyRepository facultyRepository) {
        this.specialtyRepository = specialtyRepository;
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Specialty create(SpecialtyForm form) {
        throwNotFoundIfNotExists(facultyRepository::exists, form.getFacultyId(), Faculty.class);
        Faculty faculty = facultyRepository.getOne(form.getFacultyId());

        Specialty specialty = new Specialty();
        specialty.setFaculty(faculty);
        specialty.setName(form.getName());
        specialtyRepository.save(specialty);
        return specialty;
    }

    @Override
    public Specialty get(Integer id) {
        return retrieveOneOrThrowNotFound(specialtyRepository::findOne, id, Specialty.class);
    }

    @Override
    public Page<Specialty> getAll(Pageable pageable) {
        return specialtyRepository.findAll(pageable);
    }

    @Override
    public Specialty update(SpecialtyForm form, Integer id) {
        Specialty specialty = retrieveOneOrThrowNotFound(specialtyRepository::findOne, id, Specialty.class);
        specialty.setName(form.getName());
        specialtyRepository.save(specialty);
        return specialty;
    }

    @Override
    public void delete(Integer id) {
        throwNotFoundIfNotExists(specialtyRepository::exists, id, Specialty.class);
        specialtyRepository.delete(id);
    }

    @Override
    public Page<Specialty> getByFaculty(int facultyId, Pageable pageable) {
        throwNotFoundIfNotExists(facultyRepository::exists, facultyId, Faculty.class);
        return specialtyRepository.findByFacultyId(facultyId, pageable);
    }
}
