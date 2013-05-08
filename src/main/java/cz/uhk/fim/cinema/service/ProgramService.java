package cz.uhk.fim.cinema.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.fim.cinema.dao.ProgramDAO;
import cz.uhk.fim.cinema.entity.Program;

@Service
public class ProgramService {

	@Autowired
	private ProgramDAO programDAO;
	
	@Transactional
	public List<Program> list() {		
		return programDAO.listProgram();
	}
	
	@Transactional
	public List<Program> list(Date from, Date to) {		
		return programDAO.listProgram(from, to);
	}
	
	@Transactional
	public void addProgram(Program program) {
		programDAO.addProgram(program);
	}
	
	@Transactional
	public void removeProgram(Integer id) {
		programDAO.removeProgram(id);
	}

	@Transactional
	public Program getProgram(Integer id) {
		return programDAO.getProgram(id);
	}

	@Transactional
	public List<Program> actualList() {
		return programDAO.listActualProgram();
	}

}
