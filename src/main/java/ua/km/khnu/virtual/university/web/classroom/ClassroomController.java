package ua.km.khnu.virtual.university.web.classroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.km.khnu.virtual.university.model.Classroom;
import ua.km.khnu.virtual.university.repositories.ClassroomRepository;

/**
 * @author Igor Rybak
 */
@Controller
public class ClassroomController {
    private final ClassroomRepository classroomRepository;

    @Autowired
    public ClassroomController(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    @ModelAttribute("classroom")
    public Classroom classroom() {
        Classroom classroom = new Classroom();
        classroom.setCapacity(30);
        return classroom;
    }

    @ModelAttribute("classrooms")
    public Page<Classroom> classrooms(@PageableDefault(size = 5)  Pageable pageable) {
        return classroomRepository.findAll(pageable);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/classrooms")
    public String getClassrooms() {
        return "classroom/classrooms";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/classrooms")
    public String postClassroom(
            @ModelAttribute("classroom") Classroom classroom,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "classroom/classrooms";
        }
        classroomRepository.save(classroom);
        return "redirect:/classrooms";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/delete-classroom/{classroomId}")
    public String deleteClassroom(
            @PathVariable int classroomId
    ) {
        classroomRepository.delete(classroomId);
        return "redirect:/classrooms";
    }
}
