/**
 * If you really care for the license, look for the LICENSE.txt at the project root. Frankly, I 
 * really don't care.
 **/
package com.example.starter.dao.impl;

import com.example.starter.dao.UserDao;
import com.example.starter.entity.App;
import com.example.starter.entity.Role;
import com.example.starter.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class implements the UserDao interface.
 * 
 * @author Roshan Amadoru
 **/
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    
    /**
     * The logger instance
     **/
    private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    
    /**
     * The instance of SessionFactory injected with Spring
     **/
    @Autowired
    private SessionFactory sessionFactory;
    
    /**
     * Returns the user object matched by the passed username. Returns null if the user is not 
     * found in the database.
     * @param username username of the user which needs to query for
     * @return an instance of User if the user is found, null otherwise
     **/
    @Transactional(readOnly=true)
    public User findUserByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User)session.createQuery("from User user where user.username=?")
                .setParameter(0, username)
                .uniqueResult();
        
        if (logger.isDebugEnabled()) {
            if (user==null) {
                logger.trace("User not found: " + username);
            } else {
                logger.trace("User found: " + username);
                Iterator<Role> roleIterator = user.getRoles().iterator();
                while(roleIterator.hasNext()) {
                    Role role = roleIterator.next();
                    logger.trace("\tUser role: " + role.getRoleName());
                }
            }
        }
        return user;
    }

    @Transactional
    public List<App> findUserById(long userId) {
        App app = null;
        Session session = sessionFactory.getCurrentSession();

        //test
        App app1 = new App("The Cloud", "cloud.jpg", "test  afa test");
        session.save(app1);


        app = (App) session.get(App.class, userId);
//        Hibernate.initialize(app);
        List<App> result = new ArrayList<App>();
        result.add(app);
        return result;
    }


    public List<App> searchByLucene(String queryKey) throws InterruptedException {
        Session session = sessionFactory.openSession();
        App app = null;

        // Create a Hibernate Search wrapper around the vanilla Hibernate session
        FullTextSession fullTextSession = Search.getFullTextSession(session);

        // very important
        fullTextSession.createIndexer().startAndWait();
        // Begin a transaction.  This may not be strictly necessary, but is a good practice in general.
        fullTextSession.beginTransaction();

        // Create a Hibernate Search QueryBuilder for the appropriate Lucene index (i.e. the index for "App" in this case)
        QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder()
                .forEntity( App.class ).get();

        // Use the QueryBuilder to construct a Lucene keyword query, matching the user's search keywords against the name
        // and description fields of App.

        // 1.search like %%
//        org.apache.lucene.search.Query luceneQuery = queryBuilder
//                .keyword()
//                .onFields("name", "description")
//                .matching("The Cloud")
//                .createQuery();

        // 2.search exactly
//        org.apache.lucene.search.Query luceneQuery = queryBuilder
//                .phrase()
//                .onField("name")
//                .sentence("^The Cloud$")
//                .createQuery();

        // 3.search case insensitive
        org.apache.lucene.search.Query luceneQuery = queryBuilder
                .phrase()
                .onField("name")
                .sentence("^The Cloud$")
                .createQuery();


        org.hibernate.Query hibernateQuery = fullTextSession.createFullTextQuery(luceneQuery, App.class);

        List apps = hibernateQuery.list();

        // Close and clean up the Hibernate session
        fullTextSession.getTransaction().commit();
        session.close();


        return apps;
    }
}
