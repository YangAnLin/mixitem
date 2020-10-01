package com.mmzcg.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mmzcg.entity.Account;
import com.mmzcg.entity.CommonPage;
import com.mmzcg.entity.Response;
import com.mmzcg.service.AccountService;
import com.mmzcg.vo.GameListInput;
import com.mmzcg.vo.GameListOuput;
import com.mmzcg.vo.MixitemPage;
import com.mmzcg.vo.MixitemResponsePage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/account")
@Api(tags = "游戏用户管理")
public class AccountController extends BaseController {

    @Resource
    private AccountService accountService;

    @PostMapping("/list")
    @ApiOperation("游戏用户管理列表")
    public Response<MixitemResponsePage<GameListOuput>> gameList(@RequestBody MixitemPage<GameListInput> gameListInput) {
        // 设置业主ID
//        gameListInput.getSearchParams().setParentAccountId(getParentAccountId());
        return accountService.gameList(gameListInput);
    }

}

