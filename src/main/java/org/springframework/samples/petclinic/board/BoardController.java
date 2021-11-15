package org.springframework.samples.petclinic.board;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
    
    @Autowired
	BoardService boardService;
	
        @GetMapping({"/","/board"})
        public String welcome(Map<String, Object> model, HttpServletResponse response) {	    		
        response.addHeader("Refresh","1"); 
        model.put("now", new Date());
        model.put("board", boardService.findById(1).get());
        return "welcome";
        }

}
