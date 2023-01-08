<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 时间格式化 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="../../css/bootstrap.min.css">
<style type="text/css">
	body {
		font-family: "更纱黑体 SC";
		font-size: 19px;
		font-weight: bold;
	}
</style>
</head>
<body>
	<div class="container">
		<table class="table table-striped table-bordered table-hover">
			<form action="/vip/saveAndUpdate" method="post" class="form-signin">
			<input type="hidden" name="id" value="${target.id}">
			<tr>
				<td colspan="2">
					姓名:
					<input type="text" name="name" value="${target.name}" class="form-control"><br/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					年龄:
					<input type="text" name="age" value="${target.age}" class="form-control"><br/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					性别:
					<c:if test="${target.sex}">
						<input type="radio" name="sex" value="true" checked="checked">男
						<input type="radio" name="sex" value="false">女
					</c:if>
					<c:if test="${!target.sex}">
						<input type="radio" name="sex" value="true">男
						<input type="radio" name="sex" value="false" checked="checked">女
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					工资:<input type="text" name="salary" value="${target.salary}" class="form-control">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					生日:
					<input type="date" name="birthDay" value='<fmt:formatDate value="${target.birthDay}" pattern="yyyy-MM-dd" />'>
				</td>
			</tr>
			<tr align="center">
				<td><button type="submit" class="btn btn-primary">提交</button></td>
				<td><button type="reset" class="btn">重置</button></td>
			</tr>
			
		</form>
		</table>
	</div>
</body>
<script src="../../js/jquery.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
</html>