package com.cherry.platform.springbootswagger.controller;

import com.cherry.platform.springbootswagger.annotation.NotIncludeSwagger;
import io.swagger.annotations.*;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author zhujun
 * @Email
 * @Date 2020/4/28  9:43 AM
 * @Version
 **/


@RestController
@RequestMapping(value = "/swagger")
@Api(value = "API - SwaggerController", description = "用户相关接口")
public class SwaggerController {


    @GetMapping(value = "/getUserById")
    @ApiOperation(value = "查询用户信息", notes = "根据Id查询用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户Id", required = false,dataType = "int", defaultValue = "1")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful — 请求已完成"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 403, message = "服务器拒绝请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")}
    )
    public Object get(Integer id) {
        User user = new User();
        user.setId(id);
        user.setAddress("上海市浦东新区");
        user.setName("john");
        return user;
    }


    @GetMapping(value = "/list")
    @ApiOperation(value = "查询用户信息", notes = "分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = false,dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = false,dataType = "int", defaultValue = "10"),
            @ApiImplicitParam(name = "name", value = "名称,用于模糊查询", required = false,dataType = "string", defaultValue = "")
    })
    public Object list(int page,int pageSize,String name) {
        List<User> users = new ArrayList<>();

        User user = new User();
        user.setId(1);
        user.setAddress("上海市浦东新区");
        user.setName("john");
        users.add(user);
        return user;
    }


    @PostMapping(value = "/save")
    @ApiOperation(value = "新增用户", notes = "用于保存，修改操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u", value = "用户信息", required = false,dataType = "User")
    })
    public Object save(User u){
        return "1";
    }



    @PostMapping(value = "/edit")
    @ApiOperation(value = "修改", notes = "用于保存，修改操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "u", value = "用户信息", required = false,dataType = "User")
    })
    @NotIncludeSwagger
    public Object edit(User u){
        return "1";
    }

}


@Data
@ApiModel(value = "User", description = "用户对象")
class User {

    @ApiModelProperty(value = "ID")
    int id;

    @ApiModelProperty(value = "姓名")
    String name;

    @ApiModelProperty(value = "别名")
    String nickName;

    @ApiModelProperty(value = "年龄")
    int age;

    @ApiModelProperty(value = "电话")
    String phone;

    @ApiModelProperty(value = "地址")
    String address;

}
