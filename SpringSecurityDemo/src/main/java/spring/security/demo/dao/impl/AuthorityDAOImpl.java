package spring.security.demo.dao.impl;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.security.demo.dao.AuthorityDAO;
import spring.security.demo.entity.Authority;

@Transactional
@Repository
public class AuthorityDAOImpl implements AuthorityDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void dbSaveAuthority(Authority authority) {
        this.sessionFactory.getCurrentSession().save(authority);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public long dbGetAuthorityCount() {
        Query query = this.sessionFactory.getCurrentSession().createQuery("SELECT COUNT(a) FROM Authority a");
        return (long) query.getSingleResult();
    }
}
