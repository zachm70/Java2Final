package dmacc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dmacc.beans.Member;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long>{

}
