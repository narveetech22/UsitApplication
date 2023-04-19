package com.narvee.usit.helper;

import com.narvee.usit.entity.City;
import com.narvee.usit.entity.PinCode;
import com.narvee.usit.entity.Recruiter;
import com.narvee.usit.entity.States;
import com.narvee.usit.entity.VendorDetails;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.util.NumberToTextConverter;

public class Helper {

	// check that file is of excel type or not
	public static boolean checkExcelFormat(MultipartFile file) {

		String contentType = file.getContentType();

		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		} else {
			return false;
		}

	}

	// convert excel to list of products

	/*
	 * public static List<Product> convertExcelToListOfProduct(InputStream is) {
	 * List<Product> list = new ArrayList<>();
	 * 
	 * try { XSSFWorkbook workbook = new XSSFWorkbook(is);
	 * 
	 * XSSFSheet sheet = workbook.getSheet("data");
	 * 
	 * int rowNumber = 0; Iterator<Row> iterator = sheet.iterator();
	 * 
	 * while (iterator.hasNext()) { Row row = iterator.next();
	 * 
	 * if (rowNumber == 0) { rowNumber++; continue; }
	 * 
	 * Iterator<Cell> cells = row.iterator();
	 * 
	 * int cid = 0;
	 * 
	 * Product p = new Product();
	 * 
	 * while (cells.hasNext()) { Cell cell = cells.next();
	 * 
	 * switch (cid) { case 0: p.setProductName(cell.getStringCellValue()); break;
	 * case 1: p.setProductDesc(cell.getStringCellValue()); break; case 2:
	 * p.setProductPrice(cell.getNumericCellValue()); break; default: break; }
	 * cid++; } if (!list.contains(p)) { list.add(p);
	 * 
	 * } System.out.println(" ==== " + list); } } catch (Exception e) {
	 * e.printStackTrace(); } return list;
	 * 
	 * }
	 */
	/*
	 * public static List<VendorDetails> convertExcelToListOfVendor(InputStream is)
	 * { List<VendorDetails> list = new ArrayList<>();
	 * 
	 * try { XSSFWorkbook workbook = new XSSFWorkbook(is);
	 * 
	 * XSSFSheet sheet = workbook.getSheet("data");
	 * 
	 * int rowNumber = 0; Iterator<Row> iterator = sheet.iterator();
	 * 
	 * while (iterator.hasNext()) { Row row = iterator.next();
	 * 
	 * if (rowNumber == 0) { rowNumber++; continue; }
	 * 
	 * Iterator<Cell> cells = row.iterator();
	 * 
	 * int cid = 0;
	 * 
	 * VendorDetails entity = new VendorDetails();
	 * 
	 * while (cells.hasNext()) { Cell cell = cells.next();
	 * 
	 * switch (cid) { case 0: entity.setCompany(cell.getStringCellValue()); break;
	 * case 1: entity.setLocation1(cell.getStringCellValue()); break; case 2:
	 * entity.setLocation2(cell.getStringCellValue()); break; case 3:
	 * entity.setCity(cell.getStringCellValue()); break;
	 * 
	 * case 4: entity.setState(cell.getStringCellValue()); break;
	 * 
	 * case 5: entity.setCountry(cell.getStringCellValue()); break;
	 * 
	 * case 6: entity.setZipcode(cell.getStringCellValue()); break;
	 * 
	 * case 7: entity.setTyretype(cell.getStringCellValue()); break;
	 * 
	 * case 8: entity.setVendortype(cell.getStringCellValue()); break;
	 * 
	 * case 9: entity.setFedid(cell.getStringCellValue()); break;
	 * 
	 * case 10: entity.setEmployeecount(cell.getStringCellValue()); break;
	 * 
	 * case 11: entity.setRevenue(cell.getStringCellValue()); break;
	 * 
	 * case 12: entity.setPhonenumber(cell.getStringCellValue()); break;
	 * 
	 * case 13: entity.setWebsite(cell.getStringCellValue()); break;
	 * 
	 * case 14: entity.setIndustrytype(cell.getStringCellValue()); break;
	 * 
	 * case 15: entity.setLinkedinid(cell.getStringCellValue()); break;
	 * 
	 * case 16: entity.setTwitterid(cell.getStringCellValue()); break;
	 * 
	 * case 17: entity.setFacebook(cell.getStringCellValue()); break;
	 * 
	 * default: break; } cid++; } if(!list.contains(entity)) { list.add(entity);
	 * 
	 * } } } catch (Exception e) { e.printStackTrace(); } return list;
	 * 
	 * }
	 */

	// convert excel to list of products
	public static List<VendorDetails> convertExcelToListOfVendor(InputStream is) throws IOException {
		List<VendorDetails> list = new ArrayList<>();
		XSSFWorkbook workbook = new XSSFWorkbook(is);
		System.out.println(workbook);
		XSSFSheet sheet = workbook.getSheet("data");
		// XSSFSheet sheet = workbook.getSheet("Report");
		System.out.println("kirann " + sheet);
		String data = "";
		int rowNumber = 0;
		Iterator<Row> iterator = sheet.iterator();
		iterator.next();
		City c = null;
		States s = null;
		PinCode p = null;
		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			VendorDetails entity = new VendorDetails();
			c = new City();
			s = new States();
			p = new PinCode();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				int columnIndex = cell.getColumnIndex();
				switch (columnIndex) {
				case 0:
					entity.setCompany(cell.getStringCellValue());
					break;
				case 1:
					entity.setLocation1(cell.getStringCellValue());
					break;
				case 2:
					entity.setLocation2(cell.getStringCellValue());
					break;
				case 3:
					try {
						c.setCityname(cell.getStringCellValue());
						entity.setCity(c);
					} catch (NullPointerException e) {
						// TODO: handle exception
					}
					// entity.setCity(cell.getStringCellValue());
					break;

				case 4:
					try {
						s.setStatename(cell.getStringCellValue());
						entity.setStates(s);
					} catch (NullPointerException e) {
						// TODO: handle exception
					}
					break;

				case 5:
					entity.setCountry(cell.getStringCellValue());
					break;

				case 6:
//					if(cell.getCellType()==CellType.STRING) {
//					    data = cell.getStringCellValue(); 
//					}
//					
//					else if(cell.getCellType()==CellType.NUMERIC) {
//					    data = String.valueOf(cell.getNumericCellValue());
//					}
					// System.out.println(cell.getStringCellValue() + " =============");
					// if(cell.getStringCellValue()!=null) {
					try {
//						if(cell.getCellType()==CellType.STRING) {
//						    data = cell.getStringCellValue(); 
//						}
//						
//						else if(cell.getCellType()==CellType.NUMERIC) {
//						    data = String.valueOf(cell.getNumericCellValue());
//						}
						data = NumberToTextConverter.toText(cell.getNumericCellValue());
						System.out.println(data + " kirannnnnn ");
						p.setPincode(data);
						entity.setPincode(p);
					}
//					catch (  IllegalStateException e) {
//						// TODO: handle exception
//					}
					catch (NullPointerException e) {
						// TODO: handle exception
					}
					// }
					break;

				case 7:
					entity.setTyretype(cell.getStringCellValue());
					break;

				case 8:
					entity.setVendortype(cell.getStringCellValue());
					break;

				case 9:
					entity.setFedid(cell.getStringCellValue());
					break;

				case 10:
					entity.setEmployeecount(cell.getStringCellValue());
					break;

				case 11:
					entity.setRevenue(cell.getStringCellValue());
					break;

				case 12:
					entity.setPhonenumber(cell.getStringCellValue());
					break;

				case 13:
					entity.setWebsite(cell.getStringCellValue());
					break;

				case 14:
					entity.setIndustrytype(cell.getStringCellValue());
					break;

				case 15:
					entity.setLinkedinid(cell.getStringCellValue());
					break;

				case 16:
					entity.setTwitterid(cell.getStringCellValue());
					break;

				case 17:
					entity.setFacebook(cell.getStringCellValue());
					break;

				}
			}
			if (!list.contains(entity)) {
				list.add(entity);
			}
		}

		workbook.close();

		return list;

	}

	// for recruiting
	// convert excel to list of products
	public static List<Recruiter> convertExcelToListOfRecruiter(InputStream is) throws IOException {
		List<Recruiter> list = new ArrayList<>();
		XSSFWorkbook workbook = new XSSFWorkbook(is);
		// System.out.println(workbook);
		XSSFSheet sheet = workbook.getSheet("data");
		// XSSFSheet sheet = workbook.getSheet("Report");
		System.out.println("kirann " + sheet);
		String data = "";
		int rowNumber = 0;
		Iterator<Row> iterator = sheet.iterator();
		iterator.next();
		City c = null;
		States s = null;
		VendorDetails p = null;
		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			Recruiter entity = new Recruiter();
			p = new VendorDetails();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				int columnIndex = cell.getColumnIndex();
				switch (columnIndex) {
				case 0:
					try {
						p.setCompany(cell.getStringCellValue());
						entity.setVendor(p);
					} catch (NullPointerException e) {
					}
					break;
				case 1:
					entity.setRecruiter(cell.getStringCellValue());
					break;
				case 2:
					entity.setEmail(cell.getStringCellValue());
					break;
				case 3:
					try {
						data = NumberToTextConverter.toText(cell.getNumericCellValue());
						entity.setUsnumber(data);
					} catch (NullPointerException e) {
					}

					break;

				case 4:
					try {
						data = NumberToTextConverter.toText(cell.getNumericCellValue());
						entity.setInnumber(data);
					} catch (NullPointerException e) {
					}

					break;

				case 5:
					entity.setCountry(cell.getStringCellValue());
					break;

				case 6:
					entity.setState(cell.getStringCellValue());
					break;

				case 7:
					entity.setIplogin(cell.getStringCellValue());
					break;

				case 8:
					entity.setFedid(cell.getStringCellValue());
					break;

				case 9:
					entity.setDesignation(cell.getStringCellValue());
					break;

				case 10:
					entity.setLinkedin(cell.getStringCellValue());
					break;
				}
			}
			if (!list.contains(entity)) {
				list.add(entity);
			}
		}

		workbook.close();

		return list;

	}

}
