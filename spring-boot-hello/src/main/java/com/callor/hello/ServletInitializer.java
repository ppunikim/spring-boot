package com.callor.hello;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/* SpringBootServletInitializer 클래스
Boot Project에서 root-context.xml, servlet-context.xml을 대신하는 클래스
여기에서 HelloApplication.class.main() method를 내부에서 호출한다.
 */

@EnableAutoConfiguration
/* ComponentScan 이란?
Annotation을 이용해 class를 scan해 bean으로 생성하도록 지시함.
여러개를 scan할땐 문자열 배열로 만들어 사용한다.
 */
@ComponentScan({"com.callor.hello.service",
				"com.callor.controller"})
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HelloApplication.class);
	}

}
