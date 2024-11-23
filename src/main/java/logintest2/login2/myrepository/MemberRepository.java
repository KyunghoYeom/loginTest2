package logintest2.login2.myrepository;

import jakarta.persistence.EntityManager;
import logintest2.login2.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    EntityManager em;

    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }





}
