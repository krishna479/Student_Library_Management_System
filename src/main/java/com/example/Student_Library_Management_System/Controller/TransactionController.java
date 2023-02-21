package com.example.Student_Library_Management_System.Controller;

import com.example.Student_Library_Management_System.DTOs.IssuedBookRequestDto;
import com.example.Student_Library_Management_System.Service.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    TransactionServices transactionServices;
    @PostMapping("issueBook")
    public String issueBook(@RequestBody IssuedBookRequestDto issuedBookRequestDto) throws Exception {
    return transactionServices.issueBook(issuedBookRequestDto);
    }
    @GetMapping("/getTxnInfo")
    public String getTransactionEntry(@RequestParam("bookId") Integer bookId, @RequestParam("cardId") Integer cardId){
        return transactionServices.getTransaction(bookId,cardId);

    }

}
