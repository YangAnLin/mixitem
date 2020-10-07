package com.mmzcg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mmzcg.dao.RolesDao;
import com.mmzcg.entity.Roles;
import com.mmzcg.mapper.RolesMapper;
import com.mmzcg.service.RolesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class RolesServiceImpl extends ServiceImpl<RolesMapper, Roles> implements RolesService {

    @Resource
    private RolesDao rolesDao;

    @Override
    public List<Roles> rolesList(Integer parentAccountId) {
        return rolesDao.selectFirstLevel(parentAccountId);
    }
}
