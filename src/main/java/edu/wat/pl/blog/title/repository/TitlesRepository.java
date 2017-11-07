package edu.wat.pl.blog.title.repository;

import edu.wat.pl.blog.title.model.Titles;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TitlesRepository extends MongoRepository<Titles,String> {
}
