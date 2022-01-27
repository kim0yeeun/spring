package kosaShoppingMall.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import kosaShoppingMall.domain.EmployeeDTO;

@Component
@Repository(value="kosaShoppingMall.mapper.EmployeeMapper")
public interface EmployeeMapper {
	public Integer empInsert(EmployeeDTO dto);
	public List <EmployeeDTO> selectAll();
	public EmployeeDTO selectOne(String empId);
	public Integer empUpdate(EmployeeDTO dto);
	public Integer empDelete(String empId);

}
