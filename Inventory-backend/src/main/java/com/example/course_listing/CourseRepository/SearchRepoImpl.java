package com.example.course_listing.CourseRepository;	
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import org.springframework.stereotype.Repository;

import com.example.course_listing.models.Course;
import com.mongodb.client.MongoClient;
import java.util.Arrays;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.AggregateIterable;

@Repository
public class SearchRepoImpl implements SearchRepo{
	@Autowired
	private MongoClient mongoClient;
	
	@Autowired
	MongoConverter convertor;
	
	@Override
	public List<Course> findByText (String text){	
		if (text == null || text.isEmpty()) {
            return new ArrayList<>(); // Or throw an exception, or return all courses - your choice
        }
		final  List<Course> courses= new ArrayList<>();
		
		MongoDatabase database = mongoClient.getDatabase("course-list");
		MongoCollection<Document> collection = database.getCollection("courses");
		
		AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search", 
			    new Document("text", 
			    new Document("query", text)
			                .append("path", Arrays.asList("course_id", "course_title", "course_author")))), 
			    new Document("$limit", 5L)));
		result.forEach(doc -> courses.add(convertor.read(Course.class,doc)));
		
		return courses;
	}
}