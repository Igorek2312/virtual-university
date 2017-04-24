package ua.km.khnu.virtual.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.km.khnu.virtual.university.model.Faculty;
import ua.km.khnu.virtual.university.model.Specialty;
import ua.km.khnu.virtual.university.repositories.FacultyRepository;
import ua.km.khnu.virtual.university.repositories.SpecialtyRepository;
import ua.km.khnu.virtual.university.transfare.CreateSpecialtyForm;

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
    public Specialty create(CreateSpecialtyForm form) {
        throwNotFoundIfNotExists(facultyRepository::exists, form.getFacultyId(), Faculty.class);

        Specialty specialty = new Specialty();
        specialty.setFaculty(new Faculty(form.getFacultyId()));
        specialty.setName(form.getName());
        specialtyRepository.save(specialty);
        return specialty;
    }

    @Override
    public Specialty get(int id) {
        return retrieveOneOrThrowNotFound(specialtyRepository::findOne, id, Specialty.class);
    }

    @Override
    public Page<Specialty> getAll(Pageable pageable) {
        return specialtyRepository.findAll(pageable);
    }

    @Override
    public Specialty update(CreateSpecialtyForm form, int id) {
        Specialty specialty = retrieveOneOrThrowNotFound(specialtyRepository::findOne, id, Specialty.class);
        specialty.setName(form.getName());
        specialtyRepository.save(specialty);
        return specialty;
    }

    @Override
    public void delete(int id) {
        throwNotFoundIfNotExists(specialtyRepository::exists, id, Specialty.class);
        specialtyRepository.delete(id);
    }

    @Override
    public Page<Specialty> getByFaculty(int facultyId, Pageable pageable) {
        throwNotFoundIfNotExists(facultyRepository::exists, facultyId, Faculty.class);
        return specialtyRepository.findByFacultyId(facultyId, pageable);
    }
}
