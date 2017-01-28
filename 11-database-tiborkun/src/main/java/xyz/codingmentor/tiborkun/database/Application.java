package xyz.codingmentor.tiborkun.database;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.inject.Inject;
import org.jboss.logging.Logger;
import xyz.codingmentor.tiborkun.database.entity.Address;
import xyz.codingmentor.tiborkun.database.entity.CastMemberEntity;
import xyz.codingmentor.tiborkun.database.entity.MovieEntity;
import xyz.codingmentor.tiborkun.database.entity.MovieId;
import xyz.codingmentor.tiborkun.database.entity.MovieStyleEnum;
import xyz.codingmentor.tiborkun.database.exception.RepositoryException;
import xyz.codingmentor.tiborkun.database.jpa.JPAMovieRepository;
import xyz.codingmentor.tiborkun.database.service.CastMemberService;
import xyz.codingmentor.tiborkun.database.service.MovieQueryService;
import xyz.codingmentor.tiborkun.database.service.MovieService;

/**
 *
 * @author Tibor Kun
 */
public class Application {

    @Inject
    private MovieService movieService;

    @Inject
    private MovieQueryService movieQueryService;

    @Inject
    private CastMemberService castMemberService;

    private static final Logger LOGGER = Logger.getLogger(JPAMovieRepository.class.getName());

    private MovieId firstId;
    private MovieId secondId;
    private MovieId thirdId;
    private MovieEntity firstMovie;
    private MovieEntity secondMovie;
    private MovieEntity thirdMovie;
    private Address address;
    private CastMemberEntity firstActor;
    private CastMemberEntity secondActor;
    private CastMemberEntity thirdActor;
    private List<CastMemberEntity> castMembers;
    private List<MovieEntity> movies;

    public Application() {
        // nothing to initialize
    }

    public void execute() throws RepositoryException {
        createAddress();
        createMovies();
        createMovieList();
        createCastMembers();
        createCastMemberList();
        updateCastMembers();
        updateMovies();

        castMemberService.updateCastMember(firstActor);
        castMemberService.updateCastMember(secondActor);
        castMemberService.updateCastMember(thirdActor);

        movieService.updateMovie(firstMovie);
        movieService.updateMovie(secondMovie);
        movieService.updateMovie(thirdMovie);

        logMovies(movieQueryService.getMoviesByTitle("first"));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -30);
        logMovies(movieQueryService.getMoviesAfterDate(calendar.getTime()));
        logMovies(movieQueryService.orderMoviesByDate(calendar.getTime()));
        movieQueryService.updateMoviesStyle(MovieStyleEnum.SCIFI, MovieStyleEnum.ROMANTIC);
        movieQueryService.removeMoviesByDirector("Béla");
    }

    private void createMovies() throws RepositoryException {
        Calendar calendar = Calendar.getInstance();
        firstId = new MovieId("first", "comp1", calendar.getTime());
        calendar.add(Calendar.YEAR, -15);
        secondId = new MovieId("second", "comp2", calendar.getTime());
        calendar.add(Calendar.YEAR, -6);
        thirdId = new MovieId("third", "comp3", calendar.getTime());

        firstMovie = movieService.createMovie(firstId);
        secondMovie = movieService.createMovie(secondId);
        thirdMovie = movieService.createMovie(thirdId);
    }

    private void updateMovies() throws RepositoryException {
        firstMovie.setDirector("Béla");
        firstMovie.setStyle(MovieStyleEnum.SCIFI);
        firstMovie.setCastMembers(castMembers);

        secondMovie.setDirector("Lajos");
        secondMovie.setStyle(MovieStyleEnum.COMEDY);
        secondMovie.setCastMembers(castMembers);

        thirdMovie.setDirector("Elemér");
        thirdMovie.setStyle(MovieStyleEnum.ROMANTIC);
        thirdMovie.setCastMembers(castMembers);
    }

    private void updateCastMembers() {
        firstActor.setAddress(address);
        firstActor.setAppearsOnMovies(movies);
        firstActor.setFirstName("Kata");
        firstActor.setLastName("Actress");

        secondActor.setAddress(address);
        secondActor.setAppearsOnMovies(movies);
        secondActor.setFirstName("Marcsi");
        secondActor.setLastName("Actress");

        thirdActor.setAddress(address);
        thirdActor.setAppearsOnMovies(movies);
        thirdActor.setFirstName("Jakab");
        thirdActor.setLastName("Actor");
    }

    private void createAddress() {
        address = new Address();
        address.setCity("Budapest");
        address.setCountry("Hungary");
        address.setState("Hungary");
        address.setStreet("Main Street");
        address.setZipcode("1234");
        address.setHouseNumber("1");
    }

    private void createMovieList() {
        movies = new ArrayList<>();
        movies.add(firstMovie);
        movies.add(secondMovie);
        movies.add(thirdMovie);
    }

    private void createCastMembers() throws RepositoryException {
        firstActor = castMemberService.createCastMember("1");
        secondActor = castMemberService.createCastMember("2");
        thirdActor = castMemberService.createCastMember("3");
    }

    private void createCastMemberList() {
        castMembers = new ArrayList<>();
        castMembers.add(firstActor);
        castMembers.add(secondActor);
        castMembers.add(thirdActor);
    }

    private void logMovies(List<MovieEntity> movies) {
        String loginfo = "";
        loginfo = movies.stream().map(movie -> "\n\tTitle: " + movie.getId().getTitle()
                + ". Company: " + movie.getId().getCompany()
                + ". Directed by:  " + movie.getDirector()
                + ". Release date: " + movie.getId().getReleaseDate()
                + ". Style: " + movie.getStyle().toString()).reduce(loginfo, String::concat);
        LOGGER.info(loginfo);
    }

}
