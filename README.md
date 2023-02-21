# eclipse整合SSM框架
- Spring
- SpringMVC
- MyBatis

#### 创建一个动态的web项目

- 在WEB-INF里面新建一个classes，将项目的字节码文件储存地址改为此文件夹

- 创建一个resource资源文件夹

- 将tomcat配置文件server.xml里面的上下文路径地址修改为此项目webContent文件夹路径地址
- 将项目所需要的jar包导入到lib文件夹

#### 添加jdbc的配置文件jdbc.properties

```pro
// 前面加上jdbc是为了不被windows系统变量影响
// 还一种方法就是在标签内加上system-properties-mode="NEVER" 让系统变量失效,不推荐
jdbc.driverClassName=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/test
jdbc.username=root
jdbc.password=root
```

#### 添加Spring核心配置文件applicationContext.xml

- 头部约束
- 跟标签

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
		http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context 
		http://www.springframework.org/schema/context/spring-context-4.0.xsd">
    
</beans>
```

#### 1.添加包扫描器

```xml
<context:component-scan base-package="cn.itsource"></context:component-scan>
```

#### 2.加载jdbc.properties资源文件

```xml
<context:property-placeholder location="classpath:jdbc.properties"/>
```

#### 3.Spring配置连接池,数据源dataSource

```xml
<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource">
    <!-- 配置四大精钢 -->
    <property name="driverClassName" value="${jdbc.driverClassName}"></property>
		<property name="url" value="${jdbc.url}"></property>
		<property name="username" value="${jdbc.username}"></property>
		<property name="password" value="${jdbc.password}"></property>
</bean>
```

测试是否成功连接数据源

```java
package cn.itsource.test;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//让测试运行于Spring测试环境
@RunWith(SpringJUnit4ClassRunner.class)
//指向配置文件
@ContextConfiguration("classpath:applicationContext.xml")
public class Test01 {
	
	@Autowired
	private DataSource source;
	
	@Test
	public void testName1() throws Exception {
		
		System.out.println(source);
		System.out.println(source.getConnection());
		// 当console能输出地址值就表示链接成功,只要不是为null
        
	}

}

```

创建实体类包名:cn.itsource.domain并创建实体类进行常规封装

创建接口类,上级包名为mapper

在resource资源文件夹中创建与mapper接口同路径的包名

例如我mapper接口在cn.itsource.mapper,那么在resource文件夹中也创建同样的文件夹

#### 4.新建接口xml配置文件:实体类名+Mapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="cn.itsource.mapper.VipMapper">
	...
</mapper>
```

#### 5.在Spring核心配置文件中配置管理mybatis核心对象SQLSessionFactory

```xml
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
			<!-- 注入数据源 -->
			<property name="dataSource" ref="dataSource"></property>
			<!-- 配置mapper接口映射文件 -->
			<property name="mapperLocations" value="classpath:cn/itsource/mapper/*.xml"></property>
			<!-- 设置包别名 -->
			<property name="typeAliasesPackage" value="cn.itsource.domain"></property>
		</bean>
```

#### 6.配置mapper接口层的扫描器

```xml
<bean id="mapperScannerConfigurer" class="org.mybatis.spring.mapper.MapperScannerConfigurer">
			<property name="basePackage" value="cn.itsource.mapper"></property>
</bean>
```

#### 7.在接口配置文件添加接口相关的sql语句标签

````xml
<mapper>
    <!-- 查询所有或者查询一个都需要有一个返回值类型属性需要添加 -->
    <!-- 每种SQL语句标签内部都有id属性,id要与接口中的方法名一模一样 -->
	<select id="quertAll" resultType="vip">
		select * from t_vip
	</select>
    ...
</mapper>
````

测试mapper层是否自动实现接口实现类且是否能得到数据

```java
package cn.itsource.test;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itsource.mapper.VipMapper;

//让测试运行于Spring测试环境
@RunWith(SpringJUnit4ClassRunner.class)
//指向配置文件
@ContextConfiguration("classpath:applicationContext.xml")
public class Test01 {
	
	@Autowired
	private VipMapper mapper;	
	
	@Test
	public void testName1() throws Exception {
		
		System.out.println(mapper.quertAll());
		
	}

}
```

#### 创建service层,创建实现类,实现类写上Spring注解<mark>@Service</mark>

#### 创建controller层,类写上Spring注解<mark>@Controller</mark>

#### 8.添加SpringMVC前端控制器的配置文件: applicationContext-mvc.xml

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:mvc="http://www.springframework.org/schema/mvc"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:aop="http://www.springframework.org/schema/aop" xmlns:tx="http://www.springframework.org/schema/tx"
	xsi:schemaLocation="http://www.springframework.org/schema/beans 
		http://www.springframework.org/schema/beans/spring-beans-3.2.xsd 
		http://www.springframework.org/schema/mvc 
		http://www.springframework.org/schema/mvc/spring-mvc-3.2.xsd 
		http://www.springframework.org/schema/context 
		http://www.springframework.org/schema/context/spring-context-3.2.xsd 
		http://www.springframework.org/schema/aop 
		http://www.springframework.org/schema/aop/spring-aop-3.2.xsd 
		http://www.springframework.org/schema/tx 
		http://www.springframework.org/schema/tx/spring-tx-3.2.xsd ">
    
    <!-- 扫描controller层 -->
	<context:component-scan base-package="cn.itsource.controller" />
    
    <!-- 静态资源放行，注解就失效了 -->
	<mvc:default-servlet-handler />
    
    <!-- 开启注解支持 -->
	<mvc:annotation-driven />
    
    <!-- 视图解析器 -->
    <!-- 前缀后缀看情况写 -->
	<bean id="internalResourceViewResolver"
		class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<!-- 设置前缀 -->
		<property name="prefix" value="/WEB-INF/views/" />
		<!-- 设置后缀 -->
		<property name="suffix" value=".jsp" />
	</bean>
    
</beans>
```

#### 9.配置web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns="http://java.sun.com/xml/ns/javaee"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
	id="WebApp_ID" version="3.0">

	<!-- 通过tomcat监听spring启动 -->
	<listener>
		<!-- spring提供的一个监听类给tomcat使用 -->
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>

	<!-- 全局参数 -->
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>classpath:applicationContext.xml</param-value>
	</context-param>

	<!--StrpngMVC核心控制器 -->
	<servlet>
		<servlet-name>SpringMvc</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>classpath:applicationContext-mvc.xml</param-value>
		</init-param>
	</servlet>
	<servlet-mapping>
		<servlet-name>SpringMvc</servlet-name>
		<url-pattern>/</url-pattern>
	</servlet-mapping>

	<!-- post提交中文乱码过滤器 -->
	<filter>
		<filter-name>CharacterEncodingFilter</filter-name>
		<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
		<init-param>
			<param-name>encoding</param-name>
			<param-value>UTF-8</param-value>
		</init-param>
	</filter>
	<filter-mapping>
		<filter-name>CharacterEncodingFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>

</web-app>
```

#### 10.前端页面配置

```jsp
<!-- 循环遍历后台传递过来的数据所需要的标签 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
```

```jsp
<c:forEach items="${list}" var="vip" varStatus="sta">
	<tr align="center">
		<td>${sta.count}</td>
		<td>${vip.name}</td>
		<td>${vip.age}</td>
		<td>${vip.sex?'男':'女'}</td>
		<td>${vip.salary}</td>
		<td>
			<fmt:formatDate value="${vip.birthDay}" pattern="yyyy-MM-dd"/>
		</td>
		<td>
			<fmt:formatDate value="${vip.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
		</td>
		<td>
			<a href="">修改</a>/
			<a href="">删除</a>
		</td>
	</tr>
</c:forEach>
```

```jsp
<!-- 时间格式化 -->
<!-- 后台向前台传递时间格式化用json,这里没有用到json -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
```

```jsp
<fmt:formatDate value="${}" pattern="yyyy-MM-dd"/>
```

#### 关于动态SQL

```xml
<!-- 利用where标签 -->
<!-- where标签:把第一个匹配到的and变成where ,后面的还是and -->
<!-- 在where标签中利用if标签内test属性进行条件判断 -->
<!-- if标签内写上sql条件语句,开头都要用and开头,当条件满足的时候,and将自动消失 -->
<!-- 当第一if满足且后面也有满足时,and将自动不消失 -->
```

