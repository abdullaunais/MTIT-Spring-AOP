package com.mtit.assignment2.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mtit.assignment2.models.Item;
import com.mtit.assignment2.models.User;
import com.mtit.assignment2.repository.ItemRepository;
import com.mtit.assignment2.repository.UserRepository;

@RestController
public class API {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ItemRepository itemRepository;


	/**
	 * Get All Users
	 */
	@RequestMapping(value = "/api/getAllUsers", method = RequestMethod.POST)
	public List<User> getUsers() {
		return this.getAllUsers();
	}
	
	/**
	 * Get All Items
	 */
	@RequestMapping(value = "/api/getAllItems", method = RequestMethod.POST)
	public List<Item> getItems() {
		return this.getAllItems();
	}
	
	private JSONObject parse(String data) {
		try {
			JSONParser parser = new JSONParser();
			return (JSONObject) parser.parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}


	/**
	 * Get User
	 */
	public User getUser(String username, String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte byteData[] = md.digest();

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			String md5 = sb.toString();
			
			Iterable<User> users = userRepository.findAll();
			for (User user : users) {
				if (user.getUsername().equals(username) && user.getPassword().equals(md5)) {
					return user;
				}
			}
			return null;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String md5 = "";
			return null;
		}
		
		
	}

	/**
	 * Get user by id
	 */
	public User getUser(Long id) {
		return userRepository.findOne(id);
	}

	/**
	 * Get all users
	 */
	public List<User> getAllUsers() {
		Iterable<User> users = userRepository.findAll();
		List<User> data = new ArrayList<User>();
		for (User user : users) {
			data.add(user);
		}
		return data;
	}
	
	/**
	 * Get all items
	 */
	public List<Item> getAllItems() {
		Iterable<Item> items = itemRepository.findAll();
		List<Item> data = new ArrayList<Item>();
		for (Item item : items) {
			data.add(item);
		}
		return data;
	}


	/**
	 * Edit user
	 */
	public User editUser(User user) {
		return userRepository.save(user);
	}
}
