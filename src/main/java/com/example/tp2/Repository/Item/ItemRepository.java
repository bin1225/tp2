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
    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> getItemList(){
        return em.createQuery("select i from Item i",Item.class)
                .getResultList();
    }


}
