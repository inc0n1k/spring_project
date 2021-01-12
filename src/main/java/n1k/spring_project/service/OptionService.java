package n1k.spring_project.service;

import n1k.spring_project.model.Category;
import n1k.spring_project.model.Option;
import n1k.spring_project.repository.OptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionService {

	//***Constructor********************************

	private final OptionRepository optionRepository;

	public OptionService(OptionRepository optionRepository) {
		this.optionRepository = optionRepository;
	}

	//**********************************************

	public Option getOption(long id){
		return optionRepository.findOptionsById(id);
	}

	public List<Option> getOptionsByCategory(Category category){
		return optionRepository.findOptionsByCategory(category);
	}
	public void saveOption(Option option){
		optionRepository.save(option);
	}

	public void saveOptionNow(Option option){
		optionRepository.saveAndFlush(option);
	}

}
