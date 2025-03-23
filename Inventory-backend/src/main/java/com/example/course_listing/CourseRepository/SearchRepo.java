package com.example.course_listing.CourseRepository;

import java.util.List;

import com.example.course_listing.models.Course;

public interface SearchRepo {
	List<Course> findByText(String text);
}
