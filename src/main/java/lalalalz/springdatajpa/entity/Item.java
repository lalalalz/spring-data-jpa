package lalalalz.springdatajpa.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Item implements Persistable<Long> {

    @Id @GeneratedValue
    private Long id;

    @CreatedDate
    private LocalDateTime createdDate;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return createdDate == null;
    }
}
