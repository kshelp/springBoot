package dev.zeronelab.mybatis.vo;

import lombok.Data;

@Data
public class NewsEntity {
	private String title;
	private String journalist;
	private String publisher;
	private String reg_dt;
}
