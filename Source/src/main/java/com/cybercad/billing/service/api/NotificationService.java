package com.cybercad.billing.service.api;

import java.util.Set;

public interface NotificationService {
	
	public void sendMessage(String message,Set<Channel> channel);

}
