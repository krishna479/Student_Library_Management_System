package com.example.Student_Library_Management_System.Service;

import com.example.Student_Library_Management_System.DTOs.BookRequestDto;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Repository.AuthorRepository;
import com.example.Student_Library_Management_System.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;

    public String addBook(BookRequestDto bookRequestDto){

        // I want to get the AuthorEntity? How can i get it?
        //First i will get Author Id
        int authorId = bookRequestDto.getAuthorId();



        //Now i will be Fetching the Author Entity
        Author author = authorRepository.findById(authorId).get();

        //Basic attributes are already set fom Postman

        //Setting the Foreign key Attribute in child class

        // Now , We need to update the listOfBooks written in the parent class
        List<Book> currentBooksWritten = author.getBooksWritten();
        Book book = new Book();
        book.setAuthor(author);
        book.setIssued(false);
        book.setName(bookRequestDto.getName());
        book.setPages(bookRequestDto.getPages());
        book.setGenre(bookRequestDto.getGenre());
        currentBooksWritten.add(book);
        author.setBooksWritten(currentBooksWritten);

        //Now the book is to be saved, but also author is alsoooo to be saved.

        //Why do we need to again save (updating) the author ?? bcz
        //because the author Entity has been updated....we need to resave/update it.

        authorRepository.save(author); //Date was modified

        //.save function works both as save function and as update function


        //bookRepo.save is not required : bcz it will be autocalled by cascading
        //effect

        return "Book Added successfully";

    }
}
