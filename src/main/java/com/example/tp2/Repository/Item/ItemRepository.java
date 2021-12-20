package com.example.tp2.Repository.Item;

import com.example.tp2.domain.item.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ItemRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Item item)
    {
        if(item.getId() == null){
            em.persist(item);
        }
        else{
            em.merge(item);
        }
    }
    public void save(Item... items)
    {
        for(Item item : items){
            em.persist(item);
        }
    }
    public void delete(Item item) {
        em.remove(item);
    }
    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> getItemList(){
        return em.createQuery("select i from Item i",Item.class)
                .getResultList();
    }

    public List<Item> findByCategory(String categoryName){
        return em.createQuery("select i from Item i where i.category =: categoryName")
                .setParameter("categoryName",categoryName)
                .getResultList();
    }

}

