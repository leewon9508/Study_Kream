package com.icia.kream.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductDTO {
	//	관리자 제품 관리
	private int pnumber;			//제품번호		pk
	private String pname;			//제품명
	private String pbrand;			//제품브랜드
	private int pretail;			//발매가
	private MultipartFile pfile;
	private String pfilename;		//이미지
	
	public int getPnumber() {
		return pnumber;
	}
	public void setPnumber(int pnumber) {
		this.pnumber = pnumber;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPbrand() {
		return pbrand;
	}
	public void setPbrand(String pbrand) {
		this.pbrand = pbrand;
	}
	public int getPretail() {
		return pretail;
	}
	public void setPretail(int pretail) {
		this.pretail = pretail;
	}
	public MultipartFile getPfile() {
		return pfile;
	}
	public void setPfile(MultipartFile pfile) {
		this.pfile = pfile;
	}
	public String getPfilename() {
		return pfilename;
	}
	public void setPfilename(String pfilename) {
		this.pfilename = pfilename;
	}
	@Override
	public String toString() {
		return "ProductDTO [pnumber=" + pnumber + ", pname=" + pname + ", pbrand=" + pbrand + ", pretail=" + pretail
				+ ", pfile=" + pfile + ", pfilename=" + pfilename + "]";
	}
	
	
}
