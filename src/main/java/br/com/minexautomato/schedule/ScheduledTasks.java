package br.com.minexautomato.schedule;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.minexautomato.MinexAutomato;

@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(timeUnit = TimeUnit.MINUTES, fixedRate = 62, initialDelay = 1)
	public void executeGetBonus() throws IOException {
		log.info("Indo pegar o bonus as: {}", dateFormat.format(new Date()));

		MinexAutomato minex = new MinexAutomato();
		minex.executeGetBonus();

		log.info("Bonus pego");
	}
}