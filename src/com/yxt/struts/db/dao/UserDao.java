package com.yxt.struts.db.dao;


import com.yxt.struts.db.bean.UserBean;

import java.sql.SQLException;

public interface UserDao extends IDao<UserBean>{
   UserBean getUserByName(String name) throws SQLException;
}
