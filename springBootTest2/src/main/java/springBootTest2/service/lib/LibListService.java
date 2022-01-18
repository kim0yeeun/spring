package springBootTest2.service.lib;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootTest2.domain.LibDTO;
import springBootTest2.mapper.LibMapper;

@Component
@Service
public class LibListService {
	@Autowired
	LibMapper libMapper;
	public void execute(Model model) {
		List<LibDTO> list = libMapper.selectAll();
		model.addAttribute("list", list);
	}
}
