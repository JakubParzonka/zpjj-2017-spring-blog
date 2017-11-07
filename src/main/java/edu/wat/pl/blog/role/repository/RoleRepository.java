package edu.wat.pl.blog.role.repository;

import edu.wat.pl.blog.role.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
}
