package com.sunny.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sunny.mybatisplus.entity.User;
import com.sunny.mybatisplus.mappers.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;


@SpringBootTest
@RunWith(SpringRunner.class)
public class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        System.out.println("======");
    }

    @Test
    public void testSelect(){
        List<User> list = userMapper.selectList(null);
        assertEquals(5, list.size());
        list.forEach(System.out::println);
    }

    @Test
    public void selectMaps(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("id","name","email").likeRight("name","黄");
        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void test3() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("manager_id", "avg(age) avg_age", "min(age) min_age", "max(age) max_age")
                .groupBy("manager_id").having("sum(age) < {0}", 500);
        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void selectCount() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("name", "黄");

        Integer count = userMapper.selectCount(wrapper);
        System.out.println(count);
    }

}
