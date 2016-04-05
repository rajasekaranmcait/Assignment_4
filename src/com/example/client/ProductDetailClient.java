package com.example.client;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.example.bean.ProductDetail;
import com.example.util.ApplicationConstants;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
/* Client class do the following process. 
   1. Read the values from the JSON file
   2. Fetch the product details from the java collection. */
public class ProductDetailClient
{
    //Main method for the client.
	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		// Get the json file
		FileReader fileReader = new FileReader(ApplicationConstants.JSON_FILE_PATH);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		// read the values from the json file as a List
		List<ProductDetail> productDetails = getProductDetails(fileReader);
		// Enter a value to perform the operation
		System.out.println("Options: \n" + "1. Find users by product \n" + "2. Find product by users last name");
		// variable used to repeat the process
		String repeat = "Y";
		// if the repeat = N then terminate the process
		while (!repeat.equalsIgnoreCase("N")) 
		{
		  System.out.println("Enter the operation number to perform: ");
		  // read the value for option
		  int option = new Integer(bufferedReader.readLine()).intValue();
		  switch (option)
		  {
			case 1:
				System.out.println("Enter the product name: ");
				// read the product name from the console
				String productName = bufferedReader.readLine();
				// get details using product name
				getUsersbyProductName(productName, productDetails);
				break;

			case 2:
				System.out.println("Enter the last name: ");
				// read the last name from the console
				String lastName = bufferedReader.readLine();
				// get details using last name
				getProductbyLastName(lastName, productDetails);
				break;
			default:
				System.out.println("Please enter a valid number");
				break;
			}
			// get the repeat value to continue the process
			System.out.println("Press Enter to continue or Press N to quit");
			repeat = bufferedReader.readLine();
		 }
		System.out.println("Process Exited");
		bufferedReader.close();
		System.exit(0);
	 }
	 // Read the data from the JSON file and store it in an ArrayList 
	 private static List<ProductDetail> getProductDetails(FileReader fileReader)
	 {
		ArrayList<ProductDetail> productDetails = new ArrayList<ProductDetail>();
		//convert Json object into java object using gson
		Gson gson = new Gson();
		JsonParser jsonParser = new JsonParser();
		try 
		{
			BufferedReader reader = new BufferedReader(fileReader);
			JsonElement jsonElement = jsonParser.parse(reader);
			// Create ProductDetail type
			Type productDetailType = new TypeToken<List<ProductDetail>>() { }.getType();
			return gson.fromJson(jsonElement, productDetailType);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return productDetails;
	}
    //Search user details using product name
	private static void getUsersbyProductName(String productName, List<ProductDetail> productDetails) 
	{
		boolean isFound = false;
		System.out.println("Below is the list of users using the product: " + productName);
		for (ProductDetail productDetail : productDetails) 
		{
			if (productDetail.getProductname().equalsIgnoreCase(productName)) 
			{
				System.out.println(productDetail.getFirstname() + " " + productDetail.getLastname());
				isFound = true;
			}
		}
		// if no record found in the list
		if (!isFound)
			System.out.println("No details found for the product: " + productName);
	}
    //Search product details using user's last name
	private static void getProductbyLastName(String lastName, List<ProductDetail> productDetails) 
	{
		boolean isFound = false;
		System.out.println("Below is the list of users using the product ");
		for (ProductDetail productDetail : productDetails) 
		{
			if (productDetail.getLastname().equalsIgnoreCase(lastName)) 
			{
				System.out.println(productDetail.getFirstname() + "," + productDetail.getLastname() + " - is using "
						+ productDetail.getProductname());
				isFound = true;
			}
		}
		// if no record found in the list
		if (!isFound)
			System.out.println("No details found for the user: " + lastName);
	}
}
