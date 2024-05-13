
import java.util.*;
import java.util.stream.Collectors;

public class App {

    static List<Employee> employeeList = new ArrayList<>();


    public static void main(String[] args) throws Exception {

        EmployeeFactory employeeFactory = new EmployeeFactory();
        employeeList = employeeFactory.getAllEmployee();

        // TODO
        // QnA
       /* TODO : 1 List all distinct project in non-ascending order.
        first flatten the list of projects from employee
        flatmap() converts their list of projects into a stream of projects.
        */
        List<Project> projectList = employeeList.stream()
                .flatMap(employee -> employee.getProjects().stream()).collect(Collectors.toList());

        List<Project> sortedProjectList = projectList.stream()
                                            .distinct()
                .sorted(Comparator.comparing(Project::getName).reversed())
                .collect(Collectors.toList());


        sortedProjectList.forEach(project -> System.out.println(project.getName()));



    // TODO 2 Print full name of any employee whose firstName starts with ‘A’.
     Optional<Employee> employeeStartsWithA = employeeList.stream().filter(employee -> employee.getFirstName().startsWith("A")).findAny();
     System.out.println("\n\n 2 . Full name employee starts with A = "+employeeStartsWithA.get().getFirstName()+ " "+employeeStartsWithA.get().getLastName());



    //3 TODO List of all employee who joined in year 2023 (year to be extracted from employee id i.e., 1st 4 characters)
              List<Employee> joined2023=employeeList.stream().filter(employee -> employee.getId().substring(0,4).equals("2023")).collect(Collectors.toList());
              joined2023.forEach(employee -> System.out.println("Emp id = "+employee.getId()));

    //TODO 4. Sort employees based on firstName, for same firstName sort by salary.
        List<Employee> sortedEmployees = employeeList.stream()
                .sorted(Comparator.comparing(Employee::getFirstName)
                        .thenComparing(Employee::getSalary))
                .collect(Collectors.toList());

        sortedEmployees.forEach(employee -> System.out.println(employee.getFirstName() + " " + employee.getSalary()));

    // TODO 5. Print names of all employee with 3rd highest salary. (generalise it for nth highest salary)
        int n=3;
        List<Employee> employees = employeeList.stream().distinct().sorted(Comparator.comparing(Employee::getSalary).reversed()).collect(Collectors.toList());
        System.out.println("3rd highest salary = "+ employees.get(n-1).getSalary());
        List<Employee> ans = employeeList.stream().filter(employee -> employee.getSalary()==employees.get(n-1).getSalary()).collect(Collectors.toList());

        ans.forEach(employee -> System.out.println("Third highest salary = "+employee.getSalary() + " name = "+employee.getFirstName()));

        //TODO 6. Print min salary.
            OptionalInt minSal = employeeList.stream().mapToInt(Employee::getSalary).min();
               System.out.println("Minimum salary  "+minSal.getAsInt());

    //TODO 7.Print list of all employee with min salary.
        employeeList.stream().filter(employee -> employee.getSalary()==minSal.getAsInt()).forEach(employee -> System.out.println ("  "+ employee.getFirstName() +" min salary = "+ employee.getSalary()));

    //TODO 8. Print  List of people working on more than 2 projects.
     employeeList.stream().filter(employee -> employee.getProjects().size()>2).forEach(people -> System.out.println(people.getFirstName()));

     //TODO 9.Count of total laptops assigned to the employees.
        int countTotalLaptopsAssignedToEmployees = (int) employeeList.stream().map(Employee::getTotalLaptopsAssigned).count();
        System.out.println("Count Total Laptops : "+countTotalLaptopsAssignedToEmployees);

     //TODO 10 Count of all projects with Robert Downey Jr as PM.
        long robertDowneyJRASPM = employees.stream().flatMap(employee -> employee.getProjects().stream()).filter(project -> project.getProjectManager().equalsIgnoreCase("Robert Downey Jr")).count();
        System.out.println("Count of all projects with Robert Downey Jr as PM." + robertDowneyJRASPM);
    }
}