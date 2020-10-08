package com.mmzcg.controller;


import com.mmzcg.entity.Menu;
import com.mmzcg.entity.Response;
import com.mmzcg.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/menu")
@Api(tags = "菜单管理")
public class MenuController extends BaseController {


    @Resource
    private MenuService menus;

    @GetMapping("/list")
    @ApiOperation("菜单列表")
    public Response<List<Menu>> menus() {
        // 设置业主ID
        Integer parentAccountId = getParentAccountId();
        return Response.successData(menus.menusList(parentAccountId));
    }
}

