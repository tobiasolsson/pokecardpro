package com.pokecardpro.models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "watchlist")
public class Watchlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToMany(targetEntity = Auction.class)
    @JoinTable(
            name = "watchlist_auction",
            joinColumns = @JoinColumn(name = "watchlist_id"),
            inverseJoinColumns = @JoinColumn(name = "auction_id")
    )
    //@OneToMany(mappedBy = "watchlist")
    @JsonManagedReference
    private List<Auction> auctions;

    @OneToOne(targetEntity = User.class)
    //@JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    private User user;

    public Watchlist() {
    }

    public Watchlist(List<Auction> auctions, User user) {
        this.auctions = auctions;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Auction> getAuctions() {
        return auctions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
