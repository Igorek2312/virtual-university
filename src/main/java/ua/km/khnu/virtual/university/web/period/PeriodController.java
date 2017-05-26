package ua.km.khnu.virtual.university.web.period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.km.khnu.virtual.university.model.Group;
import ua.km.khnu.virtual.university.model.Period;
import ua.km.khnu.virtual.university.model.TeacherSubjectInstance;
import ua.km.khnu.virtual.university.refrence.Semester;
import ua.km.khnu.virtual.university.repositories.ClassroomRepository;
import ua.km.khnu.virtual.university.repositories.GroupRepository;
import ua.km.khnu.virtual.university.repositories.TeacherSubjectInstanceRepository;
import ua.km.khnu.virtual.university.service.PeriodService;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Igor Rybak
 */
@Controller
@SessionAttributes({"year", "semesterNumber"})
public class PeriodController {
    private final PeriodService periodService;
    private final ClassroomRepository classroomRepository;
    private final TeacherSubjectInstanceRepository teacherSubjectInstanceRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public PeriodController(PeriodService periodService, ClassroomRepository classroomRepository, TeacherSubjectInstanceRepository teacherSubjectInstanceRepository, GroupRepository groupRepository) {
        this.periodService = periodService;
        this.classroomRepository = classroomRepository;
        this.teacherSubjectInstanceRepository = teacherSubjectInstanceRepository;
        this.groupRepository = groupRepository;
    }

    @ModelAttribute("year")
    public int year(@RequestParam("year") int year) {
        return year;
    }

    @ModelAttribute("semesterNumber")
    public int semesterNumber(@RequestParam("semester_number") int semesterNumber) {
        return semesterNumber;
    }

    @ModelAttribute("period")
    public Period period() {
        return new Period();
    }

    @ModelAttribute("periodToEdit")
    public Period period(@PathVariable(required = false) Integer periodId) {
        if (periodId == null) return null;
        return periodService.get(periodId);
    }

    private void initModel(Model model, int groupId, int year, int semesterNumber) {
        model.addAttribute("year",year);
        model.addAttribute("semesterNumber", semesterNumber);

        List<Period> periods = periodService.periodsSchedule(groupId, year, semesterNumber);
        model.addAttribute("periods", periods);
        model.addAttribute("classrooms", classroomRepository.findAll());

        Semester semester = new Semester(year, semesterNumber);
        LocalDate dateBegin = semester.getDateBegin();
        LocalDate dateEnd = semester.getDateEnd();
        List<TeacherSubjectInstance> tsi = teacherSubjectInstanceRepository.findByGroupAndSemester(groupId, dateBegin, dateEnd);
        model.addAttribute("teacherSubjectInstances", tsi);
    }

    @GetMapping("/groups/{groupId}/periods")
    public String getPeriods(
            @PathVariable int groupId,
            @RequestParam("year") int year,
            @RequestParam("semester_number") int semesterNumber,
            Model model
    ) {
        initModel(model, groupId, year, semesterNumber);

        Group group = groupRepository.findOne(groupId);
        model.addAttribute(group);

        return "period/periods";
    }

    @GetMapping("/groups/{groupId}/edit-period/{periodId}")
    public String getPeriodToEdit(
            @PathVariable int groupId,
            @RequestParam int year,
            @RequestParam("semester_number") int semesterNumber,
            Model model
    ) {
        initModel(model, groupId, year, semesterNumber);
        return "period/edit-period";
    }

    @PostMapping("/groups/{groupId}/periods")
    public String postPeriod(
            @ModelAttribute("period") Period period,
            @ModelAttribute("year") int year,
            @ModelAttribute("semesterNumber") int semesterNumber,
            RedirectAttributes attributes
    ) {
        periodService.save(period);

        attributes.addAttribute("year", year);
        attributes.addAttribute("semester_number", semesterNumber);

        return "redirect:/groups/{groupId}/periods";
    }

    @PostMapping("/groups/{groupId}/edit-period/{periodId}")
    public String updatePeriod(
            @ModelAttribute("period") Period period,
            @ModelAttribute("year") int year,
            @ModelAttribute("semesterNumber") int semesterNumber,
            RedirectAttributes attributes
    ) {
        periodService.save(period);
        attributes.addAttribute("year", year);
        attributes.addAttribute("semester_number", semesterNumber);
        return "redirect:/groups/{groupId}/periods";
    }

    @GetMapping("/groups/{groupId}/delete-period/{periodId}")
    public String deletePeriod(
            @ModelAttribute("year") int year,
            @ModelAttribute("semesterNumber") int semesterNumber,
            @PathVariable int periodId,
            RedirectAttributes attributes
    ) {
        periodService.delete(periodId);
        attributes.addAttribute("year", year);
        attributes.addAttribute("semester_number", semesterNumber);
        return "redirect:/groups/{groupId}/periods";
    }
}
