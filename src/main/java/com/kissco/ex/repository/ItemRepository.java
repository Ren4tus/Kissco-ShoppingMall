package com.kissco.ex.repository;

import com.kissco.ex.domain.item.*;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public Long save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
        return item.getId();
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select m from Item m", Item.class)
                .getResultList();
    }
    public List<Item> findBooks() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QItem item = new QItem("i");

        return queryFactory
                .selectFrom(item)
                .where(item.instanceOf(Book.class))
                .fetch();
    }
    public List<Item> findMovies() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QItem item = new QItem("i");

        return queryFactory
                .selectFrom(item)
                .where(item.instanceOf(Movie.class))
                .fetch();

    }
    public List<Item> findAlbums() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QItem item = new QItem("i");

        return queryFactory
                .selectFrom(item)
                .where(item.instanceOf(Album.class))
                .fetch();

    }
}
