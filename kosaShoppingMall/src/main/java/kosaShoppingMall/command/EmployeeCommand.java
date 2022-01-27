package kosaShoppingMall.command;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class EmployeeCommand {
	@Size(min = 4, max = 12, message = "4-12자리로 입력하세요.")
	// 사이즈 범위를 벗어나면 에러메세지 출력 
	String empId;
	
	// empty : 공백문자여도 비어있지만 않으면 ㄱㅊ
	@NotEmpty(message = "비밀번호를 입력하세요.")
	@Size(min=3, max=12)
	String empPw;
	
	// blank : 공백문자는 입력해도 취급하지 않음 
	@NotBlank(message = "비밀번호확인을 입력하세요.")
	@Size(min=3, max=12)
	String empPwCon;
	
	@NotEmpty(message = "이름을 입력하세요.")
	String empName;
	
	@NotEmpty(message = "주소를 입력하세요.")
	String empAddr;
	
	@NotEmpty(message = "전화번호를 입력하세요.")
	String empPhone;

	public boolean isEmpPwEqualsEmpPwCon() {
		return empPw.equals(empPwCon);
	}
	
}
