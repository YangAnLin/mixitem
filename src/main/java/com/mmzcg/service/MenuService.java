package com.mmzcg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mmzcg.entity.Menu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author anthony
 * @since 2020-10-06
 */
public interface MenuService extends IService<Menu> {

    List<Menu> menusList(Integer parentAccountId);

}
