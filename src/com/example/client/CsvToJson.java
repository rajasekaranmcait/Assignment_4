package com.example.client;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.example.bean.ProductDetail;
import com.example.util.ApplicationConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
// Convert CSV file to JSON file
public class CsvToJson
{
	public static void main(String[] args) throws IOException
	{
		// Get the CSV file
		FileReader csvFile = new FileReader(ApplicationConstants.CSV_FILE_PATH);
		// Convert CSV file to Json file
		convertCsvToJson(csvFile);
	}
	public static void convertCsvToJson(FileReader csvFile) throws IOException 
	{
		List<ProductDetail> productDetails = new ArrayList<ProductDetail>();
		BufferedWriter bufferedWriter = null;
		try 
		{
			// read data from CSV
			productDetails = readDataFromCSV(csvFile, productDetails);
			// add pretty printing style to json format
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String jsonString = gson.toJson(productDetails);
			// Writing to a file
			File file = new File(ApplicationConstants.JSON_FILE_PATH);
			if (!file.exists()) 
				file.createNewFile();
			FileWriter fileWriter = new FileWriter(file);
			System.out.println("Writing JSON object to file");
			System.out.println(jsonString);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(jsonString);
		} 
		catch (Exception ex) 
		{
			System.out.println(ex);
		}
		finally 
		{
			bufferedWriter.close();
		}
	}
    //Read data from CSV as a List
	private static List<ProductDetail> readDataFromCSV(FileReader csvFile, List<ProductDetail> productDetails)
			throws IOException 
	{
		BufferedReader bufferedReader = null;
		ProductDetail productDetail = null;
		String line = "";
		int row = 0;
		try 
		{
			// Get the csv file
			bufferedReader = new BufferedReader(csvFile);
			while ((line = bufferedReader.readLine()) != null) 
			{
			   String[] values = line.split(ApplicationConstants.CSV_SEPARATOR);
			   if (values.length > 0) 
			   {
					if (row > 0)
                    {
						// set the values to ProductDetail
						productDetail = new ProductDetail();
						productDetail.setId(new Long(values[0]).longValue());
						productDetail.setFirstname(values[1]);
						productDetail.setLastname(values[2]);
						productDetail.setProductname(values[3]);
						productDetail.setPrice(new Double(values[4]).doubleValue());
						productDetail.setQuantity(new Integer(values[5]).intValue());
						productDetail.setTotal(new Double(values[6]).doubleValue());
						productDetails.add(productDetail);
					}
				}
				row++;
			}
		} 
		catch (Exception ex)
		{
			System.out.println(ex);
		} 
		finally 
		{
			if (bufferedReader != null)
				bufferedReader.close();
		}
		return productDetails;
	}
}
