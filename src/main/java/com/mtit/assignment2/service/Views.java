package com.mtit.assignment2.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mtit.assignment2.models.Item;
import com.mtit.assignment2.models.User;
import com.mtit.assignment2.repository.ItemRepository;
import com.mtit.assignment2.repository.UserRepository;
import com.mtit.assignment2.session.CurrentLogin;

@Controller
public class Views {

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private UserRepository userRepository;

	static final Logger LOGGER = LogManager.getLogger(Views.class.getName());

	@RequestMapping("/")
	public String register() {
		CurrentLogin.logout();
		return "login";
	}

	@RequestMapping(value = "/authlogin", method = RequestMethod.POST)
	public ModelAndView authLogin(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		System.out.println("AUTHENTICATING USER -> " + username);
		User user = this.getUser(username, password);
		if (user != null) {
			CurrentLogin.createSession(user);
			return new ModelAndView("redirect:/home");
		} else {
			return new ModelAndView("redirect:/");
		}
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request,
			@RequestParam("username") String username,
			@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("type") String type,
			@RequestParam("password") String password
			) {
		
		LOGGER.info("CREATING NEW USER -> " + username);
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte byteData[] = md.digest();

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			String md5 = sb.toString();
			
			User user = new User();
			user.setUsername(username);
			user.setName(name);
			user.setEmail(email);
			user.setType(type);
			user.setPassword(md5);
			
			userRepository.save(user);
			
			return new ModelAndView("redirect:/users");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView("redirect:/users");
		}
	}

	@RequestMapping("/home")
	public String home() {
		if (CurrentLogin.checkSession()) {
			return "home";
		} else {
			return "login";
		}
	}
	
	@RequestMapping("/admin")
	public String admin() {
		if (CurrentLogin.checkSession()) {
			if(CurrentLogin.isAdmin()) {
				return "users";
			} else {
				return "login";
			}
		} else {
			return "login";
		}
	}
	
	@RequestMapping("/items")
	public String items() {
		if (CurrentLogin.checkSession()) {
			return "items";
		} else {
			return "login";
		}
	}


	@RequestMapping("/users")
	public String users() {
		if (CurrentLogin.checkSession()) {
			if(CurrentLogin.isAdmin()) {
				return "users";
			} else {
				return "login";
			}
		} else {
			return "login";
		}
	}

	/**
	 * get user
	 * 
	 * @return
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
			Iterable<User> users = userRepository.findAll();
			for (User user : users) {
				if (user.getUsername().equals(username) && user.getPassword().equals(md5)) {
					return user;
				}
			}
			return null;
		}
		
		
	}
	
	@RequestMapping("/intialize")
	public ModelAndView intialize(){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			String pwdDefault = "admin";
			md.update(pwdDefault.getBytes());
			byte passByte[] = md.digest();

			StringBuffer stringbuffer = new StringBuffer();
			for (int i = 0; i < passByte.length; i++) {
				stringbuffer.append(Integer.toString((passByte[i] & 0xff) + 0x100, 16).substring(1));
			}
			String md5String = stringbuffer.toString();
			User user = new User();
			user.setName("Admin");
			user.setEmail("admin@mtit.com");
			user.setUsername("admin");
			user.setType("admin");
			user.setPassword(md5String);
			userRepository.save(user);
			
			
			Item item1 = new Item();
			item1.setName("Sherlock Holmes");
			item1.setDescription("A british television movie series");
			item1.setType("tv");
			item1.setPrice(500.0);
			itemRepository.save(item1);
			
			Item item2 = new Item();
			item2.setName("Fast and Furious");
			item2.setDescription("An action movie series");
			item2.setType("movie");
			item2.setPrice(400.0);
			itemRepository.save(item2);
			
			Item item3 = new Item();
			item3.setName("Breaking Bad");
			item3.setDescription("A tv series about a cancer patient");
			item3.setType("tv");
			item3.setPrice(450.0);
			itemRepository.save(item3);
			
			Item item4 = new Item();
			item4.setName("Arrow");
			item4.setDescription("A lame superhero tv series");
			item4.setType("tv");
			item4.setPrice(200.0);
			itemRepository.save(item4);
			
			
			return new ModelAndView("redirect:/");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView("redirect:/");
		}
		
		
	}
}
