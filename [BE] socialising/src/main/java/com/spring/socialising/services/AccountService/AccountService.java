package com.spring.socialising.services.AccountService;

import com.spring.socialising.entities.Account;

public interface AccountService {

    Account findAccountByPhoneNumber(String phongnumber);

}
