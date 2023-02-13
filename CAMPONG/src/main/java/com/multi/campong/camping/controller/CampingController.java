package com.multi.campong.camping.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.multi.campong.bagpacking.model.vo.Bagpacking;
import com.multi.campong.bagpacking.model.vo.Fishing;
import com.multi.campong.camping.model.service.CampingService;
import com.multi.campong.camping.model.vo.Camping;
import com.multi.campong.common.util.PageInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/camping") // 요청 url의 상위 url을 모두 처리할때 사용
@Controller
public class CampingController {
	
	@Autowired
	private CampingService service;
	
	@GetMapping("/camping-main")
	public String campingMain(Model model, @RequestParam Map<String, String> paramMap,
			@RequestParam(required = false) List<String> checkedTheme,
			@RequestParam(required = false) List<String> checkedFclty,
			@RequestParam(required = false) String checkedPet,
			@RequestParam(required = false) String sido,
			@RequestParam(required = false) String lctCl) {
		log.info("리스트 요청, param : " + paramMap);
		log.info("리스트 요청, checkedTheme : " + checkedTheme);
		log.info("리스트 요청, checkedFclty : " + checkedFclty);
		log.info("리스트 요청, checkedPet : " + checkedPet);
		log.info("리스트 요청, sido : " + sido);
		log.info("리스트 요청, lctCl : " + lctCl);
		int page = 1;

		// 탐색할 맵을 선언
		Map<String, Object> searchMap = new HashMap<>();
		try {
			String searchSido = paramMap.get("sido");
			String searchGugun = paramMap.get("gugun");
			String searchLctCl = paramMap.get("lctCl");
			String searchValue = paramMap.get("searchValue");
			
			if(searchSido != null && searchSido.length() > 0 && !searchSido.equals("전체")) {
				searchMap.put("sido", searchSido);
			}
			if(searchGugun != null && searchGugun.length() > 0 && !searchGugun.equals("전체")) {
				searchMap.put("gugun", searchGugun);
			}
			if(searchLctCl != null && searchLctCl.length() > 0 && !searchLctCl.equals("전체")) {
				searchMap.put("lctCl", searchLctCl);
			}
			if(searchValue != null && searchValue.length() > 0) {
				searchMap.put("facltNm", searchValue);
			}
			searchMap.put("checkedTheme", checkedTheme);
			if (checkedTheme == null) {
				checkedTheme = new ArrayList<>();
			}
			searchMap.put("checkedFclty", checkedFclty);
			if (checkedFclty == null) {
				checkedFclty = new ArrayList<>();
			}
			searchMap.put("checkedPet", checkedPet);
			if (checkedPet == null) {
				checkedPet = new String();
			}
			if (lctCl == null) {
				lctCl = new String();
			}
			if (sido == null) {
				sido = new String();
			}
			page = Integer.parseInt(paramMap.get("page"));
		} catch (Exception e) {}
		
		int campingCount = service.getCampingCount(searchMap);
		PageInfo pageInfo = new PageInfo(page, 10, campingCount, 5);
		List<Camping> list = service.getCampingList(pageInfo, searchMap);
		
		model.addAttribute("list", list);
		model.addAttribute("paramMap", paramMap);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("campingCount", campingCount);
		model.addAttribute("checkedTheme", checkedTheme);
		model.addAttribute("checkedFclty", checkedFclty);
		model.addAttribute("checkedPet", checkedPet);
		model.addAttribute("selectedSido", sido);
		model.addAttribute("selectedLctCl", lctCl);
		
		return "camping/camping-main";
	}
	
	@RequestMapping("/camping-detail")
	public String campingDetail(Model model, @RequestParam("contentId") int contentId) {
		Camping content = service.findByNo(contentId);
		String[] facilitys = content.getSbrsCl().split(",");
		
		if(content == null) {
			return "redirect:error";
		}
		
		model.addAttribute("content", content);
		model.addAttribute("facilitys", facilitys);
//		model.addAttribute("replyList", bagpacking.getReplyList());
		return "camping/camping-detail";
	}
}
