package com.alke.hfs.core.service.impl;

import com.alke.hfs.core.pojo.entity.UserInfo;
import com.alke.hfs.core.mapper.UserInfoMapper;
import com.alke.hfs.core.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户基本信息 服务实现类
 * </p>
 *
 * @author Alke
 * @since 2021-08-13
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
