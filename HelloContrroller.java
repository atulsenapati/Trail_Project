package com.example.demo;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloContrroller {
	@GetMapping("/hello")
	public String hello() {
		return "Hello world";
	}
	
/**	Bajaj
    Health Insurance - 4000/yr
    Car Insurance - 40000/yr
    Bike Insurance - 2000/Yr

Acko
    Car Insurance - 45000/yr
    Health Insurance - 5000/yr
    Phone Accidental Damage Insurance - 400/yr
    
Reliance
    Car Insurance - 50000/yr
    Term Insurance - 100000/yr
    Phone Accidental Damage Insurance - 300/yr  
	
	
	
	Request : Don't use DTO, POJO or Entity for request Body(No new classes)

	Bajaj
	    Health Insurance - 4000/yr
	    Car Insurance - 40000/yr
	    Bike Insurance - 2000/Yr

	Acko
	    Car Insurance - 45000/yr
	    Health Insurance - 5000/yr
	    Phone Accidental Damage Insurance - 400/yr
	    
	Reliance
	    Car Insurance - 50000/yr
	    Term Insurance - 100000/yr
	    Phone Accidental Damage Insurance - 300/yr
	    
	    {
  "companies": [
    {
      "company": "Bajaj",
      "plans": [
        {"type": "Health Insurance", "amount": 4000},
        {"type": "Car Insurance", "amount": 40000},
        {"type": "Bike Insurance", "amount": 2000}
      ]
    },
    {
      "company": "Acko",
      "plans": [
        {"type": "Car Insurance", "amount": 45000},
        {"type": "Health Insurance", "amount": 5000},
        {"type": "Phone Accidental Damage Insurance", "amount": 400}
      ]
    },
    {
      "company": "Reliance",
      "plans": [
        {"type": "Car Insurance", "amount": 50000},
        {"type": "Term Insurance", "amount": 100000},
        {"type": "Phone Accidental Damage Insurance", "amount": 300}
      ]
    }
  ]
}
	    

	Response: Sorted by Insurance type Ascending and Amount Descending

	Bike Insurance
	    Bajaj - 2000/yr

	Car Insurance
	    Reliance - 50000/yr
	    Acko - 45000/yr
	    Bajaj - 40000/yr
	    
	Health Insurance
	    Acko - 5000/yr
	    Bajaj - 4000/yr
	    
	Phone Accidental Damage Insurance
	    Acko - 400/yr
	    Reliance - 300/yr
	    
	Term Insurance
	    Reliance - 100000/yr ****/
	
/*
 * "insuranceProviders": [ { "companyName": "Bajaj", "Health Insurance": 4000,
 * "Car Insurance": 40000, "Bike Insurance": 2000 }, { "companyName": "Acko",
 * "Car Insurance": 45000, "Health Insurance": 5000,
 * "Phone Accidental Damage Insurance": 400 }, { "companyName": "Reliance",
 * "Car Insurance": 50000, "Term Insurance": 100000,
 * "Phone Accidental Damage Insurance": 300 } ] }
 */

	
	@PostMapping("/listofCompanyInsurance")
	public String ListOfInsuranceInfo(@RequestBody List<InsuranceRequest> listofinsurance) {
		System.out.println("listofinsurance ===="+listofinsurance);
		
		return "success";
	}
	
	@GetMapping("/getlistofInsuranceBysort")
	public String ListOfInsuranceInfo() {
		
		Map<String, Map<String, Integer>> companies = new HashMap<>();
		Map<String, Integer> bajaj = new HashMap<>();
		bajaj.put("Health Insurance", 4000);
		bajaj.put("Car Insurance", 40000);
		bajaj.put("Bike Insurance", 2000);

		Map<String, Integer> acko = new HashMap<>();
		acko.put("Car Insurance", 45000);
		acko.put("Health Insurance", 5000);
		acko.put("Phone Accidental Damage Insurance", 400);

		Map<String, Integer> reliance = new HashMap<>();
		reliance.put("Car Insurance", 50000);
		reliance.put("Term Insurance", 100000);
		reliance.put("Phone Accidental Damage Insurance", 300);

		companies.put("Bajaj", bajaj);
		companies.put("Acko", acko);
		companies.put("Reliance", reliance);
		
		companies.forEach((company, plans) -> {
		    System.out.println(company);

		    plans.forEach((type, amount) ->
		        System.out.println("   " + type + " - " + amount + "/yr")
		    );

		    System.out.println();
		});
		
		List<Map.Entry<String, Integer>> sortedList =
			    companies.entrySet().stream()
			        .flatMap(company ->
			            company.getValue().entrySet().stream()
			                .map(plan -> new AbstractMap.SimpleEntry<>(
			                    company.getKey() + " - " + plan.getKey(),
			                    plan.getValue()
			                ))
			        )
			        .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
			        .collect(Collectors.toList());
		
		System.out.println("sortedList===="+sortedList);
		
		return "success";
	}


}
