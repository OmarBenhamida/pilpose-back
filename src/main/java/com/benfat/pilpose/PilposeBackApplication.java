package com.benfat.pilpose;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextClosedEvent;

@SpringBootApplication
@ComponentScan(basePackages = { "com.benfat" })
public class PilposeBackApplication {

	private static Logger logger = LoggerFactory.getLogger(PilposeBackApplication.class);

	public PilposeBackApplication() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static ConfigurableApplicationContext startMicroservice(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(PilposeBackApplication.class, args);
		ctx.addApplicationListener((ContextClosedEvent arg0) -> ctx.close());
		return ctx;

	}

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = PilposeBackApplication.startMicroservice(args);
		if (ctx.isActive() && logger.isInfoEnabled()) {
			logger.info("Pilpose started correctly");
		}
	}



}
