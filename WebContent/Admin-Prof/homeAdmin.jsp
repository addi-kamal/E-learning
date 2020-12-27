<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Admin CPanel </title>
</head>
<body>
    <div style="text-align: center">
        <h1>Welcome to E-Learning Website Admin Panel</h1>
        <b>${user.nom},${user.email}</b>
        <a href="/Authentification/Register">Add Admin / Lecturer</a>
        <br>
        <p>
         <%= session.getId() %>
        </p>
        <br>
        <a href="/Authentification/Log-in">Logout</a>
    </div>
</body>
</html>