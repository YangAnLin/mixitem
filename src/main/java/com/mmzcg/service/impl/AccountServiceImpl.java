package com.mmzcg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mmzcg.dao.AccountDao;
import com.mmzcg.entity.Account;
import com.mmzcg.entity.Response;
import com.mmzcg.mapper.AccountMapper;
import com.mmzcg.service.AccountService;
import com.mmzcg.vo.GameListInput;
import com.mmzcg.vo.GameListOuput;
import com.mmzcg.vo.MixitemPage;
import com.mmzcg.vo.MixitemResponsePage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Resource
    private AccountDao accountDao;

    @Override
    public Response<MixitemResponsePage<GameListOuput>> gameList(MixitemPage<GameListInput> parentAccountId) {
        return accountDao.gameList(parentAccountId);
    }

}
