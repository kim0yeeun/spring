package kosaShoppingMall.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import kosaShoppingMall.command.MemberCommand;
import kosaShoppingMall.domain.MemberDTO;
import kosaShoppingMall.mapper.MemberMapper;

@Component
@Service
public class MemberUpdateService {
	@Autowired
	MemberMapper memberMapper;
	public void execute(MemberCommand memberCommand) {
		MemberDTO dto = memberMapper.selectOne(memberCommand.getMemNum());
		dto.setMemId(memberCommand.getMemId());
		dto.setMemAddr(memberCommand.getMemAddr());
		dto.setMemBirth(memberCommand.getMemBirth());
		dto.setMemEmail(memberCommand.getMemEmail());
		dto.setMemGender(memberCommand.getMemGender());
		dto.setMemName(memberCommand.getMemName());
		dto.setMemPhone(memberCommand.getMemPhone());
		dto.setMemRegiDate(memberCommand.getMemRegiDate());
		dto.setMemNum(memberCommand.getMemNum());
		memberMapper.memUpdate(dto);
	}

}
