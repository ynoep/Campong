package com.multi.campong.camping.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.multi.campong.camping.model.vo.Camping;

@Mapper
public interface CampingMapper {
	List<Camping> selectCampingList(Map<String, Object> map);
	int selectCampingCount(Map<String, Object> map);
	Camping selectCampingByNo(int contentId);
}
