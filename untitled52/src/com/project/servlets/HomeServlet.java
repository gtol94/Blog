package com.project.servlets;

import com.project.db.DBManager;
import com.project.entities.Blog;
import com.project.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "HomeServlet", value = "/home")
public class HomeServlet extends HttpServlet {
    private DBManager manager;

    public void init(){
        manager = new DBManager();
        manager.connect();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String act = request.getParameter("act");
        String uri ="";

        User sessionUser = (User)request.getSession().getAttribute("USER");
        if(act!=null){
            if(sessionUser!=null){
                if(act.equals("logout")){
                    request.getSession().removeAttribute("USER");
                    uri = "?page=login";
                }else if(act.equals("addblog")){
                    String title = request.getParameter("title");
                    String short_content = request.getParameter("short_content");
                    String content = request.getParameter("content");

                    manager.addBlog(new Blog(null, sessionUser, title, short_content, content, null));
                    uri = "?page=addblog&success=1";
                }
            }else {
                if(act.equals("login")){
                    String login = request.getParameter("login");
                    String password = request.getParameter("password");
                    User user = manager.getUser(login, password);
                    if(user!=null){
                        request.getSession().setAttribute("USER", user);
                    }else {
                        uri ="?page=login&error=1";
                    }
                }else if(act.equals("register")){
                    uri = "?page=register&error=1";
                    String login = request.getParameter("login");
                    String password = request.getParameter("password");
                    String rePassword = request.getParameter("re_password");
                    String fullName = request.getParameter("full_name");
                    if(login!=null&&password!=null&&rePassword!=null&&rePassword.equals(password)){
                        manager.addUser(new User(null, login, password, fullName));
                        uri = "?page=login";
                    }
                }
            }
        }
        response.sendRedirect("/"+uri);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        String jspPage = "register";
        User sessionUser = (User)request.getSession().getAttribute("USER");
        if(page!=null){
            if(sessionUser!=null){
                if(page.equals("home")){
                    jspPage = "home";
                }else if(page.equals("read")){
                    jspPage = "read";
                }else if(page.equals("profile")){
                    jspPage = "home";
                }else if(page.equals("addblog")){
                    jspPage = "addblog";
                }
            }else {
                if(page.equals("home")){
                    jspPage = "home";
                }else if(page.equals("read")){
                    jspPage = "read";
                }else if(page.equals("login")){
                    jspPage = "login";
                }else if(page.equals("register")){
                    jspPage = "register";
                }
            }
        }
        if(jspPage.equals("home")){
            ArrayList<Blog> blogs = manager.getAllBlogs();
            request.setAttribute("blogs", blogs);
        }else if(jspPage.equals("login")){

        }else if(jspPage.equals("register")){

        }else if(jspPage.equals("profile")){

        }else if(jspPage.equals("addblog")){

        }else if(jspPage.equals("read")){
            try{
                Long id = Long.parseLong(request.getParameter("id"));
                Blog blog = manager.getBlog(id);
                request.setAttribute("blog", blog);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("/"+jspPage+".jsp").forward(request, response);

    }
}
