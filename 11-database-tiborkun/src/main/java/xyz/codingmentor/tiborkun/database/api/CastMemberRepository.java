package xyz.codingmentor.tiborkun.database.api;

import xyz.codingmentor.tiborkun.database.entity.CastMemberEntity;
import xyz.codingmentor.tiborkun.database.exception.RepositoryException;

/**
 *
 * @author Tibor Kun
 */
public interface CastMemberRepository {

    CastMemberEntity createCastMember(String id) throws RepositoryException;

    CastMemberEntity findCastMember(String id) throws RepositoryException;

    void updateCastMember(CastMemberEntity castMember) throws RepositoryException;

    void removeCastMember(String id) throws RepositoryException;

    void close();

}
