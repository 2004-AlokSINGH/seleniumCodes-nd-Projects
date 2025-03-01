package steps;

import excelfile.ExcelReader;
import io.cucumber.java.en.Given;

public class ExcelFile {
	ExcelReader reader = new ExcelReader("src/test/resources/testdata.xlsx");
	@Given("I read data from Excel sheet")
    public void readExcelData() {
        reader.readExcel("Sheet1");
    }
}
