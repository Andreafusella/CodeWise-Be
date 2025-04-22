package com.codeWise.codeWise.service;

import com.codeWise.codeWise.dto.request.NewExerciseDto;
import com.codeWise.codeWise.exception.EntityNotFoundException;
import com.codeWise.codeWise.model.Course;
import com.codeWise.codeWise.model.Exercise;
import com.codeWise.codeWise.model.Teacher;
import com.codeWise.codeWise.repository.CourseRepository;
import com.codeWise.codeWise.repository.ExerciseRepository;
import com.codeWise.codeWise.repository.TeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;

    public Exercise create(NewExerciseDto dto) {
        Optional<Course> course = courseRepository.findById(dto.getIdCourse());
        Optional<Teacher> teacher = teacherRepository.findById(dto.getIdTeacher());

        if (course.isPresent() && teacher.isPresent()) {
            Exercise exercise = new Exercise(
                    dto.getDateStart(),
                    dto.getDateEnd(),
                    dto.getDescription(),
                    teacher.get(),
                    course.get()
            );
            return exerciseRepository.save(exercise);
        }

        if (!course.isPresent()) {
            throw new EntityNotFoundException("Not exist Course with id: " + dto.getIdCourse());
        }
        throw new EntityNotFoundException("Not exist Teacher with id: " + dto.getIdTeacher());
    }

    public List<Exercise> getAll() {
        return exerciseRepository.findAll();
    }

    public Exercise getById(Long id) {
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        if (exercise.isPresent()) {
            return exercise.get();
        }
        throw new EntityNotFoundException("Not exist Exercise with id: " + id);
    }

    public void deleteById(Long id) {
        Optional<Exercise> exercise = exerciseRepository.findById(id);
        if (exercise.isPresent()) {
            exerciseRepository.delete(exercise.get());
            return;
        }
        throw new EntityNotFoundException("Not exist Exercise with id: " + id);
    }

    
}