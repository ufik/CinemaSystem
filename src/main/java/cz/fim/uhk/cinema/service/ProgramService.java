package cz.fim.uhk.cinema.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cz.fim.uhk.cinema.dao.ProgramDAO;
import cz.fim.uhk.cinema.entity.Program;


@Service
public class ProgramService {

	@Autowired
	private ProgramDAO programDAO;
	
	@Transactional
	public List<Program> list() {		
		return programDAO.listProgram();
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

}
