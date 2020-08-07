package com.studentapp.cucumber.serenity;

import com.serenityrestassured.model.StudentClass;
import com.studentapp.utills.ReusableSpecifications;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import java.util.HashMap;
import java.util.List;

public class StudentSerenitySteps {

    @Step("create a student with first name:{0}, last name: {1},...")
    public ValidatableResponse createStudent(String firstName,
         String lastName, String email, String programme,
         List<String> courses){

        StudentClass student = new StudentClass();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setProgramme(programme);
        student.setEmail(email);
        student.setCourses(courses);

        return SerenityRest.rest().given()
                .spec(ReusableSpecifications.getGenericRequestSpec())
                .when().body(student).post().then();
    }

    @Step("get student info with first name: {0}")
    public HashMap<String, Object> getStudentByFirstName(String firstName){
        String p1 = "findAll{it.firstName=='";
        String p2 = "'}.get(0)";

        return SerenityRest.rest().given().when()
                        .get("/list").then()
                        .statusCode(200)
                        .extract()
                        .path(p1 + firstName + p2);
    }

    @Step
    public ValidatableResponse updateStudent(int studentid, String firstName,
         String lastName, String email, String programme, List<String> courses){

        StudentClass student = new StudentClass();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setProgramme(programme);
        student.setEmail(email);
        student.setCourses(courses);

        return SerenityRest.rest().given()
                .spec(ReusableSpecifications.getGenericRequestSpec())
                .when().body(student).put("/"+studentid).then();
    }

    @Step
    public void deleteStudent(int studentid){

        SerenityRest.rest().given().when().delete("/"+studentid);
    }

    @Step
    public ValidatableResponse getStudentById(int studentid){

        return SerenityRest.rest().given().when()
                .get("/"+studentid).then();
    }
}
