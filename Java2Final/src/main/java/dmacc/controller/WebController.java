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

import dmacc.beans.Book;
import dmacc.repository.BookRepository;
import dmacc.repository.MemberRepo;

@Controller
public class WebController {
	@Autowired
	BookRepository bRepo;
	@Autowired
	MemberRepo mRepo;
	
	@GetMapping("/addBook") public String addNewBook(Model model) {
		Book b = new Book();
		model.addAttribute("newBook",b);
		return "addBook";
	}
	@PostMapping("/addBook") public String addNewBook(@ModelAttribute Book b, Model model) {
		bRepo.save(b);
		return viewAllBooks(model);
	}
	@GetMapping("/viewAllBooks") public String viewAllBooks(Model model) {
		model.addAttribute("b", bRepo.findAll());
		return "viewBooks";
	}
	@GetMapping("/index") public String home() {
		return"index";
	}
	
	@GetMapping("/checkOutBook") public String checkOutBook(Model model) {
		List<Book> bList = new ArrayList<>();
		for(int i =0; i < bRepo.findAll().size(); i++) {
			if(bRepo.findAll().get(i).getCheckOutStatus()==0) {
				bList.add(bRepo.findAll().get(i));
			}
		}
		model.addAttribute("books", bList);
		return "checkOut";
	}
	@GetMapping("/bookCheckOut/{id}") public String bookCheckOut(@PathVariable("id") long id, Model model) {
		Book b = bRepo.findById(id).orElse(null);
		b.setCheckOutStatus(1);
		bRepo.save(b);
		return checkOutBook(model);
	}
	@GetMapping("/returnBook") public String returnBook(Model model) {
		List<Book> bList = new ArrayList<>();
		for(int i =0; i < bRepo.findAll().size(); i++) {
			if(bRepo.findAll().get(i).getCheckOutStatus()==1) {
				bList.add(bRepo.findAll().get(i));
			}
		}
		model.addAttribute("currentBooks", bList);
		return "returnBook";
	}
	@GetMapping("/bookReturn/{id}") public String bookReturn(@PathVariable("id") long id, Model model) {
		Book b = bRepo.findById(id).orElse(null);
		b.setCheckOutStatus(0);
		bRepo.save(b);
		return returnBook(model);
	}
	@GetMapping("/viewAvailableBooks") public String availableBooks(Model model) {
		List<Book> bList = new ArrayList<>();
		for(int i = 0; i < bRepo.findAll().size(); i++) {
			if(bRepo.findAll().get(i).getCheckOutStatus()==0) {
				bList.add(bRepo.findAll().get(i));
			}
		}
		model.addAttribute("currentBooks",bList);
		return "availableBooks";
	}
	
}
