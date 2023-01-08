package cn.itsource.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.itsource.domain.Vip;
import cn.itsource.query.VipQuery;
import cn.itsource.service.VipService;

@Controller
@RequestMapping("/vip")
public class VipController {
	
	//注入Service层
	@Autowired
	private VipService VipService;
	
	@RequestMapping("/list")
	public ModelAndView queryAll(VipQuery query) {
		
		ModelAndView mv = new ModelAndView();
		List<Vip> queryAll = VipService.queryAll(query);
		mv.addObject("list", queryAll);
		mv.addObject("query", query);
		mv.setViewName("vip/vipList");
		
		return mv;
		
	}
	
	@RequestMapping("/del")
	public String delVip(Long id) {
		VipService.delVip(id);
		return "redirect:/vip/list";
	}
	
	@RequestMapping("/skip")
	public String addSkip() {
		return "vip/add";
	}
	
	@RequestMapping("/saveAndUpdate")
	public String saveVip(Vip v) {
		if (v.getId() == null || "".equals(v.getId())) {
			VipService.saveVip(v);
		} else {
			VipService.updateVip(v);
		}
		
		return "redirect:/vip/list";
	}
	
	@RequestMapping("/update")
	public ModelAndView queryVip(Long id) {
		ModelAndView mv = new ModelAndView();
		Vip targetVip = VipService.queryOne(id);
		mv.addObject("target", targetVip);
		mv.setViewName("vip/add");
		return mv;
	}

}
