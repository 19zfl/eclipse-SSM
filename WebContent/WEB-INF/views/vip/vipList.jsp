<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 时间格式化 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>VipList</title>
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
	<div class="table-responsive">
		<table class="table table-striped table-bordered table-hover">
		<form action="/vip/list" method="post">
			<tr>
				<td colspan="4">
					姓名:<input type="text" name="name" value="${query.name}"/>
				</td>
				<td colspan="3">
					年龄:
					<select name="age">
						<option value="">全部</option>
						<option value="1" <c:if test="${query.age == 1}">selected="selected"</c:if>>18岁以下</option>
						<option value="2" <c:if test="${query.age == 2}">selected="selected"</c:if>>18-45岁</option>
						<option value="3" <c:if test="${query.age == 3}">selected="selected"</c:if>>45岁以上</option>
					</select>
				</td>
				<td align="center"><button type="submit" class="btn btn-primary">提交</button></td>
			</tr>
			</form>
			<tr>
				<th>编号</th>
				<th>姓名</th>
				<th>年龄</th>
				<th>性别</th>
				<th>薪资</th>
				<th>生日</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${list}" var="vip" varStatus="sta">
				<tr align="center">
					<td>${sta.count}</td>
					<td>${vip.name}</td>
					<td>${vip.age}</td>
					<td>${vip.sex?'男':'女'}</td>
					<td>${vip.salary}</td>
					<td><fmt:formatDate value="${vip.birthDay}"
							pattern="yyyy-MM-dd" /></td>
					<td><fmt:formatDate value="${vip.createDate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>
						<button class="btn btn-warning"><a href="/vip/update?id=${vip.id}"><span style="color: white">修改</span></a></button>
						<button class="btn btn-danger"><a href="/vip/del?id=${vip.id}"><span style="color: white">删除</span></a></button>
					</td>
				</tr>
			</c:forEach>
			<tr align="center">
				<td colspan="7"></td>
				<td><button class="btn btn-success"><a href="/vip/skip"><span style="color: white">添加</span></a></button></td>
			</tr>
		</table>
	</div>
</body>
<script src="../../js/jquery.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
</html>