package by.masiuk.springproject.service;

import by.masiuk.springproject.entity.Person;
import by.masiuk.springproject.exceptions.ResourceNotFoundException;

import java.util.List;


public interface PersonService {
    List<Person> getAllPerson();
    void addNewPerson(Person person);
    void deletePerson(Person person );
    void editPerson(Person person, Long id);
    Person getById(long id) throws ResourceNotFoundException;
}