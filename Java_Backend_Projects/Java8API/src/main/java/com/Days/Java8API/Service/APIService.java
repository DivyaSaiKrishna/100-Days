package com.Days.Java8API.Service;

import com.Days.Java8API.Model.Employee;
import com.Days.Java8API.Model.LamdaModel;


import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class APIService {

    //Sort list of string by using Lamda functions
    //asc small to big , desc big to small
    public List<String> sortStringList(LamdaModel lamdaModel){
        List<String> inputModel = lamdaModel.getLamdaList();
        String sortStyle = lamdaModel.getSortStyle();
        List<String> returnString =new ArrayList<>();
        if(sortStyle.equals("asc")){
            inputModel.sort((a,b)->a.compareTo(b));
        }if(sortStyle.equals("desc")){
            inputModel.sort((a,b)->b.compareTo(a));
        }
        return inputModel;
    }

    //Functional Interface
    @FunctionalInterface
    interface Square{
        int calculate(int x);
    }

    public Integer squareNumber(int x){
        Square s = (a) -> a*a;
        int result = s.calculate(x);
        return result;
    }

    public Object squareRoot(double number){
        Function<Double, Object> factorial = n -> {
            long  result = 1;
            for(int i=1;i<=number ;i++){
                    result*=i;
            }
            return result;
        };
        Double ans = Double.valueOf(number);
        Object factResult = factorial.apply(ans);
        return factResult;
    }



    //Stream API
    public Double averageList(List<Integer> integerList){
        return integerList.stream().collect(Collectors.averagingInt(n->n*1));
    }


    public  List<Employee> getEmployeeList(){
        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(new Employee("atest","abc","male","atest@100days.com",20));
        employeeList.add(new Employee("btest","abc","female","btest@100days.com",23));
        employeeList.add(new Employee("dtest","bac","female","dtest@100days.com",26));
        employeeList.add(new Employee("ctest","dbc","male","ctest@100days.com",17));
        employeeList.add(new Employee("gtest","bck","female","gtest@100days.com",35));
        return employeeList;
    }

    public Supplier<Employee> getEmployeeSupplier(){
        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(new Employee("atest","abc","male","atest@100days.com",20));
        employeeList.add(new Employee("btest","abc","female","btest@100days.com",23));
        employeeList.add(new Employee("dtest","bac","female","dtest@100days.com",26));
        employeeList.add(new Employee("ctest","dbc","male","ctest@100days.com",17));
        employeeList.add(new Employee("gtest","bck","female","gtest@100days.com",35));
        return () -> {
            if (!employeeList.isEmpty()) {
                return employeeList.remove(0);
            } else {
                throw new IllegalStateException("No more employees in the list.");
            }
        };
    }

    public  void getGenderBasedEmployee(){
        List<Employee> femaleEmployeeList = getEmployeeList().stream().filter(employee -> employee.getGender().equalsIgnoreCase("female")).collect(Collectors.toList());
        femaleEmployeeList.forEach(System.out::println);
    }

    //predicate
    boolean isEmployeeValid(Employee employee, Predicate<Employee> employeePredicate){
        return employeePredicate.test(employee);
    }
    public void setEmployeePredicate(){
        List<Employee> employeeList = getEmployeeList();

        Predicate<Employee> employeePredicate = employee -> employee.getAge() < 25 ;
        System.out.println("---------employeePredicate------");
        employeeList.stream().forEach(employee -> System.out.println(String.valueOf(employee.getAge())+":"+isEmployeeValid(employee,employeePredicate)) );
        System.out.println("----------------------------------");

        Predicate<Employee> employeePredicateName = employee ->  employee.getLastname().contains("a");
        employeeList.stream().forEach(employee -> System.out.println(String.valueOf(employee.getLastname())+":"+isEmployeeValid(employee,employeePredicateName)));
        System.out.println("----------------------------------");

        Predicate<Employee> employeePredicateAnd = employeePredicate.and(employeePredicateName);
        employeeList.stream().forEach(employee -> System.out.println(String.valueOf(employee.getAge()+":"+employee.getLastname())+":" +isEmployeeValid(employee,employeePredicateAnd)));
        System.out.println("----------------------------------");

        Predicate<Employee> employeePredicateOr = employeePredicate.or(employeePredicateName);
        employeeList.stream().forEach(employee -> System.out.println(String.valueOf(employee.getAge()+":"+employee.getLastname())+":" + isEmployeeValid(employee,employeePredicateOr)));
        System.out.println("----------------------------------");

    }

    //Default methods
    interface EmployeeInterface{
        abstract int square(int x);

        default String sayPresent(){
            return "Present";
        }
    }

    public static class EmployeeCheck implements EmployeeInterface{
        @Override
        public int square(int x) {
            return x*x;
        }
        public void printEmployee(){
            System.out.println(new EmployeeCheck().sayPresent());
        }
    }

    //Date and Time API
    public void dateDiffrence(){
        LocalDate date =  LocalDate.now();
        LocalDate date1 = LocalDate.now().plusDays(20);
    }

    //optional
    public void EmployeeOptional(){
        Optional<Employee> employeeOptional = (Optional<Employee>) Optional.ofNullable(getEmployeeSupplier().get());
        Optional<String> getReturnList = null;
        if(employeeOptional.isPresent()){
            getReturnList = employeeOptional.
                    filter(employee -> employee.getAge()>25).
                    map(Employee::getFirstName);
        }
        System.out.println(getReturnList);
    }

    //CompletableFuture
    public void task(){
        List<Integer> integerList = new ArrayList<>();
        integerList = IntStream.rangeClosed(1,100).boxed().collect(Collectors.toList());
        integerList.stream().forEach(number -> System.out.println(number));
        integerList.parallelStream().mapToInt(Integer::intValue).sum();
    }
    public void completableFuture(){
        CompletableFuture.supplyAsync(getEmployeeSupplier());
        System.out.println("-------------");
        CompletableFuture.supplyAsync(getEmployeeSupplier());
    }




}
