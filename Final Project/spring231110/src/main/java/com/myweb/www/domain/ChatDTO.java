package com.myweb.www.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatDTO {

	int chatID;
	String fromID;
	String toID;
	String chatContent;
	String chatTime;
	int chatRead;//
	String fromNm;//
	String Nm;//
	int emp_no;//
	
//	private CompanyVO companyvo;
	FileVO filevo;
}