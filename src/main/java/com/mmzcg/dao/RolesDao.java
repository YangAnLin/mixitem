package com.mmzcg.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mmzcg.entity.Roles;
import com.mmzcg.mapper.RolesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RolesDao {

    @Resource
    private RolesMapper rolesMapper;

    public List<Roles> selectFirstLevel(Integer parentAccountId) {

        // 查最上级
        LambdaQueryWrapper<Roles> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Roles::getParentId, 0);
        wrapper.eq(Roles::getOwenParentId, parentAccountId);
        List<Roles> Roless = rolesMapper.selectList(wrapper);
        crictChildren(Roless);
        return Roless;
    }

    private void crictChildren(List<Roles> rolesList) {

        for (Roles roles : rolesList) {
            LambdaQueryWrapper<Roles> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Roles::getParentId, roles.getId());
            wrapper.eq(Roles::getOwenParentId, roles.getOwenParentId());
            List<Roles> list = rolesMapper.selectList(wrapper);
            roles.setChildren(list);
            if (list.size() > 0) {
                crictChildren(list);
            }
        }


    }
}
