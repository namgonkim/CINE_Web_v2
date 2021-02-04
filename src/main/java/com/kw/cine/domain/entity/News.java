package com.kw.cine.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "news")
@NoArgsConstructor(access =AccessLevel.PROTECTED)
public class News {

	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idx")
	private Long idx;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "imgfile")
	private String imgfile;
	
	@Column(name = "pid")
	private int pid;
	
	
	@Builder
	public News(Long idx, String title, String content, String imgfile, int pid) {
		this.idx = idx;
		this.title = title;
		this.content = content;
		this.imgfile = imgfile;
		this.pid = pid;
	}
}