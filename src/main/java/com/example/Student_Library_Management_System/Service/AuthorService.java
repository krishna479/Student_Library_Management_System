package com.example.Student_Library_Management_System.Service;

import com.example.Student_Library_Management_System.DTOs.AuthorEntryDto;
import com.example.Student_Library_Management_System.DTOs.AuthorResponseDto;
import com.example.Student_Library_Management_System.DTOs.BookRequestDto;
import com.example.Student_Library_Management_System.DTOs.BookResponseDto;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public String createAuthor(AuthorEntryDto authorEntryDto){

        //converting authorentrydto  ----> author entity
        //create an obj of type of author
        Author author = new Author();

        // Setting the values from authorEntryDto to this 
        author.setName(authorEntryDto.getName());
        author.setAge(authorEntryDto.getAge());
        author.setCountry(authorEntryDto.getCountry());
        author.setRating(authorEntryDto.getRating());


      authorRepository.save(author);

      return "Author added Successfully.";
    }

    public AuthorResponseDto getAuthor(int authorId) {
      Author author=   authorRepository.findById(authorId).get();


      // list<Book> ---> list<BooksWrittenDto>
        List<Book> bookList = author.getBooksWritten();
      List<BookResponseDto> booksWrittenDto = new ArrayList<>();

        for(Book b: bookList){
            BookResponseDto bookResponseDto = new BookResponseDto();
            bookResponseDto.setGenre(b.getGenre());
            bookResponseDto.setName(b.getName());
            bookResponseDto.setPages(b.getPages());

            booksWrittenDto.add(bookResponseDto);

        }
        // setting authorResponseDto values;
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        authorResponseDto.setName(author.getName());
        authorResponseDto.setAge(author.getAge());
        authorResponseDto.setCountry(author.getCountry());
        authorResponseDto.setRating(author.getRating());
        authorResponseDto.setBooksWritten(booksWrittenDto);

        return authorResponseDto;





    }
}
