import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class EmployeeTests {

    @Test
    public void testEmployee() {
        //Arrange
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 4;
        //Act
        Employee employee = new Employee(firstName, lastName, description, jobYears);
        //Assert
        assertEquals(employee.getFirstName(), firstName);
        assertEquals(employee.getLastName(), lastName);
        assertEquals(employee.getDescription(), description);
        assertEquals(employee.getJobYears(), jobYears);

    }

    @Test
    public void testEmployeeInvalidEmployee(){
        //Arrange
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 4;

        String expectedMessage = "Invalid Employee";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Employee(firstName, lastName, description, jobYears);
        });
        //Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testFirstNamenotNull() {
        //Arrange
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 4;
        //Act
        Employee employee = new Employee(firstName, lastName, description, jobYears);
        //Assert
        assertNotNull(employee.getFirstName());

    }

    @Test
    public void testLastNamenotNull(){

        //Arrange
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 4;
        //Act
        Employee employee = new Employee(firstName, lastName, description, jobYears);
        //Assert
        assertNotNull(employee.getLastName());
    }

    @Test
    public void testDescriptionnotNull(){
        //Arrange
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 4;
        //Act
        Employee employee = new Employee(firstName, lastName, description, jobYears);
        //Assert
        assertNotNull(employee.getDescription());

    }

    @Test
    public void testJobYearsnotNull(){
        //Arrange
        String firstName = "Frodo";
        String lastName = "Baggins";
        String description = "ring bearer";
        int jobYears = 4;
        //Act
        Employee employee = new Employee("Frodo", "Baggins", "ring bearer", 4);
        //Assert
        assertNotNull(employee.getJobYears());
    }
}
