package org.springframework.samples.petclinic.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
        return "statistics/findStatistics";
    }

    @GetMapping(value = "/statistics/player")
	public String processStatistics(@RequestParam("id") String id) {
        System.out.println("La id es:"+id);
		Statistics result = this.statisticsService.findStatisticsByID(Integer.parseInt(id));
        System.out.println("hola");
		return "redirect:/statistics/player/" + result.getId();

	}

    @GetMapping("/statistics/player/{statisticId}")
	public ModelAndView showStatistics(@PathVariable("statisticId") int statisticId) {
		ModelAndView mav = new ModelAndView("/statistics/statisticsList");
		mav.addObject("statistics", this.statisticsService.findStatisticsByID(statisticId));
		return mav;
	}  
}
