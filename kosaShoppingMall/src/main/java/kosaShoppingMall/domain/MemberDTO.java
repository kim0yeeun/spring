package kosaShoppingMall.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias(value="memDTO")
public class MemberDTO {
	String memNum;
	String memId;
	String memPw;
	String memName;
	String memAddr;
	Date memRegiDate;
	String memGender;
	String memPhone;
	Date memBirth;
	String memEmail;
	String memOk;
}
