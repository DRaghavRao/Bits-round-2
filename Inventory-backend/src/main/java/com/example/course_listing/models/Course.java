package com.example.course_listing.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection="courses")
public class Course {
	
	public Course() {
	}

	@Id
    private String id;  // MongoDB default _id field

    @Field("course_id")
    private String courseId;

    @Field("course_title")  // ✅ Ensure this matches the DB field name
    private String courseTitle;

    @Field("course_author")
    private String courseAuthor;

    @Field("course_duration")
    private String courseDuration;

    @Field("course_level")
    private String courseLevel;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getCourseAuthor() {
		return courseAuthor;
	}

	public void setCourseAuthor(String courseAuthor) {
		this.courseAuthor = courseAuthor;
	}

	public String getCourseDuration() {
		return courseDuration;
	}

	public void setCourseDuration(String courseDuration) {
		this.courseDuration = courseDuration;
	}

	public String getCourseLevel() {
		return courseLevel;
	}

	public void setCourseLevel(String courseLevel) {
		this.courseLevel = courseLevel;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", courseId=" + courseId + ", courseTitle=" + courseTitle + ", courseAuthor="
				+ courseAuthor + ", courseDuration=" + courseDuration + ", courseLevel=" + courseLevel + "]";
	}

	
}
