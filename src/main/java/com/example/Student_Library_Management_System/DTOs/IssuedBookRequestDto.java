package com.example.Student_Library_Management_System.DTOs;

public class IssuedBookRequestDto {
    private int BookId;
    private int cardId;

    public IssuedBookRequestDto() {
    }

    public int getBookId() {
        return BookId;
    }

    public void setBookId(int bookId) {
        BookId = bookId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }
}
