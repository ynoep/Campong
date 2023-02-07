package com.multi.campong.bagpacking.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MountainIsland {
	private int contentId;
	private String title;
	private String cat3;
	private String fisrtimage;
	private Double mapx;
	private Double mapy;
	private String addr1;
	private String addr2;
	private int readCount;
}
