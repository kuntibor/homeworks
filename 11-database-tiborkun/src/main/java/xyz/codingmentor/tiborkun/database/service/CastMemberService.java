package xyz.codingmentor.tiborkun.database.service;

import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.inject.Inject;
import xyz.codingmentor.tiborkun.database.api.CastMemberRepository;
import xyz.codingmentor.tiborkun.database.entity.CastMemberEntity;
import xyz.codingmentor.tiborkun.database.exception.RepositoryException;

/**
 *
 * @author Tibor Kun
 */
@Stateless
public class CastMemberService {

    @Inject
    private CastMemberRepository castMemberRepository;

    public CastMemberEntity createCastMember(String id) throws RepositoryException {
        return castMemberRepository.createCastMember(id);
    }

    public CastMemberEntity findCastMember(String id) throws RepositoryException {
        return castMemberRepository.findCastMember(id);
    }

    public void updateCastMember(CastMemberEntity castMember) throws RepositoryException {
        castMemberRepository.updateCastMember(castMember);
    }

    public void removeCastMember(String id) throws RepositoryException {
        castMemberRepository.removeCastMember(id);
    }

    @PreDestroy
    public void preDestroy() {
        castMemberRepository.close();

    }

}
