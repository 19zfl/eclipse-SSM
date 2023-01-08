package cn.itsource.test;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itsource.mapper.VipMapper;
import cn.itsource.service.VipService;

//让测试运行于Spring测试环境
@RunWith(SpringJUnit4ClassRunner.class)
//指向配置文件
@ContextConfiguration("classpath:applicationContext.xml")
public class Test01 {
	
	@Autowired
	private DataSource source;
	
	@Autowired
	private VipMapper mapper;	
	
	@Autowired
	private VipService vipService;
	
	@Test
	public void testName1() throws Exception {
		
//		System.out.println(source);
//		System.out.println(source.getConnection());
//		System.out.println(mapper.queryAll());
//		System.out.println(vipService.queryAll());
		
	}

}
