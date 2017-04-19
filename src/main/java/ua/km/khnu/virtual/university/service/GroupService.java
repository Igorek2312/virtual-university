package ua.km.khnu.virtual.university.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.km.khnu.virtual.university.model.Group;
import ua.km.khnu.virtual.university.transfare.GroupForm;

/**
 * @author igorek2312
 */
public interface GroupService {
    Group create(GroupForm groupForm);

    Page<Group> getAll(Pageable pageable);

    Page<Group> getBySpecialty(int specialtyId, Pageable pageable);

    Group update(int groupId, GroupForm groupForm);

    void delete(int groupId);
}
