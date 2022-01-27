package kosaShoppingMall.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberCommand {
	String memNum;
	String memId;
	String memPw;
	String memPwCon;
	String memName;
	String memAddr;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date memRegiDate;
	String memGender;
	String memPhone;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date memBirth;
	String memEmail;
	
	public boolean memPwEqualsmemPwCon() {
		return memPw.equals(memPwCon);
	}
}
