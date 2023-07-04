package Test_Classes1;

import Common_API_Methods.Get_API_Methods;
import RequestRepository.Get_Req_Repository;

public class Get_TC01 {

	public static void extractor () {
		int statusCode = Get_API_Methods.ResponseStatusCode(Get_Req_Repository.BaseURI(),Get_Req_Repository.Get_Resource());
		System.out.println(statusCode);
		
		String ResponseBody = Get_API_Methods.ResponseBody(Get_Req_Repository.BaseURI(),Get_Req_Repository.Get_Resource());
	    System.out.println(ResponseBody);
}
}
