package dmacc.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dmacc.beans.Member;
import dmacc.repository.BookRepository;
import dmacc.repository.MemberRepo;

@Controller
public class WebController2 {
	@Autowired
	BookRepository bRepo;
	@Autowired
	MemberRepo mRepo;
	
	@GetMapping("/addMember") public String addNewMember(Model model) {
		Member m = new Member();
		model.addAttribute("newMember",m);
		return "addMember";
	}
	@PostMapping("/addMember") public String addNewMember(@ModelAttribute Member m, Model model) {
		double fine = m.getFine();
		String f = String.format("%.2f", fine);
		m.setFine(Double.parseDouble(f));
		
		mRepo.save(m);
		return viewAllMembers(model);
	}
	@GetMapping("/viewAllMembers") public String viewAllMembers(Model model) {
		model.addAttribute("currentMembers", mRepo.findAll());
		return "viewMembers";
	}
	@GetMapping("viewAllFines") public String viewFines(Model model) {
		double totalFines =0;
		List<Member> mList = new ArrayList<>();
		for(int i =0; i < mRepo.findAll().size();i++) {
			if(mRepo.findAll().get(i).getFine()>0) {
				mList.add(mRepo.findAll().get(i));
			}
		}
		model.addAttribute("currentMembers",mList);
		for(int i = 0; i < mRepo.findAll().size(); i++) {
			totalFines = totalFines + mRepo.findAll().get(i).getFine();
		}
		model.addAttribute("totalFines",totalFines);
		return"viewFines";
	}
	@GetMapping("/edit/{id}") public String editMember(@PathVariable("id") long id, Model model) {
		Member m = mRepo.findById(id).orElse(null);
		model.addAttribute("newMember", m);
		return "addMember";
	}
	@PostMapping("/update/{id}") public String reviseContact(@ModelAttribute Member m, Model model) {
		double fine = m.getFine();
		String f = String.format("%.2f", fine);
		m.setFine(Double.parseDouble(f));
		mRepo.save(m);
		return viewAllMembers(model);
	}
}
