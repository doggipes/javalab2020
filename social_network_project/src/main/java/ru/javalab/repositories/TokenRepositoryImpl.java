package ru.javalab.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import ru.javalab.models.entity.User;
import ru.javalab.models.entity.VerificationToken;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class TokenRepositoryImpl implements TokenRepository {

    @PersistenceContext
    private EntityManager entityManager;



    @Override
    @Transactional
    public Optional<VerificationToken> findEntityByToken(String token) {
        System.out.println(token);
        List<VerificationToken> list = entityManager.createQuery("SELECT v FROM VerificationToken v WHERE v.token = ?1", VerificationToken.class)
                                .setParameter(1, token)
                                .getResultList();
        VerificationToken verificationToken = list.get(0);

        return Optional.of(verificationToken);
    }

    @Override
    public Optional<VerificationToken> findTokenByUser(User user) {
        return null;
    }

    @Override
    public Optional<VerificationToken> find(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<VerificationToken> findAll() {
        return null;
    }

    @Override
    @Transactional
    public VerificationToken save(VerificationToken verificationToken) {
        entityManager.persist(verificationToken);

        return verificationToken;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        VerificationToken verificationToken = entityManager.find(VerificationToken.class, id);
        if(verificationToken != null){
            entityManager.remove(verificationToken);
        }
    }
}
