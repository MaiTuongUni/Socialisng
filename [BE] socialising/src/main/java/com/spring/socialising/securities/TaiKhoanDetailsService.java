package com.spring.socialising.securities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Slf4j
@Service
public class TaiKhoanDetailsService implements UserDetailsService {

    private final MessageSource messageSource;

    public TaiKhoanDetailsService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public JwtUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Locale locale = LocaleContextHolder.getLocale();

        return null;
    }

    private JwtUserDetails getUserDetails( ) {
        return null;
    }
}
