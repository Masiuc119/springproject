package by.masiuk.springproject.controller;

import by.masiuk.springproject.form.PersonForm;
import by.masiuk.springproject.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping
public class MainController {
    private static List<Person> persons = new ArrayList<Person>();

    static {
        try {
            persons.add(new Person("Olga",
                    "Pertova",
                    "Gorky str",
                    "Minsk",
                    "287324",
                    "olga@gmail.com",
                    new SimpleDateFormat("yyyy-MM-dd").parse("1976-03-09"),
                    "375-29-722-07-08"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //
// Вводится (inject) из application.properties.
    @Value("${welcome.message}")
    private String message;
    @Value("${error.message}")
    private String errorMessage;

    @GetMapping(value = {"/", "/index"})
    public ModelAndView index(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        model.addAttribute("message", message);
        log.info("index was called");
        return modelAndView;
    }

    @GetMapping(value = {"/personList"})
    public ModelAndView personList(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("personList");
        model.addAttribute("persons", persons);
        return modelAndView;
    }

    @GetMapping(value = {"/addPerson"})
    public ModelAndView showAddPersonPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("addPerson");
        PersonForm personForm = new PersonForm();
        model.addAttribute("personForm", personForm);
        return modelAndView;
    }

    @PostMapping(value = {"/addPerson"})
    public ModelAndView savePerson( Model model, //
                                    @Valid @ModelAttribute("personForm")
                                            PersonForm personForm, Errors errors) {
        ModelAndView modelAndView = new ModelAndView();
        if (errors.hasErrors()) {
            modelAndView.setViewName("addPerson");
        }
        else {
            modelAndView.setViewName("personList");
            String firstName = personForm.getFirstName();
            String lastName = personForm.getLastName();
            String street = personForm.getStreet();
            String city = personForm.getCity();
            String zip = personForm.getZip();
            String email = personForm.getEmail();
            Date birthday = (Date)personForm.getBirthday();
            String phone = personForm.getPhone();
            Person newPerson = new Person(firstName, lastName, street, city, zip,
                    email, birthday, phone);
            persons.add(newPerson);
            model.addAttribute("persons", persons);
            log.info("/addPerson - POST was called");
            return modelAndView;
        }
        return modelAndView;
    }
}