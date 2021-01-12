package n1k.spring_project.service;

import n1k.spring_project.sup.FilterClass;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FilterService {

    public Map<String, List<String>> fromFilterClassListToParametersMap(FilterClass[] filterClasses){
        Map<String,List<String>> map = new HashMap<>();
        for(FilterClass filterClass: filterClasses){
            if(!map.containsKey(filterClass.getName())){
                List<String> list = new ArrayList<>();
                list.add(filterClass.getValue());
                map.put(filterClass.getName(),list);
            } else {
                map.get(filterClass.getName()).add(filterClass.getValue());
            }
        }
        return map;
    }

}//close FilterService
