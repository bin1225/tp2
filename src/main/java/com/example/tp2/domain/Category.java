package com.example.tp2.domain;

import com.example.tp2.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {


    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent",cascade = CascadeType.ALL)
    private List<Category> child = new ArrayList<>();

    public void addChild(Category category){
        this.child.add(category);
        category.setParent(this);
    }

    protected Category(){
    }
    public Category(String name) {
        this.name = name;
    }

    public Category(String name,Category categoryParent) {
        this.name = name;
        this.parent = categoryParent;
    }
}
