package ua.km.khnu.virtual.university.web.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.km.khnu.virtual.university.model.Group;
import ua.km.khnu.virtual.university.repositories.GroupRepository;

import static ua.km.khnu.virtual.university.util.EntityUtils.retrieveOneOrThrowNotFound;

/**
 * @author Igor Rybak
 */
@Controller
public class EditGroupController {
    @Autowired
    private GroupRepository groupRepository;

    @ModelAttribute("group")
    public Group group(@PathVariable int groupId) {
        return retrieveOneOrThrowNotFound(groupRepository::findOne, groupId, Group.class);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/edit-group/{groupId}")
    public String getGroup() {
        return "group/edit-group";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/edit-group/{groupId}")
    public String updateGroup(
            @ModelAttribute("group") @Validated Group group,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "group/edit-group";
        }
        groupRepository.save(group);
        return "redirect:/specialties/" + group.getSpecialty().getId() + "/groups";
    }
}
