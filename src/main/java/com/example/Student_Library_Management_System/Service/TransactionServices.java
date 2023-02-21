package com.example.Student_Library_Management_System.Service;

import com.example.Student_Library_Management_System.DTOs.IssuedBookRequestDto;
import com.example.Student_Library_Management_System.ENUMS.CardStatus;
import com.example.Student_Library_Management_System.ENUMS.TransactionStatus;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Transactions;
import com.example.Student_Library_Management_System.Repository.AuthorRepository;
import com.example.Student_Library_Management_System.Repository.BookRepository;
import com.example.Student_Library_Management_System.Repository.CardRepository;
import com.example.Student_Library_Management_System.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Service
public class TransactionServices {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CardRepository cardRepository;
    @Autowired
    BookRepository bookRepository;

public String issueBook(IssuedBookRequestDto issuedBookRequestDto) throws Exception {

    int cardId = issuedBookRequestDto.getCardId();
    int bookId = issuedBookRequestDto.getBookId();

   Book book= bookRepository.findById(bookId).get();
   Card card = cardRepository.findById(cardId).get();


   // goal is to make a transaction Entity and set its attributes;
    Transactions transactions = new Transactions();

    transactions.setBook(book);
    transactions.setCard(card);

  // setting the attributes

    transactions.setTransactionId(UUID.randomUUID().toString());
    transactions.setIssuedOperation(true);
    transactions.setTransactionStatus(TransactionStatus.PENDING);

    // check for validations

    if(book == null || book.isIssued()==true){
        transactions.setTransactionStatus(TransactionStatus.FAILED);
        transactionRepository.save(transactions);
        throw new Exception("Book not available");
    }
    if(card == null || card.getCardStatus() != CardStatus.ACTIVATED){
        transactions.setTransactionStatus(TransactionStatus.FAILED);
        transactionRepository.save(transactions);
        throw new Exception("Card in not valid");
    }

    // reached success case
transactions.setTransactionStatus(TransactionStatus.SUCCESS);
   // transactions.setTransactionDate();

    // Set attributes of book and transaction bidirectinal

    book.setIssued(true);
    //btw the book and transaction : bidirectional
   List<Transactions> listOfTransactionforBook = book.getListOfTransactions();
   listOfTransactionforBook.add(transactions);
   book.setListOfTransactions(listOfTransactionforBook);

   // set attributes for card and  books bidirectional
    List<Book> issuedBooksForCard = card.getBooksIssued();
    issuedBooksForCard.add(book);
    card.setBooksIssued(issuedBooksForCard);

   //card and transaction : birdrectional (parent classs)
    List<Transactions> transactionsListForCard = card.getTransactionsList();
    transactionsListForCard.add(transactions);
    card.setTransactionsList(transactionsListForCard);

    //Done now only save only parent(cascade on book and transaction

    cardRepository.save(card);

return "Book isssue Successfully";


}

    public String getTransaction(int bookId, int cardId) {
    List<Transactions>transactionList= transactionRepository.getTransactionForBookAndCard(bookId,cardId);
       return transactionList.get(0).getTransactionId();
    }
}
