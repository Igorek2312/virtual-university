package ua.km.khnu.virtual.university.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.km.khnu.virtual.university.model.Group;
import ua.km.khnu.virtual.university.transfare.GroupForm;

/**
 * @author igorek2312
 */
@RestController
public class GroupController {

    @PostMapping("/groups")
    @ResponseStatus(HttpStatus.CREATED)
    public Group create(@RequestBody GroupForm form) {
        return null;
    }

    @GetMapping("/specialties/{specialtyId}/groups")
    public Page<Group> getBySpecialty(@PathVariable int specialtyId, Pageable pageable) {
        return null;
    }

    @GetMapping("/groups")
    public Page<Group> getAll(Pageable pageable) {
        return null;
    }

    @PutMapping("/groups/{groupId}")
    public Group update(@PathVariable int groupId) {
        return null;
    }

    @DeleteMapping("/groups/{groupId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int groupId) {

    }
}
