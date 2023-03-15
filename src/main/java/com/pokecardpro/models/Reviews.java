package com.pokecardpro.models;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "comment_owner", referencedColumnName = "id")
    private User commentOwner;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "comment_receiver", referencedColumnName = "id")
    private User commentReceiver;

    private String comment;

    @Column(name = "grade_seller")
    private int gradeSeller;

    @Column(name = "grade_buyer")
    private int gradeBuyer;

    public Reviews() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getCommentOwner() {
        return commentOwner;
    }

    public void setCommentOwner(User commentOwner) {
        this.commentOwner = commentOwner;
    }

    public User getCommentReceiver() {
        return commentReceiver;
    }

    public void setCommentReceiver(User commentReceiver) {
        this.commentReceiver = commentReceiver;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getGradeSeller() {
        return gradeSeller;
    }

    public void setGradeSeller(int gradeSeller) {
        this.gradeSeller = gradeSeller;
    }

    public int getGradeBuyer() {
        return gradeBuyer;
    }

    public void setGradeBuyer(int gradeBuyer) {
        this.gradeBuyer = gradeBuyer;
    }

    public Reviews(User commentOwner, User commentReceiver, String comment, int gradeSeller, int gradeBuyer) {
        this.commentOwner = commentOwner;
        this.commentReceiver = commentReceiver;
        this.comment = comment;
        this.gradeSeller = gradeSeller;
        this.gradeBuyer = gradeBuyer;
    }
}
