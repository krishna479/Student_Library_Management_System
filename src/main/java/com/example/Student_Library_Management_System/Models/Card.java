package com.example.Student_Library_Management_System.Models;

import com.example.Student_Library_Management_System.ENUMS.CardStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="card")
public class Card {

    // Plan is to save this card in Db.
    // Rule 1 : Before saving I have to set its attributes
    // Rule 2 : Never forget Rule No1.



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;   //Its auto generated

    @CreationTimestamp  //Auto timestamp the time when an entry is created
    private Date createdOn;   //Its auto generated

    @UpdateTimestamp   //Sets time when an entry is updated.
    private Date updatedOn;   //Its auto generated

    @Enumerated(value = EnumType.STRING)
    private CardStatus cardStatus;  //SET this attribute by ourself


    //Card is the child wrt Student ,so the mapping for that is below(unidirectional)
    @OneToOne
    @JoinColumn
    private Student studentVariableName;  //This variable is used in the parent class,
    // while doing the bidirectional mapping


    //Now, Card is the parent wrt Book.So the mapping for that is below(Bidirectional - [ in Card->Book perspective] )
    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    private List<Book> booksIssued;


    // cardTrasaction Mapping
    @OneToMany(mappedBy = "card",cascade = CascadeType.ALL)
    private List<Transactions>transactionsList = new ArrayList<>();


    //Constructor
    public Card() {
        booksIssued = new ArrayList<>();
    }


    //  Getter - Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public Student getStudentVariableName() {
        return studentVariableName;
    }

    public void setStudentVariableName(Student studentVariableName) {
        this.studentVariableName = studentVariableName;
    }

    public List<Book> getBooksIssued() {
        return booksIssued;
    }

    public void setBooksIssued(List<Book> booksIssued) {
        this.booksIssued = booksIssued;
    }

    public List<Transactions> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<Transactions> transactionsList) {
        this.transactionsList = transactionsList;
    }
}
