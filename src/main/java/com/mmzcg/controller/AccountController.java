package com.mmzcg.controller;


import com.mmzcg.entity.Response;
import com.mmzcg.service.AccountService;
import com.mmzcg.vo.GameListInput;
import com.mmzcg.vo.GameListOuput;
import com.mmzcg.vo.MixitemPage;
import com.mmzcg.vo.MixitemResponsePage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/account")
@Api(tags = "用户管理")
@Slf4j
public class AccountController extends BaseController {

    @Resource
    private AccountService accountService;

    @PostMapping("/list")
    @ApiOperation("用户管理列表")
    public Response<MixitemResponsePage<GameListOuput>> gameList(@RequestBody MixitemPage<GameListInput> gameListInput) {
        log.info("测试");
        // 设置业主ID
        gameListInput.getSearchParams().setParentAccountId(getParentAccountId());
        return accountService.gameList(gameListInput);
    }



}

