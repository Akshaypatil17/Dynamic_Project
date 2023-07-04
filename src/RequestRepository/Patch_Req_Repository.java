package RequestRepository;

import java.io.IOException;
import java.util.ArrayList;

import Common_API_Methods.Common_Utility_Method;

public class Patch_Req_Repository {

	public static String BaseURI() {
		 String baseURI = "https://reqres.in/";
		 return baseURI;	 
	}
	public static String Patch_Resource() {
		String patch_Resource = "api/users/2";
		return patch_Resource;
	}

	
	public static String Patch_Req_TC1() throws IOException {
		
		ArrayList<String> Req_Data = Common_Utility_Method.ReadDataExcel("Patch_API","TC5");
		//System.out.println(Req_Data);
		String Req_Name = Req_Data.get(1);
		String Req_Job = Req_Data.get(2);
		String RequestBody = "{\r\n"
				+ "    \"name\": \""+Req_Name+"\",\r\n"
				+ "    \"job\": \""+Req_Job+"\"\r\n"
				+ "}";
		return RequestBody;
	}
}