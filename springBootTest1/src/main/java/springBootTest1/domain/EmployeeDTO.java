package springBootTest1.domain;
// 스프링부트에서는 DTO를 도메인이라는 패키지 안에 저장

import java.util.Date;

import lombok.Data;

@Data
public class EmployeeDTO {
	Integer empNum;
	String empName;
	String empId;
	String empPhone;
	String empPw;
	Date empHireDate;
	String empEmail;
	Integer empSalary;
	
}
