package com.mmzcg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mmzcg.entity.Roles;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author anthony
 * @since 2020-10-07
 */
public interface RolesService extends IService<Roles> {

    List<Roles> rolesList(Integer parentAccountId);
}
