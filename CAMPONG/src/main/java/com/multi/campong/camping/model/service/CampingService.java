package com.multi.campong.camping.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.campong.bagpacking.model.vo.Bagpacking;
import com.multi.campong.camping.model.mapper.CampingMapper;
import com.multi.campong.camping.model.vo.Camping;
import com.multi.campong.common.util.PageInfo;

@Service
public class CampingService {
	@Autowired
	private CampingMapper mapper;
	
	public int getCampingCount(Map<String, Object> param) {
		return mapper.selectCampingCount(param);
	}
	
	public List<Camping> getCampingList(PageInfo pageInfo, Map<String, Object> param){
		param.put("limit", "" + pageInfo.getListLimit());
		param.put("offset", "" + (pageInfo.getStartList() - 1));
		return mapper.selectCampingList(param);
	}

	public Camping findByNo(int contentId) {
		Camping camping = mapper.selectCampingByNo(contentId); 
//		camping.setReadCount(camping.getReadCount() + 1);  
//		mapper.updateReadCount(camping);
		return camping; 
	}

}
