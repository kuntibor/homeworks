package xyz.codingmentor.tiborkun.database.jpa;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import xyz.codingmentor.tiborkun.database.api.CastMemberRepository;
import xyz.codingmentor.tiborkun.database.entity.CastMemberEntity;
import xyz.codingmentor.tiborkun.database.exception.RepositoryException;

/**
 *
 * @author Tibor Kun
 */
@Stateless
public class JPACastMemberRepository implements CastMemberRepository {

    private final EntityManagerFactory factory;
    private final EntityManager entityManager;

    public JPACastMemberRepository() {
        factory = Persistence.createEntityManagerFactory("homeworkDatabasePU");
        entityManager = factory.createEntityManager();
    }

    @Override
    public CastMemberEntity createCastMember(String id) throws RepositoryException {
        CastMemberEntity castMember = new CastMemberEntity();
        castMember.setId(id);
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(castMember);
        tx.commit();
        return castMember;
    }

    @Override
    public CastMemberEntity findCastMember(String id) throws RepositoryException {
        CastMemberEntity castMember = entityManager.find(CastMemberEntity.class, id);
        if (castMember != null) {
            return castMember;
        }
        return null;
    }

    @Override
    public void updateCastMember(CastMemberEntity castMember) throws RepositoryException {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.merge(castMember);
        tx.commit();
    }

    @Override
    public void removeCastMember(String id) throws RepositoryException {
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        CastMemberEntity castMember = entityManager.find(CastMemberEntity.class, id);
        if (castMember != null) {
            entityManager.remove(castMember);
        }
        tx.commit();
    }

    @Override
    public void close() {
        entityManager.close();
        factory.close();
    }

}
