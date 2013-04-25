package cz.fim.uhk.cinema.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cz.fim.uhk.cinema.dao.HallDAO;
import cz.fim.uhk.cinema.form.Hall;

@Service
public class HallService {

	@Autowired
	private HallDAO hallDAO;
	
	@Transactional
	public List<Hall> list() {		
		return hallDAO.listHall();
	}
	
	@Transactional
	public void addHall(Hall hall) {
		hallDAO.addHall(hall);
	}
	
	@Transactional
	public void updateHall(Hall hall) {
		hallDAO.updateHall(hall);
	}
	
	@Transactional
	public void removeHall(Integer id) {
		hallDAO.removeHall(id);
	}

	@Transactional
	public Object getHall(Integer hallId) {
		return hallDAO.getHall(hallId);
	}

	public Hall getHallByName(String text) {
		return hallDAO.getHall(text);
	}

}