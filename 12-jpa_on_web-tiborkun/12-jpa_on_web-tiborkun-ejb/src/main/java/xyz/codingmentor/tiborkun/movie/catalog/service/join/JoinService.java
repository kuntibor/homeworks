package xyz.codingmentor.tiborkun.movie.catalog.service.join;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import xyz.codingmentor.tiborkun.movie.catalog.api.generic.CRUDService;
import xyz.codingmentor.tiborkun.movie.catalog.entity.ActorEntity;
import xyz.codingmentor.tiborkun.movie.catalog.entity.CategoryEntity;
import xyz.codingmentor.tiborkun.movie.catalog.entity.MovieEntity;
import xyz.codingmentor.tiborkun.movie.catalog.entity.TrailerEntity;
import xyz.codingmentor.tiborkun.movie.catalog.exception.RepositoryException;
import xyz.codingmentor.tiborkun.movie.catalog.qualifier.CRUDServiceQualifier;
import xyz.codingmentor.tiborkun.movie.catalog.qualifier.EntityModel;

/**
 *
 * @author Tibor Kun
 */
@Stateless
public class JoinService {

    private CRUDService<ActorEntity> actorCRUDService;
    private CRUDService<MovieEntity> movieCRUDService;
    private CRUDService<CategoryEntity> categoryCRUDService;
    private CRUDService<TrailerEntity> traileCRUDService;

    public JoinService() {
        // nothing to initialize
    }

    @Inject
    public JoinService(
            @CRUDServiceQualifier(EntityModel.ACTOR) CRUDService<ActorEntity> actorCRUDService,
            @CRUDServiceQualifier(EntityModel.MOVIE) CRUDService<MovieEntity> movieCRUDService,
            @CRUDServiceQualifier(EntityModel.CATEGORY) CRUDService<CategoryEntity> categoryCRUDService,
            @CRUDServiceQualifier(EntityModel.TRAILER) CRUDService<TrailerEntity> traileCRUDService) {
        this.actorCRUDService = actorCRUDService;
        this.movieCRUDService = movieCRUDService;
        this.categoryCRUDService = categoryCRUDService;
        this.traileCRUDService = traileCRUDService;
    }

    public void joinMovieToActor(Long movieId, Long actorId) {
        try {
            ActorEntity actor = actorCRUDService.getEntityById(actorId);
            MovieEntity movie = movieCRUDService.getEntityById(movieId);
            if (!actor.getMovies().contains(movie)) {
                actor.getMovies().add(movie);
                actorCRUDService.updateEntity(actor);
            }
        } catch (RepositoryException ex) {
            Logger.getLogger(JoinService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void joinMovieToCategory(Long movieId, Long categoryId) {
        try {
            CategoryEntity category = categoryCRUDService.getEntityById(categoryId);
            MovieEntity movie = movieCRUDService.getEntityById(movieId);
            movie.setCategory(category);
            movieCRUDService.updateEntity(movie);
        } catch (RepositoryException ex) {
            Logger.getLogger(JoinService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void joinMovieToTrailer(Long movieId, Long trailerId) {
        try {
            TrailerEntity trailer = traileCRUDService.getEntityById(trailerId);
            MovieEntity movie = movieCRUDService.getEntityById(movieId);
            trailer.setMovie(movie);
            traileCRUDService.updateEntity(trailer);
        } catch (RepositoryException ex) {
            Logger.getLogger(JoinService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
