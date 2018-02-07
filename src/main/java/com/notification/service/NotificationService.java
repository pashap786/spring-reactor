package com.notification.service;

import com.notification.pojo.NotificationData;

public interface NotificationService {

	void initiateNotification(NotificationData notificationData) throws InterruptedException;
	
}
