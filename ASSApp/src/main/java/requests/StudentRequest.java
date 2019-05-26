package requests;

import dtos.StudentDto;
import entities.Student;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import parameters.Settings;

import java.util.List;

public class StudentRequest {
    public List<StudentDto> getStudents() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<StudentDto>> response = restTemplate.exchange(
                Settings.URL + "/students",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<StudentDto>>() {
                });
        return response.getBody();
    }

    public StudentDto getStudent(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<StudentDto> responseEntity = restTemplate.getForEntity(Settings.URL + "/student/" + id, StudentDto.class);
        return responseEntity.getBody();
    }

    public Student saveStudent(Student student) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Student> requestUpdate = new HttpEntity<>(student, null);
        ResponseEntity<Student> response = restTemplate.exchange(Settings.URL + "/student", HttpMethod.POST, requestUpdate, Student.class);
        return response.getBody();
    }

    public void deleteStudent(Student student) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Student> requestUpdate = new HttpEntity<>(student, null);
        restTemplate.exchange(Settings.URL + "/student/" + student.getId(), HttpMethod.DELETE, requestUpdate, Student.class);
    }
}
