package kz.poll.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kz.poll.common.util.ui.PaginationInfo;
import kz.poll.data.model.Category;
import kz.poll.service.CategoryService;
import kz.poll.service.PollService;
import kz.poll.data.dto.PollUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/categories")
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	PollService pollService;
	
	@RequestMapping(value = "/view")
	public String view(@RequestParam(required = true)	String name,
			@RequestParam(required = false) String page,
			@RequestParam(required = false) String pageSize, Model model,
			Principal principal) throws Exception {

		int pageLimit = pageSize != null ? Integer.parseInt(pageSize) : 20;
		int currentPage = page != null ? Integer.parseInt(page) : 1;
		String listAction = "categories/view";
		Map<String, String> listActionParams = new HashMap<String, String>();
		Category cat = null;
		if (name != null) {
			cat = categoryService.getPollsSliceByTagName(name, currentPage,
						pageLimit);
			listActionParams.put("name", name);
			
			if (cat != null) {
				List<PollUI> polls = pollService.findById(cat.getPolls());
				PaginationInfo pageInfo = new PaginationInfo(currentPage,cat.getPollCount(),
												pageLimit, listAction,listActionParams);
				model.addAttribute("polls", polls);
				model.addAttribute("pageInfo", pageInfo);
			}
		}

		return "opros/index";
	}

}
