package com.esiee.tp2.model;

import com.esiee.tp2.domain.Civility;
import com.esiee.tp2.domain.Function;
import com.esiee.tp2.domain.Person;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Datamodel {
    private static Datamodel datamodelInstance;

    public static Datamodel getInstance() {
        if(datamodelInstance == null) {
            datamodelInstance  = new Datamodel();
        }
        return datamodelInstance;
    }

    private Map<Long, Person> persons;
    private Map<Long, Civility> civilites;
    private Map<Long, Function> functions;

    private Map<String, Person> indexedPersonByLogin;

    public Datamodel() {
        //persons = new HashMap<Long, Person>();
       initData();
    }

    private void initData() {
        initCivilities();
        initFunctions();
        initPersons();
    }

    private void initCivilities(){
        civilites = new HashMap<Long, Civility>();

        Civility mrCivility = new Civility();
        mrCivility.setId(new Long(1));
        mrCivility.setCode("M.");
        mrCivility.setLabel("M.");
        civilites.put(mrCivility.getId(), mrCivility);

        Civility mmCivility = new Civility();
        mmCivility.setId(new Long(2));
        mmCivility.setCode("Mme");
        mmCivility.setLabel("Mme");
        civilites.put(mmCivility.getId(), mmCivility);
    }

    private void initFunctions(){
        functions = new HashMap<Long, Function>();

        Function manager = new Function();
        manager.setId(new Long(1));
        manager.setCode("MANAGER");
        manager.setLabel("manager");
        functions.put(manager.getId(), manager);

        Function employee = new Function();
        employee.setId(new Long(2));
        employee.setCode("EMPLOYEE");
        employee.setLabel("employee");
        functions.put(employee.getId(), employee);
    }

    private void initPersons() {
        persons = new HashMap<Long, Person>();
        indexedPersonByLogin = new HashMap<String, Person>();

        Person personOne = new Person();
        personOne.setId(new Long(1));
        personOne.setFirstname("John");
        personOne.setLastname("Doe");
        personOne.setLogin("john.doe");
        personOne.setPassword("pass");
        personOne.setMail("john.doe@gmail.com");
        personOne.setMobilePhone("0443234245");
        personOne.setCivility(civilites.get(new Long(1)));
        personOne.setFunction(functions.get(new Long(1)));
        persons.put(personOne.getId(), personOne);
        indexedPersonByLogin.put(personOne.getLogin(), personOne);

        Person personTwo = new Person();
        personTwo.setId(new Long(1));
        personTwo.setFirstname("Anna");
        personTwo.setLastname("Doe");
        personTwo.setLogin("anna.doe");
        personTwo.setPassword("pass");
        personTwo.setMail("anna.doe@gmail.com");
        personTwo.setMobilePhone("9394393943");
        personTwo.setCivility(civilites.get(new Long(2)));
        personTwo.setFunction(functions.get(new Long(2)));
        persons.put(personTwo.getId(), personTwo);
        indexedPersonByLogin.put(personTwo.getLogin(), personTwo);

    }

    public List<Person> getPersons() {
        List<Person> personList = new ArrayList<Person>(persons.values());
        return personList;
    }

    public Person getPerson(Long id) {
        Person person = persons.get(id);
        return person;
    }

    public Person getPersonByLogin(String usernamelogin) {
        Person person = persons.get(usernamelogin);
        return person;
    }

    public List<Civility> getCivilities() {
        List<Civility> civilityList = new ArrayList<Civility>(civilites.values());
        return civilityList;
    }

    public Civility getCivility(Long id) {
        Civility civility = civilites.get(id);
        return civility;
    }

    public List<Function> getFunctions() {
        List<Function> functionList = new ArrayList<Function>(functions.values());
        return functionList;
    }

    public Function getFunction(Long id) {
        Function function = functions.get(id);
        return function;
    }
}
