package model.entity;

import com.ngocnb20.travel.model.entity.Place;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class ImagePlace {
    @Id
    private Long id;
    private String url;
    @ManyToOne
    @JoinColumn(name ="id_place",referencedColumnName = "id")
    private Place place;
}
