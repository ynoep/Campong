package com.multi.campong;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multi.campong.camping.model.vo.Camping;
import com.multi.campong.main.model.service.MainService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {
	
	@Autowired
	private MainService service;
	
	//기본은 캠핑장지도 서울로 처리함
	@GetMapping("/MainList.do")
	public @ResponseBody List<Camping> campingDo() {
		List<Camping> list = service.selectCamping("서울");
		System.out.println(list);
		return list;
	}
	
	// 캠핑장지도 클릭하면 랜덤으로 4개뿌려줌
	  @GetMapping("/MainList.do2") 
	  public @ResponseBody List<Camping> mainList(@RequestParam(value="idCheck")String idCheck){ 
		  log.info("리스트 요청, param : " + idCheck);
		  System.out.println(idCheck);
		  List<Camping> list2  = service.selectCamping2(idCheck);
		  System.out.println(list2);
		  return list2; }
	 
}
