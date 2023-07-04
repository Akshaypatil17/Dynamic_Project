package Test_Classes1;

import java.io.IOException;
import java.time.LocalDateTime;
import org.testng.Assert;
import Common_API_Methods.Common_Utility_Method;
import Common_API_Methods.Put_API_Methods;
import Driver_Package.Put_DriverClass;
import RequestRepository.Put_Req_Repository;
import io.restassured.path.json.JsonPath;

public class Put_TC01 {

	public static void extractor () throws IOException {
		for (int i=0; i<5; i++) {
		int StatusCode = Put_API_Methods.ResponseStatusCode(Put_Req_Repository.BaseURI(), Put_Req_Repository.Put_Resource(), Put_Req_Repository.Put_Req_TC1());
	    System.out.println(StatusCode);
	    
	    if (StatusCode== 200) {
	    	System.out.println("Status Code:" +StatusCode);
	    
	    String ResponseBody = Put_API_Methods.ResponseBody(Put_Req_Repository.BaseURI(), Put_Req_Repository.Put_Resource(), Put_Req_Repository.Put_Req_TC1());
	    System.out.println(ResponseBody);
	    
	    String RequestBody = Put_Req_Repository.Put_Req_TC1();
	    Common_Utility_Method.EvidenceFileCrator("Put_TC1", RequestBody, ResponseBody, StatusCode);
	    Put_DriverClass.validator(RequestBody,ResponseBody);
	    break;
	    }
	    else{
	    	System.out.println("Invalid Status Code:" + StatusCode);
	    }
	}
	}
	public static void validator(String RequestBody, String ResponseBody) throws IOException {

		// Expected RequestBody
		JsonPath reqb = new JsonPath(Put_Req_Repository.Put_Req_TC1());
		String req_name = reqb.getString("name");
		String req_job = reqb.getString("job");

		// int req_id = reqb.getInt("id");
		// System.out.println("Print ID from req TC01 "+req_id);

		LocalDateTime currentdate = LocalDateTime.now();
		String Expecteddate = currentdate.toString().substring(0, 11);
		

		// Parsing Response Body
		JsonPath resb = new JsonPath(ResponseBody);
		String res_name = resb.getString("name");
		String res_job = resb.getString("job");
		// System.out.println("Print from pharse response TC01"+resb);
		int res_id = resb.getInt("id");
		System.out.println("Print ID from res TC01 " + res_id);
		String res_date = resb.getString("createdAt");
		res_date = res_date.substring(0, 11);

		// Validating Response Body
		Assert.assertEquals(req_name, res_name);
		Assert.assertEquals(req_job, res_job);
		Assert.assertNotEquals(res_id, 0);
		Assert.assertEquals(Expecteddate, res_date);

	}

}