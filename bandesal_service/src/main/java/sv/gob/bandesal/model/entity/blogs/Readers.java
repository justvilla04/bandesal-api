package sv.gob.bandesal.bandesal.model.entity.blogs;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "readers", schema = "salvador")
@SequenceGenerator(name = "blogs_readers_id_seq", sequenceName = "salvador.blogs_readers_id_seq", allocationSize = 1)
public class Readers implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blogs_readers_id_seq")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "name")
    private String name;

}
