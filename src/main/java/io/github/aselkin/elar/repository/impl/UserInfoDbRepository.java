package io.github.aselkin.elar.repository.impl;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.github.aselkin.elar.model.entities.UserInfo;
import io.github.aselkin.elar.repository.UserInfoRepository;

@Repository
public class UserInfoDbRepository implements UserInfoRepository {

    private static final Logger LOG = Logger.getLogger(UserInfoDbRepository.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public UserInfo getUserInfoByLogin(String login) throws RepositoryException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            final DetachedCriteria criteria = DetachedCriteria.forClass(UserInfo.class);
            criteria.add(Restrictions.eq("login", login));
            final UserInfo result =
                (UserInfo) criteria.getExecutableCriteria(session).uniqueResult();

            transaction.commit();
            return result;
        }
        catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            final String exMessage = String.format("Unable to load info for user with login %s"
                + "from the repository", login);
            LOG.error(exMessage, ex);
            throw new RepositoryException(exMessage, ex);
        }
    }

}
