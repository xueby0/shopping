package com.neuedu.controller.backend;


import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/manage/order")
public class SendGoodsController {
    @Autowired
    IOrderService orderService;

    /**
     * 订单发货
     * */
    @RequestMapping(value = "/send_goods.do")
    public ServerResponse send_goods(HttpSession session, Long orderNo){

        UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENTUSER);
        //判断是否登录
        if (userInfo==null){
            return ServerResponse.serverResponseByError("需要登录");
        }
        //判断用户权限
        if(userInfo.getRole()!=Const.RoleEnum.ROLE_ADMIN.getCode()){
            return ServerResponse.serverResponseByError(Const.ReponseCodeEnum.NO_PRIVILEGE.getCode(),Const.ReponseCodeEnum.NO_PRIVILEGE.getDesc());
        }


        return orderService.send_goods(orderNo);
    }
}
