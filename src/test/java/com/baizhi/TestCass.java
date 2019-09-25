package com.baizhi;

import com.baizhi.service.EchartsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCass {
    @Resource
    private EchartsService echartsService;
    @Test
    public void test(){
        Map<String, Object> map = echartsService.selectBySexAndMonth();
        System.out.println(map);

    }
}
