package com.baizhi;

import com.baizhi.entity.Student;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPoi {

    @Test
    public void TestExporPoi(){
        // 创建Excle文档
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一个工作簿  sheet 参数：工作簿的名字(sheet1,sheet2...)
        // 如果不指定工作簿的名字，默认为sheet0，sheet1命名
        Sheet sheet = workbook.createSheet("工作簿1");
        // 创建一行 参数为行下标 （从0开始）
        Row row = sheet.createRow(0);
        // 创建一列，参数为列下标 （从0开始）
        Cell cell = row.createCell(0);
        // 给单元格设置内容
        cell.setCellValue("这是第一行第一个单元格");

        try{
            // 导出Excel
            workbook.write(new FileOutputStream(new File("D://TestPoi.xls")));
            // 释放资源
            workbook.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testExportPois(){
        Student student1 = new Student("1", "张三", 18, new Date());
        Student student2 = new Student("2", "李四", 22, new Date());
        Student student3 = new Student("3", "王五", 17, new Date());
        Student student4 = new Student("4", "赵六", 15, new Date());
        Student student5 = new Student("5", "何七", 24, new Date());

        List<Student> students = Arrays.asList(student1, student2, student3, student4, student5);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("工作簿2");

        // 创建标题行
        HSSFRow row = sheet.createRow(0);
        String[] title={"ID","名字","年龄","生日"};

        // 处理单元格对象
        HSSFCell cell = null;
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);// 单元格下标
            cell.setCellValue(title[i]);    // 单元格内容

//            cell.setCellStyle(cellStyle);   // 标题行使用样式

        }
        // 处理数据行
        for (int i = 0; i < students.size(); i++) {
            // 创建数据行

            
        }

        try{
            // 导出Excel
            workbook.write(new FileOutputStream(new File("D://TestPoi.xls")));
            // 释放资源
            workbook.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
