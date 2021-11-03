package springapp.spring.util;

import java.io.File;
import java.io.IOException;
//import org.apache.commons.io.FileUtils;

public class WriteUtil{

	public void execute(String folderPath,String fileName,String mergedString){
		try {
			String filePath=folderPath+"\\"+fileName+".txt";
			File file= new File(filePath);
			//FileUtils.writeStringToFile(file, mergedString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}