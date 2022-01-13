package springBootTest1.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
// request가 html의 name을 가져오는 것이기 때문에
// command에서도 html의 이름과 같아야한다. 다르면 못받아wa요오옹오 
public class EmployeeCommand {
	Integer empNum;
	String empName;
	String empId;
	String empPw;
	String empPwCon;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date empHireDate; 
	String empEmail;
	Integer empSalary;
	String empPhone;
	public boolean isempPwEqualsempPwCon() {
		return empPw.equals(empPwCon);
	}
}
// 롬복하든 셋터겟터쓰든 아라서오 
