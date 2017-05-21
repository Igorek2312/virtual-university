package ua.km.khnu.virtual.university.web.classroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.km.khnu.virtual.university.model.Classroom;
import ua.km.khnu.virtual.university.repositories.ClassroomRepository;

import static ua.km.khnu.virtual.university.util.EntityUtils.retrieveOneOrThrowNotFound;

/**
 * @author Igor Rybak
 */
@Controller
public class EditClassroomController {
    private final ClassroomRepository classroomRepository;

    @Autowired
    public EditClassroomController(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    @ModelAttribute("classroom")
    public Classroom classroom(@PathVariable int classroomId) {
        return retrieveOneOrThrowNotFound(classroomRepository::findOne, classroomId, Classroom.class);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/edit-classroom/{classroomId}")
    public String getClassroomToEdit() {
        return "classroom/edit-classroom";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/edit-classroom/{classroomId}")
    public String updateClassroom(
            @ModelAttribute("classroom") Classroom classroom,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "classroom/edit-classroom";
        }

        classroomRepository.save(classroom);
        return "redirect:/classrooms";
    }
}
