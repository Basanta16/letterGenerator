package com.basanta.letter.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.basanta.letter.entity.letterDetails;
import com.basanta.letter.repo.LetterRepository;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
public class homecontroller {
	
	@Autowired
	private LetterRepository letterRepository;
	
	int id = 2;
	
	String globalEmail;
	
	@GetMapping("/")
	public ModelAndView dashboard() {
		
		ModelAndView model = new ModelAndView("index");
		model.addObject("title", "Home");
		System.out.println(id);
		return model;
	}
	
	
	@PostMapping("/saveDetails")
	public ModelAndView saveDetails (@RequestParam("receipient_name") String receipient_name,
			@RequestParam("name") String name,
			@RequestParam("from_date") String from_date,
			@RequestParam("to_date") String to_date,
			@RequestParam("team_name") String team_name,
			@RequestParam("phone") String phone,
			@RequestParam("cause") String cause,
			@RequestParam("duration") int duration,
			@RequestParam("email") String email) {
		
	ModelAndView model = new ModelAndView("redirect:/");
	
	letterDetails letter = new letterDetails();
	letter.setEmail(email);
	letter.setFrom_date(from_date);
	letter.setName(name);
	letter.setPhone(phone);
	letter.setTeam_name(team_name);
	letter.setTo_date(to_date);
	letter.setReceipient_name(receipient_name);
	letter.setCause(cause);
	letter.setDuration(duration);
	
	this.letterRepository.save(letter);
	
	System.out.println("letter_id" +letter.getLetter_id());
	
	//id = letter.getLetter_id();
	
	globalEmail = email;
	
	return model;
	}
	
	
	@PostMapping("/generate")
    public ResponseEntity<byte[]> generatePdf() throws IOException {
        
    
        letterDetails letter = this.letterRepository.getUserByUserName(globalEmail);
        
        

        // Generate KYC form using JasperReports
        try {
            // Load the JasperReport template
            String templatePath = "D:/jasper/absence_letter.jrxml";
            JasperReport jasperReport = JasperCompileManager.compileReport(templatePath);

            // Create parameters map
            Map<String, Object> parameters = new HashMap<>();
          
            System.out.println("ksfj");
              
            parameters.put("recipient", letter.getReceipient_name());
            parameters.put("name", letter.getName());
            parameters.put("email", letter.getEmail());
            parameters.put("from", letter.getFrom_date());
            parameters.put("phone", letter.getPhone());
            parameters.put("team", letter.getTeam_name());
            parameters.put("to", letter.getTo_date());
            parameters.put("du", letter.getDuration());
            parameters.put("cause", letter.getCause());
           
            
            
            
            // Add more parameters as required

            // Fetch data for the report (if needed)
            // For example, you can fetch additional data related to the customer

            // Create a JRBeanCollectionDataSource using an empty list
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singletonList(new Object()));

            // Generate the JasperPrint object
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // Export JasperPrint to PDF
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);

            // Set the response headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "absence letter.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);

        } catch (JRException e) {
            // Handle exception appropriately
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
