package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LController {

	@Autowired
	Lrepo repo;

	@GetMapping("/home")
	public String gethome() {
		return "Regi";

	}

	@GetMapping("/save")
	public String savedetails(LEntity l, @RequestParam String username, @RequestParam String password) {

		l.setUsername(username);
		l.setPassword(password);

		repo.save(l);

		return "Success";
	}

	@GetMapping("/my")
	public String getloginpage() {
		return "login";

	}

	@GetMapping("/search")
	public String searchByName(@RequestParam String username, @RequestParam String password, Model model) {
		// Step 3: Call the repository method to check if the name is present
		List<LEntity> lusername = repo.findByUsername(username);
		List<LEntity> lpassword = repo.findByPassword(password);

		if (lusername != null && lpassword != null) {
			// Step 4: Check the username prefix and redirect to the appropriate page
			if (username.startsWith("emp")) {
				return "employee";
			} else if (username.startsWith("int")) {
				return "interview";
			}
		} else {
			// Step 5: If the username and password are incorrect, return an error message
			model.addAttribute("errorMessage", "Incorrect username or password");
		}

		// Step 6: If the username prefix is not recognized, return an error message
		model.addAttribute("errorMessage", "Invalid username");

		// Step 7: Return the login page
		return "error";

	}
}
