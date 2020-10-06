package com.mmzcg.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mmzcg.entity.Response;
import com.mmzcg.mapper.AccountMapper;
import com.mmzcg.vo.GameListInput;
import com.mmzcg.vo.GameListOuput;
import com.mmzcg.vo.MixitemPage;
import com.mmzcg.vo.MixitemResponsePage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountDao {

    @Resource
    private AccountMapper accountMapper;

    public Response<MixitemResponsePage<GameListOuput>> gameList(MixitemPage<GameListInput> parentAccountId) {

        GameListInput searchParams = parentAccountId.getSearchParams();

        IPage<GameListOuput> pages = accountMapper.selectGameList(new Page<>(parentAccountId.getCurrent(), parentAccountId.getSize()), searchParams);

        MixitemResponsePage<GameListOuput> objectMixitemPage = new MixitemResponsePage<>();
        objectMixitemPage.setTotal(pages.getTotal());
        objectMixitemPage.setSize(pages.getSize());
        objectMixitemPage.setCurrent(pages.getCurrent());
        objectMixitemPage.setData(pages.getRecords());
        objectMixitemPage.setPages(pages.getPages());


        return Response.successData(objectMixitemPage);
    }
}
