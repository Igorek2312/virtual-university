package ua.km.khnu.virtual.university.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.km.khnu.virtual.university.model.Group;
import ua.km.khnu.virtual.university.service.GroupService;
import ua.km.khnu.virtual.university.transfare.GroupForm;

/**
 * @author igorek2312
 */
@RestController
public class GroupController {
    private GroupService groupService;

    @Autowired
    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/groups")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Group create(@RequestBody GroupForm form) {
        return groupService.create(form);
    }

    @GetMapping("/specialties/{specialtyId}/groups")
    public Page<Group> getBySpecialty(@PathVariable int specialtyId, Pageable pageable) {
        return groupService.getBySpecialty(specialtyId, pageable);
    }

    @GetMapping("/groups")
    public Page<Group> getAll(Pageable pageable) {
        return groupService.getAll(pageable);
    }

    @PutMapping("/groups/{groupId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Group update(@PathVariable int groupId, @RequestBody GroupForm form) {
        return groupService.update(groupId, form);
    }

    @DeleteMapping("/groups/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable int groupId) {
        groupService.delete(groupId);
    }


}
