package com.mmzcg.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mmzcg.dao.AccountDao;
import com.mmzcg.dao.MenuDao;
import com.mmzcg.entity.Account;
import com.mmzcg.entity.Menu;
import com.mmzcg.entity.Response;
import com.mmzcg.mapper.AccountMapper;
import com.mmzcg.service.AccountService;
import com.mmzcg.vo.GameListInput;
import com.mmzcg.vo.GameListOuput;
import com.mmzcg.vo.MixitemPage;
import com.mmzcg.vo.MixitemResponsePage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Resource
    private AccountDao accountDao;

    @Resource
    private MenuDao menuDao;

    @Override
    public Response<MixitemResponsePage<GameListOuput>> gameList(MixitemPage<GameListInput> parentAccountId) {
        return accountDao.gameList(parentAccountId);
    }

    @Override
    public Response<MixitemResponsePage<GameListOuput>> permissions(Integer parentAccountId) {

        return null;
    }

    @Override
    public List<Menu> menus(Integer parentAccountId) {
        return menuDao.selectFirstLevel(parentAccountId);
    }
}
