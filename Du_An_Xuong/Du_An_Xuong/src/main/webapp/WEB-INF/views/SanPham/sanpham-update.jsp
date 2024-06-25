<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="border fw-bold" style="background-color: #f4f4f4;width: 98%;margin-left: 10px">
  <div style="margin-left: 10px;font-size: 12px">
<%--      enctype="multipart/form-data"--%>
      <form action="/sanpham/update" method="post" >
          ID: <br>
          <input type="text" name="id" value="${onesp.id}" readonly> <br>
          Hình ảnh: <br>
          <input type="file" name="anhsp" > <br>
          Mã sản phẩm: <br>
          <input type="text" name="masp" value="${onesp.masp}"> <br>
          Tên sản phẩm: <br>
          <input type="text" name="tensp" value="${onesp.tensp}"> <br>
          Số lượng: <br>
          <input type="text" name="soluongsp" value="${onesp.soluongsp}"> <br>
          Ngày tạo: <br>
          <input type="date" name="ngaytao" value="${onesp.ngaysua}"> <br>
          Ngày sửa: <br>
          <input type="date" name="ngaysua" value="${onesp.ngaysua}"> <br>
          Danh mục: <br>
          <select name="danhMuc">
              <c:forEach var="dm" items="${lsdanhmuc}">
                  <option value="${dm.id}" ${dm.id == onesp.danhMuc.id ? "selected" : ""}>${dm.tendanhmuc}</option>
              </c:forEach>
          </select><br>
          Nhà sản xuất: <br>
          <select name="nhaSanXuat">
              <c:forEach var="nsx" items="${lsnsx}">
                  <option value="${nsx.id}" ${nsx.id == onesp.nhaSanXuat.id ? "selected" : ""}>${nsx.tennsx}</option>
              </c:forEach>
          </select><br>
          Thương hiệu: <br>
          <select name="thuongHieu">
              <c:forEach var="th" items="${lsthuonghieu}">
                  <option value="${th.id}" ${th.id == onesp.thuongHieu.id ? "selected" : ""}>${th.tenthuonghieu}</option>
              </c:forEach>
          </select><br>
          Cổ áo: <br>
          <select name="coAo">
              <c:forEach var="coao" items="${lscoao}">
                  <option value="${coao.id}" ${coao.id == onesp.coAo.id ? "selected" : ""}>${coao.tencoao}</option>
              </c:forEach>
          </select><br>
          <button class="btn btn-warning">Update</button>
      </form>
  </div>
</div>
</body>
</html>