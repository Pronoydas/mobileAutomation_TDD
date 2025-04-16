package qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.OutputType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import io.appium.java_client.AppiumDriver;
import qa.base.BaseTest;
import qa.base.MenuPage;

public class Utilities {
	
	private static Properties prop;
	
	
	public static Properties loadProperites() {
		String filePath = System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\capabilities.properties";
		try {
			FileInputStream fis = new FileInputStream(new File(filePath));
			prop= new Properties();
			prop.load(fis);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
	
	public JSONObject getTestDataFromJson() {
		String filePath="testData/userLogin.json";
		InputStream is = getClass().getClassLoader()
				.getResourceAsStream(filePath);
		//System.out.println(is.toString());
		JSONTokener jt = new JSONTokener(is);
		 return new JSONObject(jt);
	}
	
	
	public HashMap<String, String> parseStringXML() throws Exception{
		InputStream file = getClass().getClassLoader().getResourceAsStream("strings/strings.xml");
		HashMap<String, String> stringMap = new HashMap<String, String>();
		//Get Document Builder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		 
		//Build Document
		Document document = builder.parse(file);
		 
		//Normalize the XML Structure; It's just too important !!
		document.getDocumentElement().normalize();
		 
		//Here comes the root node
		Element root = document.getDocumentElement();
		 
		//Get all elements
		NodeList nList = document.getElementsByTagName("string");
		 
		for (int temp = 0; temp < nList.getLength(); temp++)
		{
		 Node node = nList.item(temp);
		 if (node.getNodeType() == Node.ELEMENT_NODE)
		 {
		    Element eElement = (Element) node;
		    // Store each element key value in map
		    stringMap.put(eElement.getAttribute("name"), eElement.getTextContent());
		 }
		}
		return stringMap;
	}
	
	
	
	
}
