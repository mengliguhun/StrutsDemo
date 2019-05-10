package com.yxt.struts;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yxt.struts.db.bean.UserBean;
import com.yxt.struts.db.dao.UserDaoImpl;

import java.sql.SQLException;

public class LoginAction extends ActionSupport {
    private String username;
    private String password;

    @Override
    public String execute() throws Exception {
        String result = "数据库读取异常";
        if (username == null || username.isEmpty()){
            result = "用户名为空";
        } else if (password == null || password.isEmpty()){
            result = "密码为空";
        } else {
            try {
                UserBean user = UserDaoImpl.getInstance().getUserByName(username);
                if (user == null){
                    result = "不存在此用户";
                } else {
                    if (password.endsWith(user.getUserPwd())){
                        return SUCCESS;
                    } else {
                        result = "用户名密码不匹配，登录失败";
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        ActionContext.getContext().put("error",result);
        return ERROR;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
