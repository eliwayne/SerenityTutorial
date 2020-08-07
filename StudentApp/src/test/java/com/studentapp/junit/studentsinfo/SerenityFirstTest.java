package com.studentapp.junit.studentsinfo;

import io.restassured.RestAssured;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Title;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

@RunWith(SerenityRunner.class)
public class SerenityFirstTest {


    @BeforeClass
    public static void init(){
        RestAssured.baseURI="http://localhost:8080/student";
    }

    @Title("This test with a nice title by Title anotation")
    @Test
    public void getAllStudents1(){
        RestAssured.given().when().get("/list").then().log().all().
                statusCode(200);
    }

    @Test
    public void getAllStudents2(){
        SerenityRest.given().when().get("/list").then().log().all().
                statusCode(200);
    }

    @Test
    public void thisIsAFailingTest(){
        SerenityRest.given().when().get("/list").then().log().all().statusCode(233);
    }

    @Pending
    @Test
    public void thisIsAPendingTest(){

    }

    @Ignore
    @Test
    public void thisIsASkippedTest(){

    }

    @Test
    public void thisIsATestWithError(){
        System.out.println("This is an error: " + (5/0));
    }

    @Test
    public void fileNotExist() throws FileNotFoundException {
        File file = new File("C://file.text");
        FileReader fr = new FileReader(file);
    }

    @Manual
    @Test
    public void manualTest() {
    }
}
