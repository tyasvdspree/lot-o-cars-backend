package nl.lotocars.rental.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "ROLE")
@Entity(name = "role")
public class Role{

    @Id
    private Long Id;

    private String name;
}
