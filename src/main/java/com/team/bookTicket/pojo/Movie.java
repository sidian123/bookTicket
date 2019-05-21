package com.team.bookTicket.pojo;

public class Movie {
	Integer id;
	String name;
	String kind;
	Integer duration;
	String introduction;
	String image;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public boolean canAdd() {
		if(!name.isEmpty() && !kind.isEmpty() && duration!=null) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 暂不考虑请求参数不存在的情况
	 * @return
	 */
	public boolean canUpdate() {
		if(id!=null && (!name.isEmpty() || !kind.isEmpty() || duration!=null || !introduction.isEmpty() || !image.isEmpty())) {
			return true;
		}else {
			return false;
		}
	}
}
