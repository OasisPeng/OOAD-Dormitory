package com.example.ooad;

import com.example.ooad.bean.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OoadApplicationTests {
	@Autowired
	TeamDao teamDao;
	@Test
	void contextLoads() {
	}

	@Test
	public void a() {
		Team a = new Team();
		a.setId(1);
		a.setHeadId(123);
		teamDao.insert(a);
	}
}
