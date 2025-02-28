package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException{
		String path = "C:\\Selenium_Prj_1\\WebDriverDemo\\testData\\LoginData.xlsx";
		int totalrows=ExcelUtils.getRowCount(path,"Sheet1");
		int totalcols = ExcelUtils.getCellCount(path, "Sheet1", 1);
		String logindata[][] = new String[totalrows][totalcols];
		for(int i=1;i<=totalrows;i++) {
			for(int j=0;j<totalcols;j++) {
				logindata[i-1][j]=ExcelUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		return logindata;
	}
}
