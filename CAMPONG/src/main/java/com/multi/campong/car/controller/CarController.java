package com.multi.campong.car.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.multi.campong.car.model.service.CarService;
import com.multi.campong.car.model.vo.Car;
import com.multi.campong.common.util.PageInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/car") // 요청 url의 상위 url을 모두 처리할때 사용
@Controller
public class CarController {

	@Autowired
	private CarService service;

	@GetMapping("/traffic-car")
	public String car(Model model, @RequestParam Map<String, String> paramMap) {
		
//		String searchValue = null;
		int page = 1;
		
		// 탐색할 맵을 선언
		Map<String, String> searchMap = new HashMap<String, String>();
		try {
			String searchValue = paramMap.get("searchValue");
			if (searchValue != null && searchValue.length() > 0) {
				searchMap.put("searchValue", searchValue);
			}
			page = Integer.parseInt(paramMap.get("page"));
		} catch (Exception e) {}

		int carCount = service.getCarCount(searchMap);
		PageInfo pageInfo = new PageInfo(page, 10, carCount, 3);
		List<Car> list = service.getCarList(pageInfo, searchMap);

		model.addAttribute("list", list);
		model.addAttribute("paramMap", paramMap);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("carCount", carCount);
		
//		log.info("searchMap : " + searchMap.toString());
//		log.info("paramMap : " + paramMap.toString());
//		log.info(Integer.toString(carCount));
		
		return "car/traffic-car";
	}
	
	@GetMapping("/booking-confirm")
	public String bookingConfirm(Model model, @RequestParam("rentNo") int rentNo) {
		Car content = service.findByNo(rentNo);
		
		model.addAttribute("content", content);
		
		log.info(Integer.toString(rentNo));
		log.info(content.toString());
		
		return "car/booking-confirm";
	}
}