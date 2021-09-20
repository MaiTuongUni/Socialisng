package com.spring.socialising.services.taikhoan;

import com.spring.socialising.utils.EnumRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TaiKhoanServiceImpl implements TaiKhoanService {

    private final MessageSource messageSource;

    public TaiKhoanServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public List<String> getRoleTaiKhoans() {
        return Arrays.stream(EnumRole.values()).map(Enum::name)
                .filter(role -> role.equalsIgnoreCase(EnumRole.ROLE_ADMIN.name()) || role.equalsIgnoreCase(EnumRole.ROLE_THU_KY_KHOA.name()))
                .collect(Collectors.toList());
    }
}
