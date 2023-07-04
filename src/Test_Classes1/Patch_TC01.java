package Test_Classes1;

import java.io.IOException;
import java.time.LocalDateTime;
import org.testng.Assert;
import Common_API_Methods.Patch_API_Methods;
import RequestRepository.Patch_Req_Repository;
import io.restassured.path.json.JsonPath;

public class Patch_TC01 {

	public static void extractor() throws IOException {
		int statusCode = Patch_API_Methods.ResponseStatusCode(Patch_Req_Repository.BaseURI(),Patch_Req_Repository.Patch_Resource(), Patch_Req_Repository.Patch_Req_TC1());
		System.out.println(statusCode);

		String ResponseBody = Patch_API_Methods.ResponseBody(Patch_Req_Repository.BaseURI(),Patch_Req_Repository.Patch_Resource(), Patch_Req_Repository.Patch_Req_TC1());
		System.out.println(ResponseBody);

		//Expected RequestBody 
				JsonPath reqb = new JsonPath (Patch_Req_Repository.Patch_Req_TC1());
				String req_name = reqb.getString("name");
				String req_job = reqb.getString("job");
				
		         //	int req_id = reqb.getInt("id");
		        //	System.out.println("Print ID from req TC01 "+req_id);
			    
				LocalDateTime currentdate =LocalDateTime.now();
				String Expecteddate =currentdate.toString().substring(0,11);;
				
				//Parsing Response Body
				JsonPath resb = new JsonPath (ResponseBody);
				String res_name = resb.getString("name");
				String res_job = resb.getString("job");
				//System.out.println("Print from pharse response TC01"+resb);
				
				String res_date = resb.getString("updatedAt");
				res_date = res_date.substring(0,11);
				
				//Validating Response Body
				Assert.assertEquals(req_name,res_name);
				Assert.assertEquals(req_job,res_job);
				Assert.assertEquals(Expecteddate,res_date);
				
			}

		}