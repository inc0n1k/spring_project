package n1k.spring_project.service;

import n1k.spring_project.model.Option;
import n1k.spring_project.model.Value;
import n1k.spring_project.repository.ValueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValueService {

	//***Constructor********************************

	private final ValueRepository valueRepository;

	public ValueService(ValueRepository valueRepository) {
		this.valueRepository = valueRepository;
	}

	//**********************************************

	public Value getValue(long id) {
		return valueRepository.findValueById(id);
	}

	public void saveValue(Value value){
		valueRepository.save(value);
	}

	public void saveValueNow(Value value){
		valueRepository.saveAndFlush(value);
	}

//	public List<String> getUniqueValuesByOption(Option option){
//		return valueRepository.getUniqueValuesByOption(option);
//	}

	public List<Value> getUniqueValuesByOption(Option option){
		return valueRepository.getUniqueValuesByOption(option);
	}

}//close class ValueService
