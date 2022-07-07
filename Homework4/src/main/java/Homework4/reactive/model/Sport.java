package Homework4.reactive.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

@Data
@NoArgsConstructor
public class Sport {

    @Id
    private Integer id;
    private Integer externalId;
    @NonNull
    private String name;
}