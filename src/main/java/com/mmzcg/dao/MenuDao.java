package com.mmzcg.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mmzcg.entity.Menu;
import com.mmzcg.mapper.MenuMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuDao {

    @Resource
    private MenuMapper menuMapper;

    public List<Menu> selectFirstLevel(Integer parentAccountId) {

        // 查最上级
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Menu::getParentId, 0);
        wrapper.eq(Menu::getOwenParentId, parentAccountId);
        List<Menu> menus = menuMapper.selectList(wrapper);
        crictChildren(menus);
        return menus;
    }

    private void crictChildren(List<Menu> menus) {

        for (Menu menu : menus) {
            LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Menu::getParentId, menu.getId());
            wrapper.eq(Menu::getOwenParentId, menu.getOwenParentId());
            List<Menu> list = menuMapper.selectList(wrapper);
            menu.setChildren(list);
            if (list.size() > 0) {
                crictChildren(list);
            }
        }


    }
}
