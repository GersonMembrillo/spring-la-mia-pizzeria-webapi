package org.java.app.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServ {

	@Autowired
	private RoleRepo roleRepo;
	
	public List<Role> findAll() {
		
		return roleRepo.findAll();
	}
	public Role findById(int id) {
		
		return roleRepo.findById(id).get();
	}
	public void save(Role role) {
		
		roleRepo.save(role);
	}
}