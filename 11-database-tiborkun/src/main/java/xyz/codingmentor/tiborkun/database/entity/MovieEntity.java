package xyz.codingmentor.tiborkun.database.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Tibor Kun
 */
@Entity
@Table(name = "movie")
@IdClass(MovieId.class)
public class MovieEntity implements Serializable {

    @Id
    private String title;
    @Id
    private String company;
    @Id
    @Temporal(TemporalType.DATE)
    @Column(name = "release")
    private Date releaseDate;

    private String director;

    @Enumerated(EnumType.STRING)
    private MovieStyleEnum style;

    @ManyToMany(mappedBy = "appearsOnMovies")
    private List<CastMemberEntity> castMembers;

    @ManyToMany(mappedBy = "movies")
    private List<CinemaEntity> playsInCinemas;

    public MovieEntity() {
        // nothing to initialize
    }

    public void setId(MovieId id) {
        this.title = id.getTitle();
        this.company = id.getCompany();
        this.releaseDate = id.getReleaseDate();
    }

    public MovieId getId() {
        return new MovieId(title, company, releaseDate);
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public MovieStyleEnum getStyle() {
        return style;
    }

    public void setStyle(MovieStyleEnum style) {
        this.style = style;
    }

    public List<CastMemberEntity> getCastMembers() {
        return castMembers;
    }

    public void setCastMembers(List<CastMemberEntity> castMembers) {
        this.castMembers = castMembers;
    }

    public List<CinemaEntity> getPlaysInCinemas() {
        return playsInCinemas;
    }

    public void setPlaysInCinemas(List<CinemaEntity> playsInCinemas) {
        this.playsInCinemas = playsInCinemas;
    }

}
