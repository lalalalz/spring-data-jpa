package lalalalz.springdatajpa.repository;

import lalalalz.springdatajpa.entity.Member;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


public class CustomRepositoryHello implements CustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Member> customFindMembers() {
        return entityManager.createQuery("select m from Member m")
                .getResultList();
    }
}
