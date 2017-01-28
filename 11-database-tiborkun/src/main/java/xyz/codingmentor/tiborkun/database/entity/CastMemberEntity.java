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
@Table(name = "cast_member")
public class CastMemberEntity implements Serializable {

    @Id
    private String id;

    private String firstName;
    private String lastName;

    @Embedded
    private Address address;

    @ManyToMany
    @JoinTable(name = "jnd_cast_movie")
    private List<MovieEntity> appearsOnMovies;

    public CastMemberEntity() {
        // nothing to initialize
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<MovieEntity> getAppearsOnMovies() {
        return appearsOnMovies;
    }

    public void setAppearsOnMovies(List<MovieEntity> appearsOnMovies) {
        this.appearsOnMovies = appearsOnMovies;
    }

}
