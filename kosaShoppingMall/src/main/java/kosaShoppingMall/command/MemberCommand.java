package kosaShoppingMall.command;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class MemberCommand {
	String memNum;
	
	@Size(min = 4, max = 12, message = "4-12자리로 입력하세요.")
	String memId;

	@NotEmpty(message = "비밀번호를 입력하세요.")
	@Size(min=3, max=12)
	String memPw;

	@NotBlank(message = "비밀번호확인을 입력하세요.")
	@Size(min=3, max=12)
	String memPwCon;

	@NotEmpty(message = "이름을 입력하세요.")
	String memName;

	@NotEmpty(message = "주소를 입력하세요.")
	String memAddr;
	
	@NotNull(message = "등록일을 입력하세요.")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date memRegiDate;

	@NotBlank(message = "성별을 선택하세요.")
	String memGender;

	@NotEmpty(message = "전화번호를 입력하세요.")
	String memPhone;

	@NotNull(message = "생일을 입력하세요.")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date memBirth;

	@Email(message ="이메일 형식에 맞지 않습니다. ")
	@NotBlank(message = "이메일을 입력하세요.")
	String memEmail;
	
	public boolean memPwEqualsmemPwCon() {
		return memPw.equals(memPwCon);
	}

}
