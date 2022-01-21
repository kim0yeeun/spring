package springBootTest2.service.emplib;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootTest2.domain.EmplibDTO;
import springBootTest2.mapper.EmplibMapper;

@Component
@Service
public class EmplibListService {
	@Autowired
	EmplibMapper emplibMapper;
	public void execute (Model model) {
		EmplibDTO dto = new EmplibDTO();
		List<EmplibDTO> list = emplibMapper.selectAll();
		model.addAttribute("list", list);
	}

}
