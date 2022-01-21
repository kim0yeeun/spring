package springBootTest2.service.emplib;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.Model;

import springBootTest2.domain.EmplibDTO;
import springBootTest2.mapper.EmplibMapper;

@Component
@Service
public class EmplibInfoService {
	@Autowired
	EmplibMapper emplibMapper;
	public void execute(String libNum, Model model) {
		EmplibDTO dto = emplibMapper.selectOne(libNum);
		model.addAttribute("dto", dto);
	}
}
