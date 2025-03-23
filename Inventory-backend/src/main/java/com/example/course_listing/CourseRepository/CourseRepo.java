package com.example.course_listing.CourseRepository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.course_listing.models.Course;

@Repository
public interface CourseRepo extends MongoRepository<Course, String> {
    
    Optional<Course> findByCourseId(String courseId); // ✅ Find by courseId
    
    void deleteByCourseId(String courseId); // ✅ Custom delete method
}
