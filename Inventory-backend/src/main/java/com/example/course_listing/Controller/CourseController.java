package com.example.course_listing.Controller; // ✅ Ensure this is correct

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.course_listing.CourseRepository.CourseRepo;
import com.example.course_listing.CourseRepository.SearchRepo;
import com.example.course_listing.models.Course;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api")
public class CourseController { // ✅ Rename to avoid conflict with Java keyword "Controller"

    @Autowired
    private CourseRepo repo;

    @Autowired
    private SearchRepo srepo;
    
    @CrossOrigin
    @GetMapping("/GetCourses")
    public List<Course> getAllCourses() {
        return repo.findAll();
    }
    
    @GetMapping("/SearchCourses/{text}")
	@CrossOrigin
    public List<Course> search(@PathVariable String text)
    {
        return srepo.findByText(text);
    }
    
    @CrossOrigin
    @GetMapping("/GetCourseByCourseId/{courseId}")
    public ResponseEntity<Course> getCourseByCourseId(@PathVariable String courseId){
        Optional<Course> course = repo.findByCourseId(courseId);
        return course.map(ResponseEntity::ok).orElseGet(
                () -> ResponseEntity.notFound().build());
    }
 

    @CrossOrigin
    @PostMapping("/PostCourse")
	public ResponseEntity<Course> addCourse(@RequestBody Course course) {
		System.out.println("Received Course: " + course);
		return ResponseEntity.ok(repo.save(course));
	}
    
    @CrossOrigin
    @PutMapping("/UpdateCourse/{courseId}") 
    public ResponseEntity<Course> updateCourse(@PathVariable String courseId, @RequestBody Course updatedCourse) {
        Optional<Course> existingCourseOpt = repo.findByCourseId(courseId); // Ensure this method exists in repo

        if (existingCourseOpt.isPresent()) {
            Course existingCourse = existingCourseOpt.get();

            // ✅ Only update non-null fields
            if (updatedCourse.getCourseTitle() != null && !updatedCourse.getCourseTitle().isEmpty()) {
                existingCourse.setCourseTitle(updatedCourse.getCourseTitle());
            }
            if (updatedCourse.getCourseAuthor() != null && !updatedCourse.getCourseAuthor().isEmpty()) {
                existingCourse.setCourseAuthor(updatedCourse.getCourseAuthor());
            }
            if (updatedCourse.getCourseDuration() != null && !updatedCourse.getCourseDuration().isEmpty()) {
                existingCourse.setCourseDuration(updatedCourse.getCourseDuration());
            }
            if (updatedCourse.getCourseLevel() != null && !updatedCourse.getCourseLevel().isEmpty()) {
                existingCourse.setCourseLevel(updatedCourse.getCourseLevel());
            }

            Course savedCourse = repo.save(existingCourse); // ✅ Save the updated course
            return ResponseEntity.ok(savedCourse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }



    @CrossOrigin
    @DeleteMapping("/DeleteCourse/{courseId}")
    public ResponseEntity<Void> deleteCourseByCourseId(@PathVariable String courseId) {
        Optional<Course> existingCourse = repo.findByCourseId(courseId);
        if (existingCourse.isPresent()) {
            repo.deleteByCourseId(courseId);
            return ResponseEntity.noContent().build(); // ✅ 204 No Content
        }
        return ResponseEntity.notFound().build(); // ✅ 404 Not Found
    }

}

