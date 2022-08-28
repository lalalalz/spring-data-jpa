package lalalalz.springdatajpa.repository;

import lalalalz.springdatajpa.entity.Member;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

public interface CustomRepository {

    List<Member> customFindMembers();

}
