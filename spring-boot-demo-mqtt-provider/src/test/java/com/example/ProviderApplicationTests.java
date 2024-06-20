package com.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProviderApplicationTests {

  /**
   *
   */
  private String name;

  @Test
  public void contextLoads() {
    System.out.println("test");


    String excelFilePath = "/Users/beibei/Downloads/test111.xlsx"; // 替换为你的Excel文件路径
    FileInputStream fis = null;
    FileOutputStream fos = null;
    Workbook workbook = null;



    try {
      fis = new FileInputStream(excelFilePath);
      workbook = new XSSFWorkbook(fis);

      // 读取专家评分
      Sheet sheet = workbook.getSheetAt(0);
      Map<Integer, Integer> pointMap = new HashMap<>();
      Map<Integer, String> nameMap = new HashMap<>();
      for (Row row : sheet) {
        Cell guidCell = row.getCell(1);
        Cell pointCell = row.getCell(2);
        Cell nameCell = row.getCell(0);


        if (guidCell != null && pointCell != null) {
          if (guidCell.getCellType() == CellType.NUMERIC.getCode() && pointCell.getCellType() == CellType.NUMERIC.getCode()) {
            int guid = (int) guidCell.getNumericCellValue();
            int point = (int) pointCell.getNumericCellValue();
            String name = nameCell.getStringCellValue();
            System.out.println("第一列：" + guid);
            System.out.println("第二列：" + point);
            pointMap.put(guid, point);
            nameMap.put(guid, name);

          } else {
            System.out.println("非数值单元格在第 " + row.getRowNum() + " 行。");
//            cell3.setCellValue("非数值单元格"); // 如果有非数值单元格，记录在第三列
          }
        }
      }
      System.out.println(pointMap);
      System.out.println("===================");


      // 读取学生成绩
      sheet = workbook.getSheetAt(1);
      int count = 0;

      for (Row row : sheet) {
        Cell guidCell = row.getCell(0);
        Cell uestcPointCell = row.getCell(2);



        if (guidCell != null && uestcPointCell != null) {
          if (guidCell.getCellType() == CellType.NUMERIC.getCode() && uestcPointCell.getCellType() == CellType.NUMERIC.getCode()) {
            int guid = (int) guidCell.getNumericCellValue();
            int point = (int) uestcPointCell.getNumericCellValue();

            double finalPoint;
            if (pointMap.containsKey(guid)) {
              finalPoint = point * 0.8 + pointMap.get(guid) * 0.2;
//              finalPointCell.setCellValue(finalPoint);
              System.out.println(finalPoint + "=" + nameMap.get(guid)+"="+pointMap.get(guid));
              count+=1;

            } else {
              finalPoint = point * 0.8;
//              finalPointCell.setCellValue(finalPoint);
              System.out.println(finalPoint);

            }

          } else {
            System.out.println("非数值单元格在第 " + row.getRowNum() + " 行。");
//            finalPointCell.setCellValue("非数值单元格"); // 如果有非数值单元格，记录在第三列
          }

        }
      }


      System.out.println("get:" + count);


//      fos = new FileOutputStream(excelFilePath);
//      workbook.write(fos);


    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (workbook != null) {
          workbook.close();
        }
        if (fis != null) {
          fis.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }


  @Test
  public void check() {

    System.out.println("test");


    String excelFilePath = "/Users/beibei/Downloads/test222.xlsx"; // 替换为你的Excel文件路径
    FileInputStream fis = null;
    FileOutputStream fos = null;
    Workbook workbook = null;

    try {
      fis = new FileInputStream(excelFilePath);
      workbook = new XSSFWorkbook(fis);

      // 读取学生名字
      Sheet sheet = workbook.getSheetAt(1);
      Map<Integer, Integer> pointMap = new HashMap<>();
      for (Row row : sheet) {
        Cell guidCell = row.getCell(0);
        Cell pointCell = row.getCell(1);


        if (guidCell != null) {
          if (guidCell.getCellType() == CellType.NUMERIC.getCode()  && pointCell.getCellType() == CellType.NUMERIC.getCode() ) {
            int guid = (int) guidCell.getNumericCellValue();
            int point = (int) pointCell.getNumericCellValue();

            pointMap.put(guid, point);

          } else {
            System.out.println("非数值单元格在第 " + row.getRowNum() + " 行。");
//            cell3.setCellValue("非数值单元格"); // 如果有非数值单元格，记录在第三列
          }
        }
      }
      System.out.println(pointMap);
      System.out.println("===================");



      // 读取学生名字
      sheet = workbook.getSheetAt(0);
      for (Row row : sheet) {
        Cell guidCell = row.getCell(1);
        Cell nameCell = row.getCell(0);
        Cell pointCell = row.getCell(2);


        if (guidCell != null) {
          if (guidCell.getCellType() == CellType.NUMERIC.getCode() ) {
            int guid = (int) guidCell.getNumericCellValue();
            int point = (int) pointCell.getNumericCellValue();
            String name = nameCell.getStringCellValue();

            System.out.println(guid + "===" + point + "===" + pointMap.get(guid));
            if(point != pointMap.get(guid)){
              System.out.println("WARN!!!");
            }



          } else {
            System.out.println("非数值单元格在第 " + row.getRowNum() + " 行。");
//            cell3.setCellValue("非数值单元格"); // 如果有非数值单元格，记录在第三列
          }
        }
      }


    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (workbook != null) {
          workbook.close();
        }
        if (fis != null) {
          fis.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
