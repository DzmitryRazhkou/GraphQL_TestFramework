package com.qa.graphql.tests;

import com.qa.pojos.GraphQLQuery;
import com.qa.pojos.QueryVariable;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GraphQlQuery_TestPojos {

    @Test
    public void getAllUsers_POJOS() {

        RestAssured.baseURI = "https://hasura.io";
        GraphQLQuery qlQuery = new GraphQLQuery();

        qlQuery.setQuery("query ($limit: Int!, $name: String!) {\n" +
                "  users(limit: $limit, where: {name: {_eq: $name}}) {\n" +
                "    id\n" +
                "    name\n" +
                "  }\n" +
                "}");

        QueryVariable variable = new QueryVariable();
        variable.setLimit(5);
        variable.setName("dassad");

        qlQuery.setVariable(variable);

        given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDYyMWYxNGZlYmY4ZWZmMDA2ODNlZjZmYSJ9LCJuaWNrbmFtZSI6ImR6bWl0cnlyYXpoa291IiwibmFtZSI6ImR6bWl0cnlyYXpoa291QGdtYWlsLmNvbSIsInBpY3R1cmUiOiJodHRwczovL3MuZ3JhdmF0YXIuY29tL2F2YXRhci9lZDY2N2FiY2E5MDljZjU3NmNiZWZjOGE3YzY5OGM3OT9zPTQ4MCZyPXBnJmQ9aHR0cHMlM0ElMkYlMkZjZG4uYXV0aDAuY29tJTJGYXZhdGFycyUyRmR6LnBuZyIsInVwZGF0ZWRfYXQiOiIyMDIyLTAzLTAyVDA2OjU1OjU5LjY2NFoiLCJpc3MiOiJodHRwczovL2dyYXBocWwtdHV0b3JpYWxzLmF1dGgwLmNvbS8iLCJzdWIiOiJhdXRoMHw2MjFmMTRmZWJmOGVmZjAwNjgzZWY2ZmEiLCJhdWQiOiJQMzhxbkZvMWxGQVFKcnprdW4tLXdFenFsalZOR2NXVyIsImlhdCI6MTY0NjUxMDQyOCwiZXhwIjoxNjQ2NTQ2NDI4LCJhdF9oYXNoIjoiYWl4aDVkMHZ3ekUyXzlMaWZoaTY3dyIsIm5vbmNlIjoiTXgwZXBrcUZSUzcweFQ0OUZ6aFRIRU9RNl80THpwZGUifQ.fg0bmABzOO2nIe3JPmSvOXnbaF-zpYTv0M_cLhNGuqeqERuC0cV-5YXo7hh5xkUdJs538CERhG-n3_Vj_9PaQdG8eWOpIuS6bH17w1BO15Odqwbc19MFIJVjMIqspXDWLjzEs5YFTeiwnDbRgCpo5DF2d09NBM4Wd0wehy7698NADcu7BzzleMXXpTdPMRN4xFmULruko7KRH9Tv6jCcdpVG2xdamGA9jcEGCuL-pyCy3IjdMopx9CimzhQbigTth_RlTebrGkuy8J7NcSjlAxtv95KuSa0ZawCtbXlCpcThoXLzEWI8wWV2lA_vBmm_DuWWLzv-HiqLyHXwY8FQVw")

                .body(qlQuery)
                .when().log().all()
                .post("/learn/graphql")
                .then().log().all()
                .assertThat()
                .statusCode(200);
//                .and()
//                .body("data.users[1].name", equalTo("dassad"));
    }
}

