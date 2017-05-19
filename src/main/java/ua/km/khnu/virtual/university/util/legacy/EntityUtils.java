package ua.km.khnu.virtual.university.util.legacy;

import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.core.io.PathResource;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ua.km.khnu.virtual.university.error.legacy.CustomException;
import ua.km.khnu.virtual.university.error.legacy.NoEntityWithSuchIdCustomException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.function.Function;

/**
 * A util class for generic operations with repositories.
 *
 * @author Igor Rybak
 */
public class EntityUtils {
    private EntityUtils() {

    }

    /**
     * Retrieve entity using callback function, else if entity is null the id
     * and entity type will be passed to exception for error message interpolation.
     *
     * @param retrieveOne callback function to retrieve entity by id
     * @param id          the id of entity
     * @param entityType  the Class object corresponding to the entity type
     * @param <T>         the type of entity
     * @param <ID>        the type of entity
     * @return the found entity if present
     * @throws ResourceNotFoundException if entity was not found
     */
    public static <T, ID> T retrieveOneOrThrowNotFound(
            Function<ID, T> retrieveOne,
            ID id,
            Class<T> entityType
    ) {
        T entity = retrieveOne.apply(id);
        if (entity != null) return entity;

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getResponse();
        response.setStatus(404);

        throw new ResourceNotFoundException(entityType.getName(),new PathResource(request.getRequestURI()));
    }

    /**
     * Checks by callback if entity with given id exists. if not the id
     * and entity type will be passed to exception for error message interpolation.
     *
     * @param exists     callback function to check if entity with given id exists
     * @param id         the id of entity
     * @param entityType the Class object corresponding to the entity type
     * @param <T>        the type of entity
     * @param <ID>       the type of entity
     * @throws NoEntityWithSuchIdCustomException if entity was not found
     */
    public static <T, ID> void throwNotFoundIfNotExists(
            Function<ID, Boolean> exists,
            ID id,
            Class<T> entityType
    ) {
        if (exists.apply(id)) return;

        CustomException exception = new NoEntityWithSuchIdCustomException();
        exception.setDescriptionArgs(entityType.getName(), id);
        throw exception;
    }
}