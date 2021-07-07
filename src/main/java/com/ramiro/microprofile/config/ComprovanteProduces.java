package com.ramiro.microprofile.config;

import javax.enterprise.inject.Produces;

import com.ramiro.binder.ServiceBind;

public class ComprovanteProduces {
	
	@Produces
	public ServiceBind getServiceBind() {
		return new ServiceBind();
	}

}
