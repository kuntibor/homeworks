package xyz.codingmentor.tiborkun.movie.catalog.loader;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;
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
@Singleton
public class Loader {

    @Inject
    @CRUDServiceQualifier(EntityModel.MOVIE)
    private CRUDService<MovieEntity> movieService;

    @Inject
    @CRUDServiceQualifier(EntityModel.ACTOR)
    private CRUDService<ActorEntity> actorService;

    @Inject
    @CRUDServiceQualifier(EntityModel.CATEGORY)
    private CRUDService<CategoryEntity> categoryService;

    @Inject
    @CRUDServiceQualifier(EntityModel.TRAILER)
    private CRUDService<TrailerEntity> trailerService;

    private List<MovieEntity> movieListFromJson = null;
    private List<ActorEntity> actorListFromJson = null;
    private List<CategoryEntity> categoryListFromJson = null;
    private List<TrailerEntity> trailerListFromJson = null;
    private ObjectMapper mapper;
    private JavaType movieType;
    private JavaType actorType;
    private JavaType categoryType;
    private JavaType trailerType;

    private static final Logger LOGGER = Logger.getLogger(Loader.class.getName());

    public void init() {
        LOGGER.info("Loading entities from json to database...");
        mapper = new ObjectMapper();
        movieType = mapper.getTypeFactory().constructCollectionType(List.class, MovieEntity.class);
        actorType = mapper.getTypeFactory().constructCollectionType(List.class, ActorEntity.class);
        categoryType = mapper.getTypeFactory().constructCollectionType(List.class, CategoryEntity.class);
        trailerType = mapper.getTypeFactory().constructCollectionType(List.class, TrailerEntity.class);
        loadEntitiesFromJson();
        fillEntities();
        LOGGER.info("Loading completed");
    }

    private void loadEntitiesFromJson() {
        try {
            movieListFromJson = mapper.readValue(new File(getClass().getClassLoader().getResource("movies.json").getFile()), movieType);
            actorListFromJson = mapper.readValue(new File(getClass().getClassLoader().getResource("actors.json").getFile()), actorType);
            categoryListFromJson = mapper.readValue(new File(getClass().getClassLoader().getResource("categories.json").getFile()), categoryType);
            trailerListFromJson = mapper.readValue(new File(getClass().getClassLoader().getResource("trailers.json").getFile()), trailerType);
        } catch (IOException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillEntities() {
        try {
            for (CategoryEntity category : categoryListFromJson) {
                categoryService.createEntity(category);
            }
            for (MovieEntity movie : movieListFromJson) {
                movieService.createEntity(movie);
            }
            for (ActorEntity actor : actorListFromJson) {
                actorService.createEntity(actor);
            }
            for (TrailerEntity trailer : trailerListFromJson) {
                trailerService.createEntity(trailer);
            }
        } catch (RepositoryException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
