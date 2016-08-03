package br.com.igoraraujo91.voteNoRestaurante.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.igoraraujo91.voteNoRestaurante.dao.DAO;
import br.com.igoraraujo91.voteNoRestaurante.models.Restaurant;
import br.com.igoraraujo91.voteNoRestaurante.models.Vote;

@RestController
@RequestMapping("/contest")
public class ContestController {
	
	@Autowired
	private DAO<Restaurant> restaurantsDAO;	
	
	@Autowired
	private DAO<Vote> votesDAO;

	@RequestMapping(value = "/participants", method = RequestMethod.GET)
	public List<Restaurant> getParticipants(){		
		return this.restaurantsDAO.readAll();
	}
	
	@RequestMapping(value = "/vote", method = RequestMethod.PUT)
	public ResponseEntity<String> putVote(@RequestBody() Vote userVote){
		
		try{
			this.votesDAO.create(userVote);
		}catch(Exception e){
			System.out.println(e.getMessage());
			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		}
		
		for(Restaurant r : userVote.getUserVotes()){
			Restaurant curr = this.restaurantsDAO.readOne(r.getName());
			curr.setVotes(curr.getVotes() + r.getVotes());
			this.restaurantsDAO.update(curr);	
		}
		
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

}
