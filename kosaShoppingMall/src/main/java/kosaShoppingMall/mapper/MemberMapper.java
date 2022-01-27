package kosaShoppingMall.mapper;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import kosaShoppingMall.domain.MemberDTO;

@Component
@Repository("kosaShoppingMall.mapper.MemberMapper")
public interface MemberMapper {
	public String autoNum();
	public Integer memInsert(MemberDTO dto);
	public List<MemberDTO> selectAll();
	public MemberDTO selectOne(String memNum);
	public Integer memUpdate(MemberDTO dto);
	public Integer memDelete(String memNum);
	

}
