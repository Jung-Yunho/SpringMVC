package com.in28minutes.todo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.in28minutes.exception.ExceptionController;

@Controller
@SessionAttributes("name")
public class TodoController {

	@Autowired
	private TodoService service;
	
	private Log logger = LogFactory.getLog(ExceptionController.class);

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String listTodos(ModelMap model) {
		model.addAttribute("todos", service.retrieveTodos(retrivevLoggedInUserName()));
		return "list-todos";
	}

	private String retrivevLoggedInUserName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showTodopage(ModelMap model) {
		throw new RuntimeException("Dummy Exception");
		/*
		 * model.addAttribute("todo", new Todo(0, "yunho", "Default Desc", new Date(),
		 * false)); return "todo";
		 */
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "todo";
		}

		service.addTodo(retrivevLoggedInUserName(), todo.getDesc(), new Date(), false);
		model.clear();
		return "redirect:list-todos";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String updateTodo(ModelMap model, @RequestParam int id) {
		model.addAttribute("todo", service.retrieveTodo(id));
		return "todo";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(@Valid Todo todo, BindingResult result) {
		if (result.hasErrors())
			return "todo";
		service.updateTodo(todo);
		todo.setUser("yunho");
		return "redirect:list-todos";
	}

	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(ModelMap model, @RequestParam int id) {
		// Delete Todo
		service.deleteTodo(id);
		return "redirect:list-todos";
	}
	
	@ExceptionHandler(value = Exception.class)
	public String handleException(HttpServletRequest request, Exception exception) {
		logger.error("Request " + request.getRequestURI() + "Threw an Exception", exception);
		return "error-specific";
	}
}
