package xyz.codingmentor.tiborkun.movie.catalog.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Tibor Kun
 */
@Entity
@Table(name = "movie")
public class MovieEntity implements Serializable {

    @Id
    private Long id;

    private String title;

    @ManyToMany(mappedBy = "movies", cascade = {CascadeType.PERSIST})
    private List<ActorEntity> actors;

    @OneToMany(mappedBy = "movie", cascade = {CascadeType.PERSIST})
    private List<TrailerEntity> trailers;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private CategoryEntity category;

    public MovieEntity() {
        // nothing to initialize
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlTransient
    public List<ActorEntity> getActors() {
        return actors;
    }

    public void setActors(List<ActorEntity> actors) {
        this.actors = actors;
    }

    @XmlTransient
    public List<TrailerEntity> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<TrailerEntity> trailers) {
        this.trailers = trailers;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

}
