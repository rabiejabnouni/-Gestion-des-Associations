package print.print;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BissenApplication {

	public static void main(String[] args) {
		SpringApplication.run(BissenApplication.class, args);
		System.out.println("hello bissen");
	}

}
