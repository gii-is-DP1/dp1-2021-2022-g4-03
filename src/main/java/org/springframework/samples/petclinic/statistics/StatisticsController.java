package org.springframework.samples.petclinic.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class StatisticsController {

    @Autowired
    private final StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService){
        this.statisticsService = statisticsService;
    }

    @InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

    @GetMapping(value = "/statistics")
    public String initStatistics(Map<String, Object> model){
        model.put("statistics", new Statistics());
        return "statistics/statisticsList";
    }

    // @GetMapping(value = "/statistics")
    // public ModelAndView initStatistics(Map<String, Object> model){
    //     model.put("statistics", new Statistics());
    //     ModelAndView mav = new ModelAndView("statistics/statisticsList");
	// 	mav.addObject("statistics", this.statisticsService.findStatisticsByID(1));
    //     return mav;
    // }





    
}
