package com.mmzcg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mmzcg.dao.MenuDao;
import com.mmzcg.entity.Menu;
import com.mmzcg.mapper.MenuMapper;
import com.mmzcg.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author anthony
 * @since 2020-10-06
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Resource
    private MenuDao menuDao;

    @Override
    public List<Menu> menusList(Integer parentAccountId) {
        return menuDao.selectFirstLevel(parentAccountId);
    }

}
