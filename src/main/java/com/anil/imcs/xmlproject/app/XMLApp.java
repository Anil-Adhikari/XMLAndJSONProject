package com.anil.imcs.xmlproject.app;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;

import com.anil.imcs.xmlproject.jaxb.entity.Customer;

public class XMLApp {
	public static void main(String[] args) {
		
		try {
			marshalling();
			//unmarshalling();
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
	}

	private static Customer unmarshalling() throws DatatypeConfigurationException {
		Customer customer = new Customer();		
		File file = new File("src/main/resources/xml/Customer.xml");
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
			Unmarshaller jaxbUnMarshaller = jaxbContext.createUnmarshaller();
			
			customer = (Customer)jaxbUnMarshaller.unmarshal(file);
			System.out.println(customer);
			return customer;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void marshalling() throws DatatypeConfigurationException {
		Customer customer = unmarshalling();
		
		/*GregorianCalendar c = new GregorianCalendar();
		c.setTime(new Date());
		XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		
		Address address = new Address(101, "6445 Love Dr.", "", "Irving", "Texas", "75038", "USA");
		
		PaymentType paymentMethod = new PaymentType("1234567891234567", "Anil Adhikari", date2, date2, "CREDIT");
		List<PaymentType> paymentList = new ArrayList<PaymentType>();
		paymentList.add(paymentMethod);
		
		customer.setCustomerId(101);
		customer.setCustomerName("Anil Adhikari");
		customer.setCustomerAddress(address);
		customer.setCustomerDob(date2);
		customer.setCustomerPhone("2524818608");
		customer.setCustomerAnnualSalary(80000);
		customer.setPaymentType(paymentList);*/
		
		File file = new File("src/main/resources/xml/MarshalledCustomer.xml");
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(customer, file);
			jaxbMarshaller.marshal(customer, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
}
