package com.alke.hfs.core.service.impl;

import com.alke.hfs.core.pojo.entity.UserLoginRecord;
import com.alke.hfs.core.mapper.UserLoginRecordMapper;
import com.alke.hfs.core.service.UserLoginRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户登录记录表 服务实现类
 * </p>
 *
 * @author Alke
 * @since 2021-08-13
 */
@Service
public class UserLoginRecordServiceImpl extends ServiceImpl<UserLoginRecordMapper, UserLoginRecord> implements UserLoginRecordService {

}
