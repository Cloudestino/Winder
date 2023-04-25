package com.example.pidev.Controller;

import com.example.pidev.Service.ReclamationService;
import com.example.pidev.entity.Reclamation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reclamation")
public class ReclamationController {
@Autowired
ReclamationService reclamationService;


@PostMapping("/add-reclamation")
@ResponseBody
public void addReclamation(@RequestBody Reclamation r)
{
	  reclamationService.ajouterReclamation(r);

}


@GetMapping("/listReclamations")
@ResponseBody
public List<Reclamation> listReclamations(){
	return reclamationService.ListReclamations();
}

@DeleteMapping("/deleteReclamation/{idReclamation}")
@ResponseBody
public void deleteReclamation(@PathVariable("idReclamation") Long idReclamation){
	 reclamationService.supprimerReclamation(idReclamation);
}
@PutMapping("/modifierReclamation/{idReclamation}")
@ResponseBody
public void modifierReclamation(@RequestBody Reclamation r,@PathVariable("idReclamation") Long idReclamation){
	 reclamationService.updateReclamation(r,idReclamation);
}

@GetMapping("/getReclamation/{idReclamation}")
@ResponseBody
public Reclamation getReclamationByiD(@PathVariable("idReclamation") Long idReclamation){
	return reclamationService.getReclamationById(idReclamation);
}

@GetMapping("/getReclamationByDate/{datedate}")
@ResponseBody
public List<Reclamation> listerReclamationbydate(@PathVariable("datedate") Date datedate){
	return reclamationService.listerReclamationParDateDonnéé(datedate);
}
@GetMapping("/ReclamationAujourdhui")
@ResponseBody
public List<Reclamation> ReclamationAujourdhui(){
	return reclamationService.reclamationAujourdhui();
}


@GetMapping("/nombresReclamationAujourdhui")
@ResponseBody
public int nombresReclamationAujourdhui(){
	return reclamationService.nbrReclamationAujourdhui();
}
/*	@GetMapping("/{id}/responseTime")
	public long getResponseTime(@PathVariable Long id) {
		return reclamationService.getResponseTime(id);
	}*/


}
