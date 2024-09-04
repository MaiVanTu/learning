package com.tumai.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class KafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}

	@Bean
	public Function<String, String> graalUppercaseFunction() {
		return String::toUpperCase;
	}

	@Bean
	public Consumer<String> graalLoggingConsumer() {
		return s -> {
			System.out.println("++++++Received:" + s);
		};
	}

	@Bean
	public Supplier<String> graalSupplier() {
		final String[] splitWoodchuck = "How much wood could a woodchuck chuck if a woodchuck could chuck wood?"
				.split(" ");
		final AtomicInteger wordsIndex = new AtomicInteger(0);
		return () -> {
			int wordIndex = wordsIndex.getAndAccumulate(splitWoodchuck.length,
					(curIndex, numWords) -> curIndex < numWords - 1 ? curIndex + 1 : 0);
			return splitWoodchuck[wordIndex];
		};
	}

}
