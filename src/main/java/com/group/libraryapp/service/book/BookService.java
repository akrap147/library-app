package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.loanhistory.UserLoanHistory;
import com.group.libraryapp.domain.loanhistory.UserLoanHistoryRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.dto.book.request.BookLoanRequest;
import com.group.libraryapp.dto.book.request.BookReturnRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;
    public BookService(BookRepository bookRepository, UserLoanHistoryRepository userLoanHistoryRepository,
                       UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveBook(BookCreateRequest request){
        bookRepository.save(new Book(request.getName()));
    }
    @Transactional
    public void loanBook(BookLoanRequest request){
        //1.책 정보를 기입한다. -> 우리가 찾은 book을 객체로 받는다.
        Book book = bookRepository.findByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);
        //2.대출 정보를 입력해서 대출인지 확인한다. -> 만약 우리가 받은 책의 이름이, isReturn -> False이면 throw
        //3.만약에 확인했는데 대출 중이라면 대출 에외를 시킨다.
        if (userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)){
            throw new IllegalArgumentException("대출된 책입니다.");
        }
        //유저 정보 가져오기.
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);
        user.loanBook(book.getName());
        //유저 정보를 가져와 책 정보를 기반으로 userLoanHistory에 저장
        // 알아서 Spirng JPA가 유저 정보를 받아서 변환시킨다.


    }
    @Transactional
    public void returnBook(BookReturnRequest request){
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new); // getUser로 받으면 그 user가 빌린 책의 list가 나온다.
        user.returnBook(request.getBookName());
    }
}
