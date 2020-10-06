package com.mmzcg.controller;


import com.mmzcg.entity.Menu;
import com.mmzcg.entity.Response;
import com.mmzcg.service.AccountService;
import com.mmzcg.vo.GameListInput;
import com.mmzcg.vo.GameListOuput;
import com.mmzcg.vo.MixitemPage;
import com.mmzcg.vo.MixitemResponsePage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
        gameListInput.getSearchParams().setParentAccountId(getParentAccountId());
        return accountService.gameList(gameListInput);
    }

    @GetMapping("/menus")
    @ApiOperation("菜单列表")
    public Response<List<Menu>> menus() {
        // 设置业主ID
        Integer parentAccountId = getParentAccountId();
        return Response.successData(accountService.menus(parentAccountId));
    }

}

