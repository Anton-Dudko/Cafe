package com.example.kursach.domain;

import javax.persistence.*;


@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade ={ CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade ={ CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="product_id")
    private Dish dish;

    @Column(name="card_product_id")
    private long removeDishId;

    @Column(name="product_count")
    private int count;

    public Cart(){}

    public Cart(User user, Dish dish,long removeDishId,int count ) {
        this.user = user;
        this.dish = dish;
        this.removeDishId=removeDishId;
        this.count = count;
    }

    public long getRemoveDishId() {
        return removeDishId;
    }

    public void setRemoveDishId(long removeDishId) {
        this.removeDishId = removeDishId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}