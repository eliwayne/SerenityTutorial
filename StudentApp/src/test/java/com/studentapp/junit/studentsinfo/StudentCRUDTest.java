package com.studentapp.junit.studentsinfo;

import com.openhtmltopdf.extend.FSTextBreaker;
import com.serenityrestassured.model.StudentClass;
import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.testbase.TestBase;
import com.studentapp.utills.ReusableSpecifications;
import com.studentapp.utills.TestUtils;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentCRUDTest extends TestBase {

    static int studentId;
    static String firstName = "Bruce";
    static String lastName = "Wayne";
    static String programme = "Super Hero";
    static String email = TestUtils.getRandomValue() + "bruce.wayne@wayne.com";

    @Steps
    StudentSerenitySteps steps;

    @Title("create a new student")
    @Test
    public void test001(){
        ArrayList<String> courses = new ArrayList<>();
        courses.add("Martial Arts");
        courses.add("Detective");

        steps.createStudent(firstName, lastName, email, programme, courses)
        .statusCode(201)
        .spec(ReusableSpecifications.getGenericResponseSpec());
    }

    @Test
    public void test002(){
        HashMap<String, Object> value = steps.getStudentByFirstName(firstName);

        assertThat(value, hasValue(firstName));
        studentId = (int) value.get("id");
    }

    @Title("Update the user info and verify the updated info")
    @Test
    public void test003(){

        ArrayList<String> courses = new ArrayList<>();
        courses.add("Martial Arts");
        courses.add("Detective");

        email = "batman@gmail.com";

        steps.updateStudent(studentId, firstName, lastName,email, programme, courses);

        HashMap<String, Object> value = steps.getStudentByFirstName(firstName);
        assertThat(value, hasValue(email));
    }

    @Title("delete record and verify it is deleted")
    @Test
    public void test004(){
        steps.deleteStudent(studentId);

        steps.getStudentById(studentId).statusCode(404);
    }
}
