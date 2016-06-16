package org.fxi.common.controller;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by xifei on 16-3-11.
 */
@Controller
@RequestMapping(value = "/test", method = RequestMethod.GET)
public class TestController extends BaseController {

	@RequestMapping("/write")
	public void findSalesAdvertisersList() {
		response.reset();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=test.xls");
		try {
			ServletOutputStream outputStream = response.getOutputStream();
//			outputStream.write("abc".getBytes("UTF-8"));
			export().write(outputStream);

//			FileOutputStream fos = new FileOutputStream("/Users/seki/Downloads/1.xls");
//			export().write(fos);
//			fos.flush();
//			fos.close();

			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	String[] excelHeader = { "学生", "学生", "学生"};
	public HSSFWorkbook export() {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("Campaign");
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		for (int i = 0; i < excelHeader.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(excelHeader[i]);
			cell.setCellStyle(style);
			sheet.autoSizeColumn(i);
		}

		return wb;
	}


}
