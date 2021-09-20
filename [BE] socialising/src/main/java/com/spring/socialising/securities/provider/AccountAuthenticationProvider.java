package com.spring.socialising.securities.provider;

import com.spring.socialising.securities.TaiKhoanDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * Created by: IntelliJ IDEA
 * User      : thangpx
 * Date      : 3/31/21
 * Time      : 17:56
 * Filename  : TaiKhoanAuthenticationProvider
 */
@Slf4j
@Service
public class AccountAuthenticationProvider implements AuthenticationProvider {

    private final TaiKhoanDetailsService taiKhoanDetailsService;

    private final MessageSource messageSource;

    public AccountAuthenticationProvider(TaiKhoanDetailsService taiKhoanDetailsService, MessageSource messageSource) {
        this.taiKhoanDetailsService = taiKhoanDetailsService;
        this.messageSource = messageSource;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AccountAuthenticationToken token = (AccountAuthenticationToken) authentication;
        String email = token.getName();
        String password = token.getCredentials() == null ? null : token.getCredentials().toString();
        boolean verifyCredentials = Boolean.parseBoolean(token.isVerifyCredentials().toString());
        UserDetails userDetails = taiKhoanDetailsService.loadUserByUsername(email);
        Locale locale = LocaleContextHolder.getLocale();
        if (!userDetails.isEnabled())
            throw new BadCredentialsException(messageSource.getMessage("error.account.disable", null, locale));
        if (verifyCredentials) {
            assert password != null;
            if (password.equals(userDetails.getPassword())) {
                return new AccountAuthenticationToken(email, password, verifyCredentials, userDetails.getAuthorities());
            } else {
                throw new BadCredentialsException(messageSource.getMessage("error.account.wrongpass", null, locale));
            }
        } else {
            return new AccountAuthenticationToken(email, "N/A", verifyCredentials, userDetails.getAuthorities());
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(AccountAuthenticationToken.class);
    }
}
