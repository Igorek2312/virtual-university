package ua.km.khnu.virtual.university.web.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.km.khnu.virtual.university.model.Group;
import ua.km.khnu.virtual.university.model.Specialty;
import ua.km.khnu.virtual.university.repositories.GroupRepository;
import ua.km.khnu.virtual.university.repositories.SpecialtyRepository;

import java.time.Year;
import java.util.List;

import static ua.km.khnu.virtual.university.util.EntityUtils.retrieveOneOrThrowNotFound;

/**
 * @author Igor Rybak
 */
@Controller
public class GroupController {
    private final SpecialtyRepository specialtyRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public GroupController(SpecialtyRepository specialtyRepository, GroupRepository groupRepository) {
        this.specialtyRepository = specialtyRepository;
        this.groupRepository = groupRepository;
    }

    @ModelAttribute("group")
    public Group group(@PathVariable int specialtyId) {
        Specialty specialty = retrieveOneOrThrowNotFound(specialtyRepository::findOne, specialtyId, Specialty.class);
        Group group = new Group();
        group.setSpecialty(specialty);
        group.setYearEntered(Year.now().getValue());
        group.setYearOfStudyEntered(1);
        return group;
    }

    @ModelAttribute("specialty")
    public Specialty specialty(@PathVariable int specialtyId) {
        return retrieveOneOrThrowNotFound(specialtyRepository::findOne, specialtyId, Specialty.class);
    }

    private void initModel(@PathVariable int specialtyId, Sort sort, Model model) {
        List<Group> groups = groupRepository.findBySpecialtyId(specialtyId, sort);
        model.addAttribute("groups",groups);
    }

    @GetMapping("/specialties/{specialtyId}/groups")
    public String getGroupsBySpecialty(
            @PathVariable int specialtyId,
            Sort sort,
            Model model
    ) {
        initModel(specialtyId, sort, model);
        return "group/groups";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/specialties/{specialtyId}/groups")
    public String postGroup(
            @ModelAttribute("group") @Validated Group group,
            BindingResult result,
            @PathVariable int specialtyId,
            Sort sort,
            Model model
    ) {
        if (result.hasErrors()) {
            initModel(specialtyId, sort, model);
            return "group/groups";
        }
        groupRepository.save(group);
        return "redirect:/specialties/{specialtyId}/groups";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/specialties/{specialtyId}/delete-group/{groupId}")
    public String deleteGroup(
            @PathVariable int specialtyId,
            @PathVariable int groupId
    ) {
        groupRepository.delete(groupId);
        return "redirect:/specialties/{specialtyId}/groups";
    }


}
