package ua.km.khnu.virtual.university.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.km.khnu.virtual.university.model.Group;
import ua.km.khnu.virtual.university.model.Specialty;
import ua.km.khnu.virtual.university.repositories.GroupRepository;
import ua.km.khnu.virtual.university.repositories.SpecialtyRepository;
import ua.km.khnu.virtual.university.transfare.GroupForm;

import java.util.List;

import static ua.km.khnu.virtual.university.util.legacy.EntityUtils.retrieveOneOrThrowNotFound;
import static ua.km.khnu.virtual.university.util.legacy.EntityUtils.throwNotFoundIfNotExists;

/**
 * @author Igor Rybak
 */
@Service
@Transactional
public class GroupServiceImpl implements GroupService {
    private GroupRepository groupRepository;
    private SpecialtyRepository specialtyRepository;
    private ModelMapper modelMapper;

    @Autowired
    public GroupServiceImpl(
            GroupRepository groupRepository,
            SpecialtyRepository specialtyRepository,
            ModelMapper modelMapper
    ) {
        this.groupRepository = groupRepository;
        this.specialtyRepository = specialtyRepository;
        this.modelMapper = modelMapper;
    }

    private Group mapToGroup(GroupForm groupForm) {
        Group group = modelMapper.map(groupForm, Group.class);
        return group;
    }

    @Override
    public Group create(GroupForm groupForm) {
        throwNotFoundIfNotExists(
                specialtyRepository::exists,
                groupForm.getSpecialtyId(),
                Specialty.class
        );
        Group group = mapToGroup(groupForm);
        group.setSpecialty(new Specialty(groupForm.getSpecialtyId()));
        groupRepository.save(group);
        return group;
    }

    @Override
    public Page<Group> getAll(Pageable pageable) {
        return groupRepository.findAll(pageable);
    }

    @Override
    public List<Group> getBySpecialty(int specialtyId, Sort sort) {
        throwNotFoundIfNotExists(
                specialtyRepository::exists,
                specialtyId,
                Specialty.class
        );

        return groupRepository.findBySpecialtyId(specialtyId, sort);
    }

    @Override
    public Group update(int groupId, GroupForm groupForm) {
        Group group = retrieveOneOrThrowNotFound(
                groupRepository::findOne,
                groupId,
                Group.class
        );
        group.setName(groupForm.getName());
        groupRepository.save(group);
        return group;
    }

    @Override
    public void delete(int groupId) {
        throwNotFoundIfNotExists(
                groupRepository::exists,
                groupId,
                Group.class
        );

        groupRepository.delete(groupId);
    }

    @Override
    public Group get(int groupId) {
        return groupRepository.findOne(groupId);
    }
}
