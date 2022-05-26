package com.qa.graphql.tests;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GraphQlQueryTest {

    @Test
    public void getAllFilmsTest() {

//       https://swapi-graphql.netlify.app/.netlify/functions/index

        RestAssured.baseURI = "https://swapi-graphql.netlify.app";
        String query = "{\"query\":\"{\\n  allFilms {\\n    films{\\n      title\\n    }\\n  }\\n}\\n\\n\",\"variables\":null}";

        given().log().all()
                .contentType("application/json")
                .body(query)
                .when().log().all()
                .post("/.netlify/functions/index")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .and()
                .body("data.allFilms.films[1].title", equalTo("The Empire Strikes Back"));
    }

    @Test
    public void getUsers() {

//        https://hasura.io/learn/graphql

        RestAssured.baseURI = "https://hasura.io";
        String query = "{\"query\":\"{\\n  users(limit: 5) {\\n    id\\n    name\\n  }\\n}\\n\",\"variables\":null}";

        given().log().all()
                .contentType("application/json")
                .header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDYyMWYxNGZlYmY4ZWZmMDA2ODNlZjZmYSJ9LCJuaWNrbmFtZSI6ImR6bWl0cnlyYXpoa291IiwibmFtZSI6ImR6bWl0cnlyYXpoa291QGdtYWlsLmNvbSIsInBpY3R1cmUiOiJodHRwczovL3MuZ3JhdmF0YXIuY29tL2F2YXRhci9lZDY2N2FiY2E5MDljZjU3NmNiZWZjOGE3YzY5OGM3OT9zPTQ4MCZyPXBnJmQ9aHR0cHMlM0ElMkYlMkZjZG4uYXV0aDAuY29tJTJGYXZhdGFycyUyRmR6LnBuZyIsInVwZGF0ZWRfYXQiOiIyMDIyLTAzLTAyVDA2OjU1OjU5LjY2NFoiLCJpc3MiOiJodHRwczovL2dyYXBocWwtdHV0b3JpYWxzLmF1dGgwLmNvbS8iLCJzdWIiOiJhdXRoMHw2MjFmMTRmZWJmOGVmZjAwNjgzZWY2ZmEiLCJhdWQiOiJQMzhxbkZvMWxGQVFKcnprdW4tLXdFenFsalZOR2NXVyIsImlhdCI6MTY0NjUxMDQyOCwiZXhwIjoxNjQ2NTQ2NDI4LCJhdF9oYXNoIjoiYWl4aDVkMHZ3ekUyXzlMaWZoaTY3dyIsIm5vbmNlIjoiTXgwZXBrcUZSUzcweFQ0OUZ6aFRIRU9RNl80THpwZGUifQ.fg0bmABzOO2nIe3JPmSvOXnbaF-zpYTv0M_cLhNGuqeqERuC0cV-5YXo7hh5xkUdJs538CERhG-n3_Vj_9PaQdG8eWOpIuS6bH17w1BO15Odqwbc19MFIJVjMIqspXDWLjzEs5YFTeiwnDbRgCpo5DF2d09NBM4Wd0wehy7698NADcu7BzzleMXXpTdPMRN4xFmULruko7KRH9Tv6jCcdpVG2xdamGA9jcEGCuL-pyCy3IjdMopx9CimzhQbigTth_RlTebrGkuy8J7NcSjlAxtv95KuSa0ZawCtbXlCpcThoXLzEWI8wWV2lA_vBmm_DuWWLzv-HiqLyHXwY8FQVw")
                .body(query)
                .when().log().all()
                .post("/learn/graphql")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .and()
                .body("data.users[1].name", equalTo("dassad"));
    }

    @DataProvider
    public Object[][] getQueryData() {
        return new Object[][]{{"5"},
                               {"2"}};
    }

    @Test(dataProvider = "getQueryData")
    public void getAllUsersTestWithData(String limit) {

        RestAssured.baseURI = "https://hasura.io";
        String query = "{\"query\":\"{\\n  users(limit:"+limit+") {\\n    id\\n    name\\n  }\\n}\\n\",\"variables\":null}";

        given().log().all()
                .contentType("application/json")
                .header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDYyMWYxNGZlYmY4ZWZmMDA2ODNlZjZmYSJ9LCJuaWNrbmFtZSI6ImR6bWl0cnlyYXpoa291IiwibmFtZSI6ImR6bWl0cnlyYXpoa291QGdtYWlsLmNvbSIsInBpY3R1cmUiOiJodHRwczovL3MuZ3JhdmF0YXIuY29tL2F2YXRhci9lZDY2N2FiY2E5MDljZjU3NmNiZWZjOGE3YzY5OGM3OT9zPTQ4MCZyPXBnJmQ9aHR0cHMlM0ElMkYlMkZjZG4uYXV0aDAuY29tJTJGYXZhdGFycyUyRmR6LnBuZyIsInVwZGF0ZWRfYXQiOiIyMDIyLTAzLTAyVDA2OjU1OjU5LjY2NFoiLCJpc3MiOiJodHRwczovL2dyYXBocWwtdHV0b3JpYWxzLmF1dGgwLmNvbS8iLCJzdWIiOiJhdXRoMHw2MjFmMTRmZWJmOGVmZjAwNjgzZWY2ZmEiLCJhdWQiOiJQMzhxbkZvMWxGQVFKcnprdW4tLXdFenFsalZOR2NXVyIsImlhdCI6MTY0NjUxMDQyOCwiZXhwIjoxNjQ2NTQ2NDI4LCJhdF9oYXNoIjoiYWl4aDVkMHZ3ekUyXzlMaWZoaTY3dyIsIm5vbmNlIjoiTXgwZXBrcUZSUzcweFQ0OUZ6aFRIRU9RNl80THpwZGUifQ.fg0bmABzOO2nIe3JPmSvOXnbaF-zpYTv0M_cLhNGuqeqERuC0cV-5YXo7hh5xkUdJs538CERhG-n3_Vj_9PaQdG8eWOpIuS6bH17w1BO15Odqwbc19MFIJVjMIqspXDWLjzEs5YFTeiwnDbRgCpo5DF2d09NBM4Wd0wehy7698NADcu7BzzleMXXpTdPMRN4xFmULruko7KRH9Tv6jCcdpVG2xdamGA9jcEGCuL-pyCy3IjdMopx9CimzhQbigTth_RlTebrGkuy8J7NcSjlAxtv95KuSa0ZawCtbXlCpcThoXLzEWI8wWV2lA_vBmm_DuWWLzv-HiqLyHXwY8FQVw")
                .body(query)
                .when().log().all()
                .post("/learn/graphql")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .and()
                .body("data.users[0].name", equalTo("tui.glen"));
    }

    //    Parameterized two variable
    @DataProvider
    public Object[][] getQueryDataNameLimit() {
        return new Object[][]{{"5", "tui.glen"},
                               {"2", "dassad"}};
    }

    @Test(dataProvider = "getQueryDataNameLimit")
    public void getAllUsersTestWithData_Limit_Name(String limit, String name) {

        RestAssured.baseURI = "https://hasura.io";
        String query = "{\"query\":\"{\\n  users(limit: "+limit+", where: {name: {_eq: \\\""+name+"\\\"}}) {\\n    id\\n    name\\n  }\\n}\\n\",\"variables\":null}";

        given().log().all()
                .contentType("application/json")
                .header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDYyMWYxNGZlYmY4ZWZmMDA2ODNlZjZmYSJ9LCJuaWNrbmFtZSI6ImR6bWl0cnlyYXpoa291IiwibmFtZSI6ImR6bWl0cnlyYXpoa291QGdtYWlsLmNvbSIsInBpY3R1cmUiOiJodHRwczovL3MuZ3JhdmF0YXIuY29tL2F2YXRhci9lZDY2N2FiY2E5MDljZjU3NmNiZWZjOGE3YzY5OGM3OT9zPTQ4MCZyPXBnJmQ9aHR0cHMlM0ElMkYlMkZjZG4uYXV0aDAuY29tJTJGYXZhdGFycyUyRmR6LnBuZyIsInVwZGF0ZWRfYXQiOiIyMDIyLTAzLTAyVDA2OjU1OjU5LjY2NFoiLCJpc3MiOiJodHRwczovL2dyYXBocWwtdHV0b3JpYWxzLmF1dGgwLmNvbS8iLCJzdWIiOiJhdXRoMHw2MjFmMTRmZWJmOGVmZjAwNjgzZWY2ZmEiLCJhdWQiOiJQMzhxbkZvMWxGQVFKcnprdW4tLXdFenFsalZOR2NXVyIsImlhdCI6MTY0NjQ2NDM5NCwiZXhwIjoxNjQ2NTAwMzk0LCJhdF9oYXNoIjoiMUl3OU1jcXJCZTUwSjZzMVFLWnRWZyIsIm5vbmNlIjoiTkc2VjB1OEVxTkl0TEhRaFU0OVRZLnFoQndRflVTbEwifQ.M9rgymqL-luGdaq9shuY0PTxQzfLAHsVrOEOKhRUUkEgi0ltsL8NRg0_ywHCkoXv-wBvloTenECYiLMs4PotvUn4GIY_EDf6X0JIPn90QmADkJIKtOI5Q4X-Mx3WJo-GKZpLUAigshjJ8vbfKP7C192_mL69ctUh9vQMvVFfZMgoCdqjd-AFBBe5wUjilwMLjTT79I-KSfMbRGrC_T8Urww1P--scP9iumaVFrTV3bJKfVdSN_RT-LVaHvw4P7JMggqZSNOCywODlEq8-tOHaqLjMg0XGQHMJTa7xIytSipDPWuUH9RARm6q7bNhEFhLYcKFwINbpke4Hc1qzKwJmA")
                .body(query)
                .when().log().all()
                .post("/learn/graphql")
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }


    //    Parameterized More than two variable
    @DataProvider
    public Object[][] getQueryDataMore() {
        return new Object[][]{{"5", "scott", "Something more"}};
    }

    @Test(dataProvider = "getQueryDataMore")
    public void getAllUsersTestWithDataMore (String limit, String name, String title) {

        RestAssured.baseURI = "https://hasura.io";
        String query = "{\"query\":\"{\\n  online_users(where: {user: {name: {_eq: \\\""+name+"\\\"}}}) {\\n    id\\n    user {\\n      id\\n      name\\n    }\\n  }\\n  " +
                "todos(limit: "+limit+", where: {title: {_eq: \\\""+title+"\\\"}}) {\\n    user {\\n      id\\n      name\\n    }\\n  }\\n}\\n\",\"variables\":null}";

        given().log().all()
                .contentType("application/json")
                .header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDYyMWYxNGZlYmY4ZWZmMDA2ODNlZjZmYSJ9LCJuaWNrbmFtZSI6ImR6bWl0cnlyYXpoa291IiwibmFtZSI6ImR6bWl0cnlyYXpoa291QGdtYWlsLmNvbSIsInBpY3R1cmUiOiJodHRwczovL3MuZ3JhdmF0YXIuY29tL2F2YXRhci9lZDY2N2FiY2E5MDljZjU3NmNiZWZjOGE3YzY5OGM3OT9zPTQ4MCZyPXBnJmQ9aHR0cHMlM0ElMkYlMkZjZG4uYXV0aDAuY29tJTJGYXZhdGFycyUyRmR6LnBuZyIsInVwZGF0ZWRfYXQiOiIyMDIyLTAzLTAyVDA2OjU1OjU5LjY2NFoiLCJpc3MiOiJodHRwczovL2dyYXBocWwtdHV0b3JpYWxzLmF1dGgwLmNvbS8iLCJzdWIiOiJhdXRoMHw2MjFmMTRmZWJmOGVmZjAwNjgzZWY2ZmEiLCJhdWQiOiJQMzhxbkZvMWxGQVFKcnprdW4tLXdFenFsalZOR2NXVyIsImlhdCI6MTY0NjUxMDQyOCwiZXhwIjoxNjQ2NTQ2NDI4LCJhdF9oYXNoIjoiYWl4aDVkMHZ3ekUyXzlMaWZoaTY3dyIsIm5vbmNlIjoiTXgwZXBrcUZSUzcweFQ0OUZ6aFRIRU9RNl80THpwZGUifQ.fg0bmABzOO2nIe3JPmSvOXnbaF-zpYTv0M_cLhNGuqeqERuC0cV-5YXo7hh5xkUdJs538CERhG-n3_Vj_9PaQdG8eWOpIuS6bH17w1BO15Odqwbc19MFIJVjMIqspXDWLjzEs5YFTeiwnDbRgCpo5DF2d09NBM4Wd0wehy7698NADcu7BzzleMXXpTdPMRN4xFmULruko7KRH9Tv6jCcdpVG2xdamGA9jcEGCuL-pyCy3IjdMopx9CimzhQbigTth_RlTebrGkuy8J7NcSjlAxtv95KuSa0ZawCtbXlCpcThoXLzEWI8wWV2lA_vBmm_DuWWLzv-HiqLyHXwY8FQVw")
                .body(query)
                .when().log().all()
                .post("/learn/graphql")
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }
}
