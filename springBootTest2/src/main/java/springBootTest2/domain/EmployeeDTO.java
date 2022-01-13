package springBootTest2.domain;

import java.util.Date;

import lombok.Data;

@Data
public class EmployeeDTO {
	// dto 는 컬럼명과 같아야 한다. 
	// command : html 의 값을 받아옴
	// 그래서 date 가 있으면 형식을 알려줘야하므로 format이 필요하다
	// dto : DB에 전달하는 것이므로 format이 필요하지 않다. 
	Integer empNum;
	String empName;
	String empId;
	String empPw;
	String empPwCon;
	 // @DateTimeFormat(pattern = "yyyy-MM-dd")
	Date empHireDate; 
	String empEmail;
	Integer empSalary;
	String empPhone;
}
