package com.escuelaing.arep.awsgatewaylambda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class AwsGatewayLambdaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsGatewayLambdaApplication.class, args);
	}

}
