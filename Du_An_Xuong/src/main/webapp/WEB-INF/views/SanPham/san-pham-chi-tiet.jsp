<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Thông tin sản phẩm chi tiết</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.1.0-beta.1/css/select2.min.css" rel="stylesheet" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.1.0-beta.1/js/select2.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<h2 class="text-center">sản phẩm chi tiết</h2>

    <form:form action="/sanpham/spctadd" method="post" modelAttribute="SanPhamChiTiet" class="container mt-5">
        <div class="mb-3">
            <label for="ten_san_pham" class="form-label">Tên sản phẩm:</label>
            <select id="ten_san_pham" name="sanPham" class="form-select">
                <c:forEach items="${listsanpham}" var="sanpham">
                    <option value="${sanpham.id}">${sanpham.tensp}</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label for="mo_ta" class="form-label">Mô tả:</label>
            <textarea id="mo_ta" name="mo_ta" class="form-control"></textarea>
        </div>

        <div class="mb-3">
            <label for="chatLieu" class="form-label">Chất liệu:</label>
            <select id="chatLieu" name="chatLieu" class="form-select">
                <c:forEach items="${chatlieuList}" var="chatlieu">
                    <option value="${chatlieu.id}">${chatlieu.ten_chat_lieu}</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label for="status" class="form-label">Trạng thái:</label>
            <select id="status" name="status" class="form-select">
                <option value="0">Hoạt động</option>
                <option value="1">Không hoạt động</option>
            </select>
        </div>

        <div class="mb-3">
            <label for="mauSac" class="form-label">Màu sắc:</label>
            <select id="mauSac" name="mauSac" class="form-select" multiple>
                <c:forEach items="${mauSacList}" var="mauSac">
                    <option value="${mauSac.id}">${mauSac.ten_mau_sac}</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label for="kichThuoc" class="form-label">Kích thước:</label>
            <select id="kichThuoc" name="kichThuoc" class="form-select" multiple>
                <c:forEach items="${kichThuocList}" var="kichThuoc">
                    <option value="${kichThuoc.id}">${kichThuoc.ten_kich_thuoc}</option>
                </c:forEach>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Lưu</button>
    </form:form>

    <%--///vùng để tạm--%>

<div class="container mt-5"><h2>danh sách các sản phẩm thêm</h2>
    <a class="btn btn-primary" href="/sanpham/themSPCT">ADD</a>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Tên sản phẩm</th>
            <th scope="col">Màu sắc</th>
            <th scope="col">Kích thước</th>
            <th scope="col">Chất liệu</th>
            <th scope="col">Số lượng</th>
            <th scope="col">Đơn giá</th>
            <th scope="col">hình ảnh</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${sanphamdetam}" var="SPCT" varStatus="i">
            <tr>
                <th scope="col">${i.index +1}</th>
                <th scope="col">${SPCT.sanPham.tensp}</th>
                <th scope="col">${SPCT.mauSac.ten_mau_sac}</th>
                <th scope="col">${SPCT.kichThuoc.ten_kich_thuoc}</th>
                <th scope="col">${SPCT.chatLieu.ten_chat_lieu}</th>
                <th scope="col">${SPCT.so_luong_san_pham}</th>
                <th scope="col">${SPCT.don_gia}</th>
                <th scope="col">${SPCT.anh_san_pham_chi_tiet}</th>
                <th scope="col">  <a class="btn btn-info" href="/sanpham/xoaSPCTtam?id=${SPCT.id}">xóa</a></th>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
<%--///--%>

<div class="container mt-5">
    <h1>Sản Phẩm Chi Tiết</h1>
<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Tên sản phẩm</th>
        <th scope="col">Màu sắc</th>
        <th scope="col">Kích thước</th>
        <th scope="col">Chất liệu</th>
        <th scope="col">Số lượng</th>
        <th scope="col">Đơn giá</th>
        <th scope="col">hình ảnh</th>
        <th scope="col">chức năng</th>
    </tr>
    </thead>
    <tbody>
<c:forEach items="${SanPhamChiTietList.content}" var="SPCT" varStatus="i">
     <tr>
        <th scope="col">${i.index +1}</th>
        <th scope="col">${SPCT.sanPham.tensp}</th>
        <th scope="col">${SPCT.mauSac.ten_mau_sac}</th>
        <th scope="col">${SPCT.kichThuoc.ten_kich_thuoc}</th>
        <th scope="col">${SPCT.chatLieu.ten_chat_lieu}</th>
        <th scope="col">${SPCT.so_luong_san_pham}</th>
        <th scope="col">${SPCT.don_gia}</th>
        <th scope="col">${SPCT.anh_san_pham_chi_tiet}</th>
        <th scope="col"><a class="btn btn-info" href="/sanpham/xoaSPCT?id=${SPCT.id}">xóa</a>
            <a class="btn btn-info" href="/sanpham/suaSPCT?id=${SPCT.id}">sửa</a>
        </th>
     </tr>
</c:forEach>

    </tbody>
</table>
    <nav aria-label="Page navigation example" class="mt-3">
        <ul class="pagination justify-content-center">
            <li class="page-item ${SanPhamChiTietList.first ? 'disabled' : ''}">
                <c:choose>
                    <c:when test="${SanPhamChiTietList.first}">
                        <span class="page-link">Previous</span>
                    </c:when>
                    <c:otherwise>
                        <a class="page-link" href="/sanpham/formSPCT?x=${SanPhamChiTietList.number - 1}" tabindex="-1">Previous</a>
                    </c:otherwise>
                </c:choose>
            </li>
            <c:forEach begin="0" end="${SanPhamChiTietList.totalPages - 1}" var="i">
                <li class="page-item ${SanPhamChiTietList.number == i ? 'active' : ''}">
                    <c:choose>
                        <c:when test="${SanPhamChiTietList.number == i}">
                            <span class="page-link">${i + 1}</span>
                        </c:when>
                        <c:otherwise>
                            <a class="page-link" href="/sanpham/formSPCT?x=${i}">${i + 1}</a>
                        </c:otherwise>
                    </c:choose>
                </li>
            </c:forEach>
            <li class="page-item ${SanPhamChiTietList.last ? 'disabled' : ''}">
                <c:choose>
                    <c:when test="${SanPhamChiTietList.last}">
                        <span class="page-link">Next</span>
                    </c:when>
                    <c:otherwise>
                        <a class="page-link" href="/sanpham/formSPCT?x=${SanPhamChiTietList.number + 1}">Next</a>
                    </c:otherwise>
                </c:choose>
            </li>
        </ul>
    </nav>


</div>
<script type="text/javascript">
    $(document).ready(function() {
        $('#mauSac').select2();
        $('#kichThuoc').select2();
        $('#ten_san_pham').select2();
    });
</script>
</body>
</html>