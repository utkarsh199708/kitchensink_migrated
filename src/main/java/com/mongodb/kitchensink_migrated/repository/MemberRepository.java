package com.mongodb.kitchensink_migrated.repository;
import com.mongodb.kitchensink_migrated.entity.Member;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MemberRepository extends MongoRepository<Member,String> {
    Member findByEmail(String email);
   List<Member> findAllByOrderByUsernameAsc();

}
