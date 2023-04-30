package com.bealmont.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bealmont.workshopmongo.domain.User;
import com.bealmont.workshopmongo.dto.UserDTO;
import com.bealmont.workshopmongo.repository.UserRepository;
import com.bealmont.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public List<User> findAll() {
		return repo.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User obj) {
	    Optional<User> newObj = repo.findById(obj.getId());
	    updateData(newObj,obj);
	    return repo.save(obj);
	}
	 
	private void updateData(Optional<User> newObj, User obj) {
	    newObj.get().setName(obj.getName());
	    newObj.get().setEmail(obj.getEmail());
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
