package com.application.instagramm.controllers;

import java.util.List;
import java.util.Set;

import com.application.instagramm.dto.ConnectionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.application.instagramm.connection.Connection;
import com.application.instagramm.connection.ConnectionService;
import com.application.instagramm.connection.Status;
import com.application.instagramm.user.AppUser;
import com.application.instagramm.user.AppUserService;

@RestController
@RequestMapping(ConnectionControllerAPI.MAIN_URL)
public class ConnectionControllerAPI {

	public static final String MAIN_URL = "/api/v1/connection/";

	private ConnectionService connectionService;
	private AppUserService appUserService;

	public ConnectionControllerAPI(ConnectionService connectionService, AppUserService appUserService) {
		this.connectionService = connectionService;
		this.appUserService = appUserService;
	}
	
	@PostMapping("addfriend")
	@ResponseStatus(HttpStatus.OK)
	public List<ConnectionDTO> addFriend() {
		AppUser user = new AppUser("username", "password", "email", "first", "last");
		AppUser user2 = new AppUser("username2", "password2", "email2", "first2", "last2");
		AppUser user3 = new AppUser("username3", "password3", "email3", "first3", "last3");
		AppUser user4 = new AppUser("username4", "password4", "email4", "first4", "last4");

		// appUserService.saveAppUser(user);
		// appUserService.saveAppUser(user2);
		// appUserService.saveAppUser(user3);
		// appUserService.saveAppUser(user4);

		Connection af = new Connection();
		af.setStatus(Status.PENDING);
		af.setAppUser(user);
		af.setFriend(user2);
		connectionService.saveConnection(af);
		Set<Connection> connections = user.getConnections();
		connections.add(af);
		user.setConnections(connections);
		// appUserService.saveAppUser(user);

		Connection af2 = new Connection();
		af2.setStatus(Status.PENDING);
		af2.setAppUser(user4);
		af2.setFriend(user);
		connectionService.saveConnection(af2);
		// return ResponseEntity.status(200).body(appUserService.connectionList(user));

		return connectionService.getConnections(user, Status.PENDING, true);
	}

}
