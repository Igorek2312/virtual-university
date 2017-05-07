package ua.km.khnu.virtual.university.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ua.km.khnu.virtual.university.model.TeacherSubjectInstance;
import ua.km.khnu.virtual.university.service.TeacherSubjectInstanceService;
import ua.km.khnu.virtual.university.transfare.CreateTeacherSubjectInstanceForm;
import ua.km.khnu.virtual.university.transfare.UpdateTeacherSubjectInstanceForm;

/**
 * @author Igor Rybak
 */
@RestController
public class TeacherSubjectInstanceController {
    private TeacherSubjectInstanceService teacherSubjectInstanceService;

    @Autowired
    public void setTeacherSubjectInstanceService(TeacherSubjectInstanceService teacherSubjectInstanceService) {
        this.teacherSubjectInstanceService = teacherSubjectInstanceService;
    }

    @PostMapping("/teacher-subject-instances")
    public TeacherSubjectInstance create(CreateTeacherSubjectInstanceForm form) {
        return teacherSubjectInstanceService.create(form);
    }

    @GetMapping("/teacher-subject-instances")
    public Page<TeacherSubjectInstance> getAll(Pageable pageable) {
        return teacherSubjectInstanceService.getAll(pageable);
    }

    @PutMapping("/teacher-subject-instances/{teacherSubjectInstancesId}")
    public TeacherSubjectInstance update(
            @PathVariable int teacherSubjectInstancesId,
            @RequestBody UpdateTeacherSubjectInstanceForm form
    ){
        return teacherSubjectInstanceService.update(teacherSubjectInstancesId,form);
    }

    @DeleteMapping("/teacher-subject-instances/{teacherSubjectInstancesId}")
    public void delete(@PathVariable int teacherSubjectInstancesId){
         teacherSubjectInstanceService.delete(teacherSubjectInstancesId);
    }
}
