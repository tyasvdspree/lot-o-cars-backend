package nl.lotocars.rental.entities;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.LocalDate;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue
    private long id;

    @Version
    private Integer version;

    @CreatedDate
    private LocalDate createdDate;
}
