package lalalalz.springdatajpa.repository;

import lalalalz.springdatajpa.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}

