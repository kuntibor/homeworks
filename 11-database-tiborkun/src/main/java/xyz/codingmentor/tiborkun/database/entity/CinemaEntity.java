package xyz.codingmentor.tiborkun.database.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Tibor Kun
 */
@Entity
@Table(name = "cinema")
public class CinemaEntity implements Serializable {

    @Id
    private String id;

    private String name;

    @Embedded
    private Address address;

    @ManyToMany
    @JoinTable(name = "jnd_movie_cinema")
    private List<MovieEntity> movies;

    public CinemaEntity() {
        // nothing to initialize
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<MovieEntity> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieEntity> movies) {
        this.movies = movies;
    }

}
