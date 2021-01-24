<%--
  Created by IntelliJ IDEA.
  User: Gaukhartas
  Date: 28.08.2020
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<div class="container">
<%
    String error = (String)request.getParameter("error");
    if(error!=null&&error.equals("1")){
        out.print("User exists or passwords are different");
    }
%>
    <form method="post" action="/">
        <input type="hidden" name="act" value="register">
        <div class="form-group">
            <label for="exampleInputEmail1">LOGIN</label>
            <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="login">
            <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">PASSWORD</label>
            <input type="password" class="form-control" id="exampleInputPassword1" name="password">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword2">CONFIRM PASSWORD</label>
            <input type="password" class="form-control" id="exampleInputPassword2" name="re_password">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword3">FULL NAME</label>
            <input type="text" class="form-control" id="exampleInputPassword3" name="full_name">
        </div>
        <button type="submit" class="btn btn-primary">REGISTER</button>
    </form>
    </div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>
