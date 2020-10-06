package com.mmzcg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mmzcg.entity.Account;
import com.mmzcg.entity.Menu;
import com.mmzcg.entity.Response;
import com.mmzcg.vo.GameListInput;
import com.mmzcg.vo.GameListOuput;
import com.mmzcg.vo.MixitemPage;
import com.mmzcg.vo.MixitemResponsePage;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author anthony
 * @since 2020-09-26
 */
public interface AccountService extends IService<Account>  {

    Response<MixitemResponsePage<GameListOuput>> gameList(MixitemPage<GameListInput> parentAccountId);

    Response<MixitemResponsePage<GameListOuput>> permissions(Integer parentAccountId);

    List<Menu> menus(Integer parentAccountId);
}
