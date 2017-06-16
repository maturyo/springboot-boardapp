package com.example.web;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.BillBoards;
import com.example.service.BillBoardsService;

@Controller
@RequestMapping("billboards")
public class BillBoardsController {
	@Autowired
	BillBoardsService billboardsService;

	@ModelAttribute
	BillBoardsForm setUpForm() {
		return new BillBoardsForm();
	}

	@GetMapping
	String list(Model model) {
		List<BillBoards> billboards = billboardsService.findAll();
		model.addAttribute("billboards", billboards);
		return "billboards/list";
	}

	@PostMapping(path = "create")
	String add(@Validated BillBoardsForm form, BindingResult result, Model model) {
		BillBoards billboards = new BillBoards();
		BeanUtils.copyProperties(form, billboards);
		billboardsService.create(billboards);
		return "billboards/create";
	}

	@GetMapping(path = "edit", params = "form")
	String editForm(@RequestParam Integer id, @Validated BillBoardsForm form) {
		BillBoards billboards = billboardsService.findOne(id);
		BeanUtils.copyProperties(billboards, form);
		return "billboards/edit";
	}

	@PostMapping(path = "edit")
	String edit(@RequestParam Integer id, @Validated BillBoardsForm form, BindingResult result) {
		BillBoards billboards = new BillBoards();
		BeanUtils.copyProperties(form, billboards);
		billboards.setId(id);
		billboardsService.update(billboards);
		return "redirect:/billboards";
	}

	@GetMapping(path = "add", params = "goToTop")
	String goToTop() {
		return "billboards/list";
	}

	@PostMapping(path = "detail")
	String detail(@Validated BillBoardsForm form, BindingResult result, Model model) {
		return "billboards/detail";
	}

	@PostMapping(path = "delete")
	String delete(@RequestParam Integer id) {
		billboardsService.delete(id);
		return "redirect:/billboards";
	}
}