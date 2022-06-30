package com.example.navigation.service;

import com.example.navigation.entity.Navigate;
import com.example.navigation.repository.NavigateRepository;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class NavigateService implements IService<Navigate> {
    @Autowired
    private NavigateRepository repository;


    @Autowired
    private EntityManager entityManager;

    private static final int NUM = 5;
    private static final int SIZE = 10;

    @Override
    public ResponseEntity<Navigate> create(Navigate navigate, boolean isDeleted) {
        try {
            List<Navigate> navigateList = read(isDeleted);
            if (navigateList.size() < SIZE) {
                return new ResponseEntity<>(repository.save(navigate), HttpStatus.OK);
            } else {
                boolean check = false;
                for (int i = 0; i < navigateList.size(); i++) {
                    if (i == NUM) {
                        navigateList.get(NUM).setDeleted(true);
                        check = true;
                        break;
                    }
                }
                if (check == true) {
                    repository.save(navigate);
                }
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Navigate> read(boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedNavigateFilter");
        filter.setParameter("isDeleted", isDeleted);

        List<Navigate> navigateList = repository.findAll();

        session.disableFilter("deletedNavigateFilter");
        return navigateList;
    }

    @Override
    public ResponseEntity<List<Navigate>> getList(boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedNavigateFilter");
        filter.setParameter("isDeleted", isDeleted);

        List<Navigate> navigateList = repository.findAll();

        session.disableFilter("deletedNavigateFilter");
        return new ResponseEntity<>(navigateList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Navigate> update(int id) {
        return null;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    public Session Common(boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedNavigateFilter");
        filter.setParameter("isDeleted", isDeleted);
        return session;
    }

}
