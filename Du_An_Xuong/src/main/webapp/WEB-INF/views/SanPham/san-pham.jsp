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
<a href="/sanpham/viewadd">Thêm sản phẩm</a>
<div class="border" style="background-color: #f4f4f4;height: 300px; font-size: 12px">
        <br>
        <div style="margin-left: 530px" class="fw-bold">
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <c:forEach begin="${1}" end="${hienthi.totalPages}" var="i">
                        <li class="page-item">
                            <a class="page-link" href="/sanpham/hienthi?page=${i-1}">${i}</a>
                        </li>
                    </c:forEach>

                </ul>
            </nav>
        </div>
        <h5 class="text-center">Danh sách sản phẩm</h5>
        <table class="table border table-bordered text-center  ms-5" style="width: 90%">
            <thead>
            <tr>
                <th scope="col">STT</th>
                <th scope="col">Hình ảnh</th>
                <th scope="col">Mã SP</th>
                <th scope="col">Tên SP</th>
                <th scope="col">Ngày tạo</th>
                <th scope="col">Ngày sửa</th>
                <th scope="col">Số lượng</th>
                <th scope="col">Danh mục</th>
                <th scope="col">Nhà sản xuất</th>
                <th scope="col">Thương hiệu</th>
                <th scope="col">Hành động</th>
            </tr>
            </thead>
            <tbody style="">
            <c:forEach var="sp" items="${hienthi.content}" varStatus="i">
                <tr>
                    <td>${i.index +1}</td>
                    <td>${sp.anhsp}</td>
                    <td>${sp.masp}</td>
                    <td>${sp.tensp}</td>
                    <td>${sp.ngaytao}</td>
                    <td>${sp.ngaysua}</td>
                    <td>${sp.soluongsp}</td>
                    <td>${sp.danhMuc.tendanhmuc}</td>
                    <td>${sp.nhaSanXuat.tennsx}</td>
                    <td>${sp.thuongHieu.tenthuonghieu}</td>
                    <td>
                            <button class="btn btn-warning"><a href="/sanpham/spct">Sản Phẩm Chi Tiết</a></button>
                            <button class="btn btn-info"><a href="/sanpham/detail?id=${sp.id}">CHI TIẾT</a></button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
</div>
</body>
</html>