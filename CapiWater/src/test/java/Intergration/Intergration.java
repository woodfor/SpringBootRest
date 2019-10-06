package Intergration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

import com.example.CapiWater.CapiWaterApplication;
import com.example.CapiWater.House;
import com.example.CapiWater.HouseCreation;
import com.example.CapiWater.State;
import com.example.CapiWater.Suburb;
import com.example.CapiWater.Tap;
import com.example.CapiWater.Token;
import com.example.CapiWater.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CapiWaterApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Intergration {
	@Autowired
	private TestRestTemplate restTemplate;
	@LocalServerPort
	private int port;
	private static long uid = 0;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void test() {

	}
	
	
	
//	  @Test 
//	  public void testGetAllUsers() {
//	  
//	  String today = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()); 
//	  String day = "2019-10-05T12:34-04:00";
//	  HttpHeaders headers = new HttpHeaders();
//	  //headers.add("Content-Type","application/json");
//	  headers.add("Accept", "application/json"); 
//	  HttpEntity<String> entity = new HttpEntity<String>(null,headers); 
//	  ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/notification/39/"+day+"/15", HttpMethod.PUT,
//	  entity, String.class); Assert.assertNotNull(response.getBody());
//	  System.out.println(response.getBody());
//	  
//	  }
	  
	  @Test 
	  public void testGet() {
	  
	  String today = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()); 
	  String day = "2019-10-05T12:34-04:00";
	  HttpHeaders headers = new HttpHeaders();
	  //headers.add("Content-Type","application/json");
	  headers.add("Accept", "application/json"); 
	  HttpEntity<String> entity = new HttpEntity<String>(null,headers); 
	  ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/notification/10", HttpMethod.GET,
	  entity, String.class); Assert.assertNotNull(response.getBody());
	  System.out.println(response.getBody());
	  
	  }
//	 
//	  @Test public void testGet() {
//		  
//		  String today = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()); 
//		  
//		  HttpHeaders headers = new HttpHeaders();
//		  //headers.add("Content-Type","application/json");
//		  headers.add("Accept", "application/json"); 
//		  HttpEntity<String> entity = new HttpEntity<String>(null,headers); 
//		  ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/tap/token/10/23", HttpMethod.PUT,
//		  entity, String.class);
//		  System.out.println(response.getBody());
//		  
//	}
	 

	
	/*
	 * @Test public void testGetAllUsers() {
	 * 
	 * HttpHeaders headers = new HttpHeaders(); headers.add("Content-Type",
	 * "application/json"); headers.add("Accept", "application/json");
	 * HttpEntity<String> entity = new HttpEntity<String>(null,headers);
	 * ResponseEntity<String> response = restTemplate.exchange(getRootUrl() +
	 * "/house/"+ "b91b642e-e390-4705-ac1e-20fa38b56cec", HttpMethod.GET, entity,
	 * String.class); Assert.assertNotNull(response.getBody());
	 * System.out.println(response.getBody());
	 * 
	 * }
	 */
	 

	
//	  @Test public void testainsert() throws RestClientException,
//	  JsonProcessingException {
//	  
////	  User user = new User("test111","test"); 
////	  State state = new State("VIC");
////	  Suburb suburb = new Suburb("sss","3145"); 
////	  House house = new House("dddsss",2); HouseCreation housecreation = new
////	  HouseCreation(user,house,state,suburb);
//	  
//	  HttpHeaders headers = new HttpHeaders(); headers.add("Content-Type",
//	  "application/json"); headers.add("Accept", "application/json");
//	  
//	  ObjectMapper objectMapper = new ObjectMapper();
//	  
//	  restTemplate.exchange(getRootUrl() + "/house", HttpMethod.POST, new
//	  HttpEntity<>
//	  ("{\"house\":{\"address\":\"\",\"hid\":0,\"name\":\"dd\",\"nop\":1},\"state\":{\"name\":\"Victoria\",\"sid\":0},\"suburb\":{\"name\":\"Darling\",\"postcode\":\"3145\",\"subid\":0},\"user\":{\"name\":\"dd\",\"uid\":0,\"uuid\":\"7dfae8b0-9222-47f6-8539-d5939d6949e2\"}}"
//	  ,headers),String. class);
//	  
//	  }
//	 
//	 
//
}
/*
 * @Test public void testbinsert() { State state = new State("vic");
 * ResponseEntity<State> postResponse = restTemplate.postForEntity(getRootUrl()
 * + "/state", state, State.class); State tmpuser = postResponse.getBody();
 * 
 * }
 * 
 * @Test public void testcinsert() { Tap test = new Tap("Malvern","3145");
 * ResponseEntity<Tap> postResponse = restTemplate.postForEntity(getRootUrl() +
 * "/suburb", test, Tap.class); Tap tmpuser = postResponse.getBody();
 * 
 * }
 * 
 * @Test public void testdinsert() { Suburb test = new Suburb("Malvern","3145");
 * ResponseEntity<Suburb> postResponse = restTemplate.postForEntity(getRootUrl()
 * + "/suburb", test, Suburb.class); Suburb tmpuser = postResponse.getBody();
 * 
 * }
 * 
 * 
 * 
 */
/*
 * @Test public void testainsert() throws RestClientException,
 * JsonProcessingException {
 * 
 * Token token = new Token("test5"); HttpHeaders headers = new HttpHeaders();
 * headers.add("Content-Type", "application/json"); headers.add("Accept",
 * "application/json"); ObjectMapper objectMapper = new ObjectMapper();
 * restTemplate.exchange(getRootUrl() + "/token", HttpMethod.PUT, new
 * HttpEntity<>(objectMapper.writeValueAsString(token),headers),String.class);
 * //HttpEntity<String> entity = new HttpEntity<String>(null, headers);
 * //ResponseEntity<String> response = restTemplate.put(getRootUrl() + "/token",
 * token,entity); //restTemplate.put(getRootUrl() + "/token", token);
 * //ResponseEntity<Token> response = restTemplate.getForEntity(getRootUrl() +
 * "/token", Token.class); //System.out.println(response.getBody().getToken());
 * //Token tmpuser = postResponse.getBody();
 * 
 * }
 */

/*
 * @Test
 * 
 * testRestTemplate.getRestTemplate().setInterceptors(
 * Collections.singletonList((request, body, execution) -> {
 * request.getHeaders() .add("header-name", "value"); return
 * execution.execute(request, body); })); List<MediaType> type = new
 * ArrayList<>(); type.add(new MediaType("application/json") ); HttpHeaders
 * headers = new HttpHeaders(); headers.setContentType(new
 * MediaType("application/json")); headers.setAccept(type); public void test() {
 * ResponseEntity<Token> response = restTemplate.getForEntity(getRootUrl() +
 * "/token/2", Token.class); System.out.println(response.getBody().getToken());
 * 
 * }
 */
