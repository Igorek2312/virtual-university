package ua.km.khnu.virtual.university.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ua.km.khnu.virtual.university.model.Group;
import ua.km.khnu.virtual.university.transfare.GroupForm;

import java.util.List;

/**
 * @author Igor Rybak
 */
public interface GroupService {
    Group create(GroupForm groupForm);

    Page<Group> getAll(Pageable pageable);

    List<Group> getBySpecialty(int specialtyId, Sort sort);

    Group update(int groupId, GroupForm groupForm);

    void delete(int groupId);

    Group get(int groupId);
}
