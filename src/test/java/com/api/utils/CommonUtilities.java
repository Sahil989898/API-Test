package com.api.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.naming.ConfigurationException;

import com.api.constants.SourcePath;

public class CommonUtilities {


		public FileInputStream stream=null;

		public Properties loadFile(String filename) {
			Properties propertyFile=new Properties();
			String PropertyFilePath=null;
			switch(filename) {
			case "applicationProperties":
			
				PropertyFilePath = SourcePath.APPLICATION_PROPERTIES_PATH;
				break;
			case "applicationDataField":
		PropertyFilePath = SourcePath.APPLICATION_PROPERTIES_PATH;
		break;
			}try {
				stream=new FileInputStream(PropertyFilePath);
				propertyFile.load(stream);
				}catch (IOException e) {
					e.printStackTrace();
				}
			return propertyFile;
			
		}
			
				public String getApplicationProperty(String Key, Properties propertyFile) {
				
				String value = propertyFile.getProperty(Key);
				System.out.println("Property we got from file is::"+ value);
				try {
						stream.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				return value;
			}
	}
