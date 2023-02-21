package com.pokecardpro.models;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "watchlist")
public class Watchlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToMany(targetEntity = Auction.class)
    @JoinColumn(name = "auction_id", referencedColumnName = "id")
    private List<Auction> auctions;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Watchlist() {
    }

    public Watchlist(int id, List<Auction> auctions, User user) {
        this.id = id;
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

    public void setAuctions(List<Auction> auctions) {
        this.auctions = auctions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
