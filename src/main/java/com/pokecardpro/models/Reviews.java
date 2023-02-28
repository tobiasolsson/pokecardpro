package com.pokecardpro.models;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Reviews {

    @Id
    @Column(name = "id")
    private String id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "comment_owner", referencedColumnName = "id")
    private User comment_Owner;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "comment_reciver", referencedColumnName = "id")
    private User comment_Reciver;

    private String comment;

    private int grade_Seller;

    private int grade_Buyer;

    public Reviews() {
    }

    public Reviews(String id, User comment_Owner, User comment_Reciver, String comment, int grade_Seller, int grade_Buyer) {
        this.id = id;
        this.comment_Owner = comment_Owner;
        this.comment_Reciver = comment_Reciver;
        this.comment = comment;
        this.grade_Seller = grade_Seller;
        this.grade_Buyer = grade_Buyer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getComment_Owner() {
        return comment_Owner;
    }

    public void setComment_Owner(User comment_Owner) {
        this.comment_Owner = comment_Owner;
    }

    public User getComment_Reciver() {
        return comment_Reciver;
    }

    public void setComment_Reciver(User comment_Reciver) {
        this.comment_Reciver = comment_Reciver;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getGrade_Seller() {
        return grade_Seller;
    }

    public void setGrade_Seller(int grade_Seller) {
        this.grade_Seller = grade_Seller;
    }

    public int getGrade_Buyer() {
        return grade_Buyer;
    }

    public void setGrade_Buyer(int grade_Buyer) {
        this.grade_Buyer = grade_Buyer;
    }
}
