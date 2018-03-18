package examples.topic;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {
	
	@RequestMapping("/topics")
	public List<Topic> getAllTopics() {
		return Arrays.asList(
				new Topic("Spring", "Spring Framwork", "Spring boot woot woot"),
				new Topic("Simpsons", "Homer Simpson", "Simpsons test topic"),
				new Topic("JSF", "JSF Framwork", "JSF sucks compared to Spring")
				);
		//return type will be auto converted to a JSON and sent back as the HTTP response
	}
	
}
