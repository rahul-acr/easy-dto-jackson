package com.easydto.jackson;

import com.easydto.jackson.domain.Department;
import com.easydto.jackson.domain.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.easydto.proxy.Dto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class JacksonSerializationTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    @BeforeAll
    public static void setup() {
        Registerer.registerModules(mapper);
    }

    @Test
    public void simpleSerialization() throws Exception {
        Student student = new Student("John", new Department(1, "CST"));
        Dto<Student> dto = Dto.from(student);

        final String json = mapper.writeValueAsString(dto);

        JSONAssert.assertEquals("{\"name\":\"John\",\"dept\":{\"id\":1,\"name\":\"CST\"},\"isEnrolled\":true}", json, JSONCompareMode.STRICT);
    }

    @Test
    public void profileBasedSerialization() throws Exception {
        Student student = new Student("John", new Department(1, "CST"));
        Dto<Student> dto = Dto.from(student, "REST");

        final String json = mapper.writeValueAsString(dto);

        JSONAssert.assertEquals("{\"dept\":{\"id\":1,\"name\":\"CST\"},\"isEnrolled\":true}", json, JSONCompareMode.STRICT);
    }

}
