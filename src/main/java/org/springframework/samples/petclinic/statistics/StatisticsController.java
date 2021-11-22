package org.springframework.samples.petclinic.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.Map;

@Controller
public class StatisticsController {

    // private final StatisticsService statisticsService;

    // @Autowired
    // public StatisticsController(StatisticsService statisticsService){
    //     this.statisticsService = statisticsService;
    // }

    @InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

    @GetMapping(value = "/statistics")
    public String initStatistics(Map<String, Object> model){
        model.put("statistics", new Statistics());
        return "statistics/findStatistics";
    }





    
}
