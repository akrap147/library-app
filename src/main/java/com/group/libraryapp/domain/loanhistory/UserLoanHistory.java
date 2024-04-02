package com.group.libraryapp.domain.loanhistory;

import com.group.libraryapp.domain.user.User;

import javax.persistence.*;

@Entity
public class UserLoanHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;
    @ManyToOne // 내가 Many고 너가 One이다.
    private User user; // user를 받는 것이라고 생각해도 될듯 하다. 그런데 해당하는 id는 어떻게 받는데?
    private String bookName;
    private boolean isReturn;

    //table은 어디에 간거냐? -> 그대로 user-id가 남아 있읉텐데

    protected UserLoanHistory(){
    }
    public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        this.isReturn = false;
    }
    public void doReturn(){
        this.isReturn = true;
    }

    public String getBookName() {
        return this.bookName;
    }
}
