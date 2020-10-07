package com.mmzcg.controller;


import com.mmzcg.entity.Response;
import com.mmzcg.entity.Roles;
import com.mmzcg.service.RolesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/roles")
@Api(tags = "角色管理")
public class RolesController extends BaseController{

    @Resource
    private RolesService rolesService;

    @GetMapping("/list")
    @ApiOperation("角色列表")
    public Response<List<Roles>> list() {
        // 设置业主ID
        Integer parentAccountId = getParentAccountId();
        List<Roles> roles = rolesService.rolesList(parentAccountId);
        return Response.successData(roles);

    }
}

