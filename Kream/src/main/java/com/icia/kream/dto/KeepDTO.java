package com.icia.kream.dto;

import lombok.Data;

@Data
public class KeepDTO {
	//	상품 찜 목록 관리
	private int knumber;	//찜번호 pk
	private String kid;		//회원ID fk
	private int kpnumber;		//찜목록 fk
	
	private ProductDTO productdto; // 조인

	public int getKnumber() {
		return knumber;
	}

	public void setKnumber(int knumber) {
		this.knumber = knumber;
	}

	public String getKid() {
		return kid;
	}

	public void setKid(String kid) {
		this.kid = kid;
	}

	public int getKpnumber() {
		return kpnumber;
	}

	public void setKpnumber(int kpnumber) {
		this.kpnumber = kpnumber;
	}

	public ProductDTO getProductdto() {
		return productdto;
	}

	public void setProductdto(ProductDTO productdto) {
		this.productdto = productdto;
	}

	@Override
	public String toString() {
		return "KeepDTO [knumber=" + knumber + ", kid=" + kid + ", kpnumber=" + kpnumber + ", productdto=" + productdto
				+ "]";
	}
	
	
}
