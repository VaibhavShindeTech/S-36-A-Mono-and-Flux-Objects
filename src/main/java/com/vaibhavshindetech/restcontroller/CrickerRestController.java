package com.vaibhavshindetech.restcontroller;

import java.time.Duration;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaibhavshindetech.response.Cricket;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

/**
 * 
 * @author Vaibhav
 *
 */
@RestController
public class CrickerRestController {

	@GetMapping(value = { "/score" }, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public ResponseEntity<Flux<Cricket>> getScore() {

		// creating cricket data in the form of object
		Cricket cricket = new Cricket(10, 0.3);

		// creating stream object to send the data
		Stream<Cricket> CricketStream = Stream.generate(() -> cricket);

		// give the stream object to flux object
		Flux<Cricket> cricketFlux = Flux.fromStream(CricketStream);

		// setting response interval
		Flux<Long> interval = Flux.interval(Duration.ofSeconds(5));

		// combine flux interval and cricket flux
		Flux<Tuple2<Long, Cricket>> zip = Flux.zip(interval, cricketFlux);

		// getting flux value from the zip
		Flux<Cricket> fluxMap = zip.map(Tuple2::getT2);

		return new ResponseEntity<Flux<Cricket>>(fluxMap, HttpStatus.OK);

	}
}
