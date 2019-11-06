package io.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.google.protobuf.util.JsonFormat;

import io.proto.GreetingProtos;
import io.proto.GreetingProtos.Greeting;
import io.proto.GreetingProtos.Greeting.Builder;


public class ProtoExample {

	public static void main(String args[]) {
		// Create new Greeting object - default value
		Greeting gp = GreetingProtos.Greeting.newBuilder().build();
		System.out.println(gp.getGreeting());

		// Create Greeting object & setting value
		Builder greetingBuilder = GreetingProtos.Greeting.newBuilder();
		greetingBuilder.setGreeting("hello");
		greetingBuilder.setFrom("suraj");
		Greeting greeting = greetingBuilder.build();
		System.out.println(greeting.getGreeting());

		try {
			// Write the message into a file. Serialize the object.
			// Contents of "greetings.bin" would be sent over the wire.
			FileOutputStream output = new FileOutputStream("greetings.bin");
			greeting.writeTo(output);
			output.close();

			// Deserialize the object from the file.
			Greeting greetingFromFile = Greeting.parseFrom(new FileInputStream("greetings.bin"));
			System.out.println(greetingFromFile.getGreeting());
			
			//Print as Json for debugging
			System.out.println(JsonFormat.printer().print(greetingFromFile));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
