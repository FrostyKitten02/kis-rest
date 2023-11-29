package si.um.feri.kis.kisrest.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;


@Getter
@Setter
@Immutable
@MappedSuperclass
public class Animal extends BaseModel {
    private String name;
    private String age;
    private String color;
    private String breed;
}
