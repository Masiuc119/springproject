package by.masiuk.springproject.service;

import by.masiuk.springproject.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<Person> getAllPerson();
    void addNewPerson(Person person);
    void deletePerson(Person person );
    void editPerson(Person person);
    Optional<Person> getById(long id);
}