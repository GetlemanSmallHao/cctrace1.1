package com.cctrace.testrevss;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cctrace.utils.OfflineOrderUtil;

@RunWith(SpringJUnit4ClassRunner.class)//表示整合JUnit4进行测试
@ContextConfiguration(locations={"classpath:config/beans.xml"})//加载spring配置文件
public class TestContextferh {
	
	@Autowired
	public OfflineOrderUtil ff;
	
	@Test
	public void StTsest(){
		String ferf = OfflineOrderUtil.getCommandStringByDevId("863725345345039317035");
		System.out.println("*************************"+ferf);
	}
}