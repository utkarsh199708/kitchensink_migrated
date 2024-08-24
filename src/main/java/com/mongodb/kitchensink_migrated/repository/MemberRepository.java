package com.mongodb.kitchensink_migrated.repository;
import com.mongodb.kitchensink_migrated.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
    List<Member> findAllByOrderByNameAsc();
    Member findByName(String username);
}
