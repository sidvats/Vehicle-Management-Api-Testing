package ExcelReader;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
 
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
 
public class Reader
{
	File f;
	FileInputStream fis;
	XSSFWorkbook wb;
	XSSFSheet s1;
	public Reader() throws IOException{}
	public JSONObject getData() throws IOException
	{
		f = new File("C:\\Users\\SIDKISHO\\Desktop\\project\\TestingVehicleManagement 1\\TestingProj\\src\\test\\resources\\excel\\userRegister.xlsx");
		fis = new FileInputStream(f);
		wb = new XSSFWorkbook(fis); // .xlsx
		s1 = wb.getSheetAt(0);
		int r = s1.getPhysicalNumberOfRows();
		int c = s1.getRow(0).getPhysicalNumberOfCells();
		JSONObject obj = new JSONObject();
//		Object[][] data = new Object[r][c];
 
		for(int c1 = 0; c1<c; c1++)
		{
			String key="";
//			Map<String, String> map = new HashMap<String, String>();
			for(int r1=0; r1<r; r1++)
			{
				if(r1==0) {
					key = s1.getRow(0).getCell(c1).toString();
					continue;
				}
//				map.put(key, s1.getRow(r1).getCell(c1).toString());
				obj.put(key, s1.getRow(r1).getCell(c1).toString());
			}
		}
		return obj;		
	}
}