package com.anil.imcs.jsonproject.app;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.anil.imcs.jsonproject.entity.Address;
import com.anil.imcs.jsonproject.entity.Customer;
import com.anil.imcs.jsonproject.entity.PaymentMethods;
import com.anil.imcs.jsonproject.entity.PaymentMethods.CardType;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class CustomerJSONApp {

	public static void main(String[] args) {
		
		Address address = new Address(101, "6445 Love Dr.", "", "Irving", "Texas", "75038", "USA");
		List<Address> addressList = new ArrayList<Address>();
		addressList.add(address);
		PaymentMethods paymentMethod = new PaymentMethods(1234567891234567L, "Anil Adhikari", new Date(), new Date(), CardType.CREDIT);
		List<PaymentMethods> methodList = new ArrayList<PaymentMethods>();
		methodList.add(paymentMethod);
		Customer customer = new Customer(1002, "Anil Adhikari", new Date(), 80000.0, addressList, methodList);
		
		try {
			boolean flag = serializeCustomer(customer);
			if(flag)
				System.out.println("Customer is written into a json file.");
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			Customer deCustomer = deSerializeCustomer();
			System.out.println(deCustomer.getCustomerName());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static Customer deSerializeCustomer() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		File file = new File("src/main/customer.json");
		
        //mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		
		Customer customer = mapper.readValue(file, Customer.class);
		return customer;
	}

	private static boolean serializeCustomer(Customer customer) throws JsonGenerationException, JsonMappingException, IOException {

		File file = new File("src/main/customer.json");
		
		ObjectMapper mapper = new ObjectMapper();
		
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		
		mapper.writeValue(System.out, customer);
		mapper.writeValue(file, customer);

		return true;
	}
	
	

}
