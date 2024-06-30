<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<h2 id="tieu_de" class="text-center">chất liệu</h2>
<div class="container d-flex justify-content-center mt-5">
    <form:form method="post" action="/sanpham/updateCL/${chatlieu.id}" modelAttribute="chatlieu" >
        <div class="mt-3">
            <label for="ma_chat_lieu">mã chất liệu</label>
            <form:input path="ma_chat_lieu" id="ma_chat_lieu"/>
            <div class="text-danger"><form:errors id="ma_chat_lieu" path="ma_chat_lieu" /> </div>
        </div>
        <div class="mt-5">
            <label for="ten_chat_lieu">tên chất liệu</label>
            <form:input path="ten_chat_lieu" id="ten_chat_lieu"/>
            <div class="text-danger"><form:errors id="ten_chat_lieu" path="ten_chat_lieu" /> </div>
        </div>
        <div class=" mt-3 d-flex justify-content-center">
            <button class="btn btn-success ">GỬI</button>
        </div>

    </form:form>

</div>
<script>
    function changeColor() {
        const colors = ['text-primary',
            'text-secondary',
            'text-success',
            'text-danger',
            'text-warning',
            'text-info',
            'text-dark'];
        const element = document.getElementById('tieu_de');
        const currentColor = element.classList[1];
        const currentIndex = colors.indexOf(currentColor);
        const nextIndex = (currentIndex + 1) % colors.length;
        const nextColor = colors[nextIndex];
        element.classList.remove(currentColor);
        element.classList.add(nextColor);
    }
    setInterval(changeColor, 3000);
</script>

</body>
</html>