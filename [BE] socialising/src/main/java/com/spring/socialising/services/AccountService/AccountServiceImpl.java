package com.spring.socialising.services.AccountService;

import com.spring.socialising.entities.Account;
import com.spring.socialising.repositories.AccountRepository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account findAccountByPhoneNumber(String phongnumber) {
        return accountRepository.findByUserName(phongnumber);
    }
}
