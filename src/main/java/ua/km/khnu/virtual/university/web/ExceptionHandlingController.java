package ua.km.khnu.virtual.university.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @author Igor Rybak
 */
@ControllerAdvice
public class ExceptionHandlingController {
    @Autowired
    private MessageSource messageSource;
/*
    @ExceptionHandler(CustomException.class)
    public String handle(CustomException e, Model model) {
        String message = messageSource.getMessage(
                e.getDetailsMessage(),
                e.getMessageArgs(),
                "",
                LocaleContextHolder.getLocale()
        );

        model.addAttribute("status", e.getHttpStatus().value());
        model.addAttribute("reasonPhrase", e.getHttpStatus().getReasonPhrase());
        model.addAttribute("message", message);
        return "error-page";
    }*/

}
