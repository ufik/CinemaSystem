package cz.fim.uhk.cinema.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cz.fim.uhk.cinema.entity.Program;

@Repository
public class ProgramDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addProgram(Program program) {
		sessionFactory.getCurrentSession().saveOrUpdate(program);
	}

	public List<Program> listProgram() {

		return sessionFactory.getCurrentSession().createQuery("from Program")
				.list();
	}
	
	public Program getProgram(Integer programId) {

		List<Program> l = sessionFactory.getCurrentSession().createQuery("from Program WHERE id_program = " + programId).list();
		return l.get(0);
	}

	public void removeProgram(Integer id) {
		Program p = (Program) sessionFactory.getCurrentSession().load(
				Program.class, id);
		if (null != p) {
			sessionFactory.getCurrentSession().delete(p);
		}

	}
}
