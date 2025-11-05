package com.itheima.springbootmybatisquickstart;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@MapperScan("com.itheima.mapper")
class SpringbootMybatisQuickstartApplicationTests {
	@Autowired
	private UserMapper userMapper;

	@Test
	public void testFindAll() {
		List<User> userList = userMapper.findAll();
		userList.forEach(System.out::println);
	}

	@Test
	public void testDeleteById() {
		Integer i = userMapper.deleteById(5);
		System.out.println("执行完毕影响的记录数："+i);
	}

	@Test
	public void testInsert() {
		User user = new User(null,"linsonghui","666888","林松辉",18);
		userMapper.insert(user);
	}

	@Test
	public void testUpdate() {
		User user = new User(6,"superhui","666888","超级辉",18);
		userMapper.updateById(user);
	}


	@Test
	public void testFindByUserNameAndPassword(){
		User user = userMapper.findByUserNameAndPassword("superhui","666888");
		System.out.println(user);
	}
}
