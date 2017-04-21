package com.hand.actions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hand.commonKey.CommonKey;
import com.hand.entity.User;
import com.hand.paging.Pager;
import com.hand.paging.PagingService;
import com.hand.service.IUserService;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static com.hand.commonKey.CommonKey.PAGESIZE;

public class UserAction extends BaseAction {

    @Resource(name = "userService")
    private IUserService userService;

    @Resource(name = "pagingService")
    private PagingService<User> pagingUserService;

    public void createUser() throws Exception {
        User user = new User();
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        System.out.println("---》createUser 方法");
        String accountName = request.getParameter("accountName");
        String realName    = request.getParameter("realName");
        String password    = request.getParameter("password");
        String identity    = request.getParameter("identity");
        if (accountName == null|| password == null)
        {
            System.out.println("由于参数导致创建失败--用户名或者是密码没有填写");
            out.print(CommonKey.PARAMETERDEFICIENCY);
            return;
        }
        // 校验用户名是否重复
        String sql = "SELECT * FROM user WHERE accountName='"+accountName+"';";
        List<User> userList = null;
        try {
            userList = userService.FindBySQL(sql);
        } catch (Exception e) {
            System.out.println("深层调用导致登陆失败");
            out.print(CommonKey.FAILCAUSEDEEP);
            e.printStackTrace();
            return;
        }
        if(userList.size()!=0){
            System.out.println("用户名重复");
            out.print(CommonKey.ACCOUNTNAMEDUPLICATE);
            return;
        }

        user.setAccountName(accountName);
        user.setRealName(realName);
        user.setPassword(password);
        if(Integer.parseInt(identity)==CommonKey.USER || Integer.parseInt(identity) == CommonKey.ADMIN){
            user.setIdentity(Integer.parseInt(identity));
        }else {
            System.out.print("用户角色 不正确");
            out.print(CommonKey.CREATFAIL);
        }
        try {
            userService.createUser(user);
        } catch (Exception e) {
            System.out.println("深层调用导致创建失败");
            out.print(CommonKey.ACCOUNTNAMEDUPLICATE);
            e.printStackTrace();
            return;
        }
        System.out.println("创建成功");
        out.print(CommonKey.CREATESUCCESS);   // 创建成功
    }


    public void updateUser() throws Exception {
        System.out.print("---》updateUser 方法");
        User user = new User();
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        System.out.println("---》updateUser 方法");
        String id          = request.getParameter("id");
        if(id==null){
            System.out.print("没有userID 无法进行更新");
            out.print(CommonKey.UPDATEFAIL);
            return;
        }
        String accountName = request.getParameter("accountName");
        String realName = request.getParameter("realName");
        String password = request.getParameter("password");
        String identity = request.getParameter("identity");
        user.setId(Integer.parseInt(id));
        if(accountName  !=null)user.setAccountName(accountName);
        if(realName     !=null)user.setRealName(realName);
        if(password     !=null)user.setPassword(password);
        if(identity     !=null)user.setIdentity(Integer.parseInt(identity));

        try {
            userService.updateUser(user);
        } catch (Exception e) {
            System.out.println("深层调用导致更新失败");
            out.print(CommonKey.FAILCAUSEDEEP);
            e.printStackTrace();
            return;
        }
        System.out.println("更新成功");
        out.print(CommonKey.UPDATESUCCESS);   // 更新成功

    }

    public void deleteUser() throws Exception {
        // 删除 User
        System.out.print("---》deleteUser 方法");
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        if(id==null){
            System.out.print("没有userID 无法进行删除");
            out.print(CommonKey.DELETEFAIL);
            return;
        }
        try {
            User user = userService.FindByID(Integer.parseInt(id));
            user.setDeleteFlag(1);
            userService.updateUser(user);
        } catch (Exception e) {
            System.out.println("深层调用导致删除失败");
            out.print(CommonKey.FAILCAUSEDEEP);
            e.printStackTrace();
            return;
        }
        System.out.println("删除成功");
        out.print(CommonKey.DELETESUCCESS);
    }

    public void pageingUser() throws Exception {
        System.out.print("---》pageingUser 方法");
        // user 信息 的分页查询
        int pageNo = Integer.parseInt(request.getParameter("pageNo"));
        String identity = request.getParameter("identity");
        Criterion criterion = null;
        if(identity!=null)criterion =  Restrictions.eq("identity",Integer.parseInt(identity));

        pagingUserService.PagingService(User.class);
        Pager pager = pagingUserService.paging(pageNo,PAGESIZE,criterion);

        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("数据："+pager.toString());
        for (Object user: pager.getResult()){
            System.out.println("user --->：  "+((User)user).toString());
        }
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        out.print(gson.toJson(pager));
        System.out.println("发送数据=="+gson.toJson(pager));
    }

    public void selectUserById() throws Exception {
        System.out.println("---》selectUserById 方法");
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out    = response.getWriter();
        String userId = request.getParameter("userId");
        if(null == userId || "".equals(userId)){
            System.out.print("用户 ID 不正确");
            out.print(CommonKey.PARAMETERDEFICIENCY);
            return;
        }
        User user = null;
        try {
            user = userService.FindByID(Integer.parseInt(userId));
        } catch (Exception e) {
            System.out.println("深层调用导致登陆失败");
            out.print(CommonKey.DELETESUCCESS);
            e.printStackTrace();
            return;
        }
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        out.print(gson.toJson((User)user));
    }



    public void login() throws Exception {
        System.out.println("---》login 方法");
        // 用户登陆
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out    = response.getWriter();
        String accountName = request.getParameter("accountName");
        String password    = request.getParameter("password");
        if(accountName == null ||password == null){
            System.out.print("用户名密码 缺失无法进行登陆");
            out.print(CommonKey.PARAMETERDEFICIENCY);
            return;
        }
        String sql = "SELECT * FROM user WHERE accountName='"+accountName+"' AND password = '"+password+"';";
        List<User> userList = null;
        try {
            userList = userService.FindBySQL(sql);
        } catch (Exception e) {
            System.out.println("深层调用导致登陆失败");
            out.print(CommonKey.DELETESUCCESS);
            e.printStackTrace();
            return;
        }
        if(userList.size() == 1){
            System.out.println("登陆成功");
            session.put("user",userList.get(0));
//            out.print(CommonKey.LOGINSUCCESS); // 登陆成功
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            out.print(gson.toJson((User)session.get("user")));
        }else {
            System.out.println("登陆失败\n可能原因:\n1.用户名错误\n2.密码错误\n3.有相同用户名和密码的用户");
            out.print(CommonKey.LOGINFAIL); // 登陆失败
        }
    }

    public void logout() throws Exception{
        System.out.println("---》logout 方法");
        // 用户退出
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        if(session.get("user")==null){
            System.out.println("您还没有进行登陆");
            out.println(CommonKey.LOGOUTSUCCESS);// 退出登陆成功
            return;
        }
        session.remove("user");
        System.out.println("退出登陆成功");
        out.print(CommonKey.LOGOUTSUCCESS);// 退出登陆成功
    }

    public void isLogin() throws Exception{
        System.out.println("---》isLogin 方法   判断用户是否登陆");
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        // 判断用户是否登陆
        if(session.get("user")==null){
            System.out.println("用户没有进行登陆");
            out.print(CommonKey.NOTLOGIN); // 用户没有进行登陆
        }else{
            System.out.println("用户已经登陆");
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            out.print(gson.toJson((User)session.get("user")));
        }
    }
}