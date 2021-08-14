package com.alke.hfs.core.service.impl;

import com.alke.hfs.core.pojo.entity.UserAccount;
import com.alke.hfs.core.mapper.UserAccountMapper;
import com.alke.hfs.core.service.UserAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户账户 服务实现类
 * </p>
 *
 * @author Alke
 * @since 2021-08-13
 */
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountMapper, UserAccount> implements UserAccountService {

}
