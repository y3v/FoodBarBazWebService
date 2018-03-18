package examples;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HelloController {
	//map requests to particular url requests
	
	@RequestMapping("/hello") // at the url /hello -> execute the following method - this way is only for a GET
	public String sayHi() {
		return "hi you";
	}
}
