package com.janak.streamapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class StreamApiApplication {

    static List<Employee> employees = new ArrayList<>();
    static {
        employees.add(new Employee("janak","adhikari",4000.0, List.of("Project1","Project2")));
        employees.add(new Employee("Niki","adhikari",5000.0, List.of("Project3","Project4")));
        employees.add(new Employee("Jiaa","adhikari",6000.0, List.of("Project5","Project6")));
        employees.add(new Employee("janak1","adhikari",2000.0, List.of("Project7","Project77")));
        employees.add(new Employee("Niki1","adhikari",3000.0, List.of("Project8","Project88")));
        employees.add(new Employee("Jiaa1","adhikari",5000.0, List.of("Project9","Project99")));
    }


    public static void main(String[] args) {
        //SpringApplication.run(StreamApiApplication.class, args);

        //foreach - terminal operation
        employees.stream()
                .forEach(employee -> System.out.println(employee));


        //map intermediate operation
        //collect
        System.out.println("**********map and collect operation ************");
         List<Employee> increasedSalary = employees.stream()
                .map(employee -> new Employee(
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getSalary() * 1.10,
                        employee.getProjects()
                )).collect(Collectors.toList());
        System.out.println(increasedSalary);

        //filter intermediate operation
        System.out.println("**********filter operation ************");
        List<Employee> employeeList = employees
                .stream()
                .filter(employee -> employee.getSalary() >= 4000.0)
                .map(employee -> new Employee(
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getSalary(),
                        employee.getProjects()))
                .collect(Collectors.toList());
        System.out.println(employeeList);


        System.out.println("**********find first operation ************");

        Employee employee1 = employees
                .stream()
                .filter(employee -> employee.getSalary()>=8000.0)
                .map(employee -> new Employee(
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getSalary(),
                        employee.getProjects()
                ))
                .findFirst()
                .orElse(null);
        System.out.println(employee1);

        System.out.println("********** flat map operation ************");

        String projects = employees.stream()
                .map(employee -> employee.getProjects())
                .flatMap(strings -> strings.stream())
                .collect(Collectors.joining(","));
        System.out.println(projects);


        System.out.println("********** Short Circuit operation ************");

        List<Employee> shortCircuitData = employees.stream()
                .skip(4)
                //.limit(1)
                .collect(Collectors.toList());
        System.out.println(shortCircuitData);

        System.out.println("********** Finite data ************");

        Stream.generate(Math::random)
                .limit(4)
                .forEach(x-> System.out.println(x));

    }

}
