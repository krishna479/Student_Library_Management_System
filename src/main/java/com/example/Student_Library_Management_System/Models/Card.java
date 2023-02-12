package com.example.Student_Library_Management_System.Models;

import com.example.Student_Library_Management_System.ENUMS.CardStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

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

    @OneToOne
    @JoinColumn
    private Student studentVariableName;  //This variable is used in the parent class,
    // while doing the bidirectional mapping



    //Constructor
    public Card() {
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
}
