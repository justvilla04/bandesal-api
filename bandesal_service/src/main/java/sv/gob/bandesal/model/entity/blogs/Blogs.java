package sv.gob.bandesal.bandesal.model.entity.blogs;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "blogs", schema = "salvador")
@SequenceGenerator(name = "blogs_id_seq", sequenceName = "salvador.blogs_id_seq", allocationSize = 1)
public class Blogs implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blogs_id_seq")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "title")
    private String title;

    @Basic
    @Column(name = "description")
    private String description;

}
