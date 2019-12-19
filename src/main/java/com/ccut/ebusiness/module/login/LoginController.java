package com.ccut.ebusiness.module.login;

import com.ccut.ebusiness.module.system.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *   @author Meng.yang
 *   @ProjectName
 *   @title:L oginController
 *   @Description: 登录接口
 *   @date 2019/12/02
*/
@Api(description = "主页main接口")
@RestController
public class LoginController extends BaseController {

    @RequestMapping("/")
    public String index(){
        return "index.html";
    }

}
