package com.group.libraryapp.domain.loanhistory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory, Long> {
    //SELECT * FROM user_loan_history Book_name = ? and is_return -?
    boolean existsByBookNameAndIsReturn(String name, boolean isReturn);
}
