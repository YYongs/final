package or.clearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyChatController {

	    @RequestMapping(value="/chat")
	    public String viewChattingPage() {
	        return "index2";
	    }
}
