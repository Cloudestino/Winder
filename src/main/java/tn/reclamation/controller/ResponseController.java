package tn.reclamation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.security.crypto.password.PasswordEncoder;
import tn.reclamation.entities.Reclamation;
import tn.reclamation.entities.Response;
import tn.reclamation.service.ReclamationService;
import tn.reclamation.service.ResponseService;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
@RestController
@RequestMapping("/response")

public class ResponseController {
	@Autowired
	ResponseService  responseService;

	
	@GetMapping("/listResponses")
	@ResponseBody
	public List<Response> listResponses(){
		return responseService.getAllResponses();
	}
	
	
	@PostMapping("/add-response/{idReclamation}")
	@ResponseBody
	public void addResponse(@RequestBody Response r,@PathVariable("idReclamation") Long idReclamation)
	{
		  responseService.addResponse(r,idReclamation);
		  sendsms("+21621499250");
		  

	}
	public void sendsms(String str) {
		Twilio.init("ACbc4373fd2cc5375dec9807665d085c2b", "1901138ff2aefe44943750f0919ed39e");
		try {
			com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message
					.creator(new PhoneNumber(str), // To number
							new PhoneNumber("+16813219835"), // From number
							"Cher Client,Votre réclamation a été traité")
					.create();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	@DeleteMapping("/deleteResponse/{idResponse}")
	@ResponseBody
	public void deleteReclamation(@PathVariable("idResponse") Long idResponse){
		responseService.supprimerResponse(idResponse);
	}

	
	@PutMapping("/modifierResponse/{idResponse}")
	@ResponseBody
	public void modifierResponse(@RequestBody Response r,@PathVariable("idResponse") Long idResponse){
		responseService.updateResponse(r,idResponse);
	}
	
	@PutMapping("/getResponsesByReclamation/{idReclamation}")
	@ResponseBody
	public List<Response> getResponsesByReclamation(@PathVariable("idReclamation") Long idReclamation){
		 return responseService.getResponsesByReclamation(idReclamation);
	}
	

}
