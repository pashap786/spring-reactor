package com.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.notification.pojo.NotificationData;

import reactor.bus.Event;
import reactor.fn.Consumer;

@Controller
public class NotificationConsumer implements Consumer<Event<NotificationData>> {

	@Autowired
	private NotificationService notificationService;

	public void accept(Event<NotificationData> event) {
		NotificationData notificationData = event.getData();

		try {
			notificationService.initiateNotification(notificationData);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
