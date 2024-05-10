package ru.HollowKaeden.service;

import lombok.AllArgsConstructor;
import ru.HollowKaeden.DTO.CourseDTO;
import ru.HollowKaeden.entity.Course;
import ru.HollowKaeden.repository.CourseRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository repository;

    public Course addCourse(CourseDTO dto) {
        Course course = Course.builder()
                .name(dto.getName())
                .image(dto.getImage())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .date(dto.getDate())
                .build();
        return repository.save(course);
    }

    public List<Course> readAll() {
        return repository.findAll();
    }

    public Course update(Course user) {
        return repository.save(user);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void deleteByName(String name) {
        delete(repository.findByName(name).getId());
    }

    public Course getByID(Long id) {return repository.getReferenceById(id);}
}