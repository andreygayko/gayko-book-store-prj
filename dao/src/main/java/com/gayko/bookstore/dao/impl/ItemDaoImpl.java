package com.gayko.bookstore.dao.impl;

import com.gayko.bookstore.dao.ItemDao;
import com.gayko.bookstore.dao.model.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDaoImpl extends GenericDaoImpl<Item> implements ItemDao {
    public ItemDaoImpl() {
        super(Item.class);
    }

    @Override
    public List<Item> findAll() {
        return (List<Item>) getCurrentSession().createQuery("from Item as i where i.isAlive=true order by i.name desc")
                .list();
    }

    @Override
    public Item findById(long id) {
        return (Item) getCurrentSession().createQuery("from Item where id=:id")
                .setParameter("id", id)
                .uniqueResult();
    }

    @Override
    public List<Item> findItems(int from, int to) { //find items in price range from - to
        return getCurrentSession().createQuery("select i from Item as i where i.price between " + from + " and " + to)
                .list();
    }

    @Override
    public Long countItems(int from, int to) { //count items in price range from - to
        return (Long) getCurrentSession().createQuery("select count (f.id) from Item f where f.price between " + from + " and " + to)
                .uniqueResult();
    }


}
