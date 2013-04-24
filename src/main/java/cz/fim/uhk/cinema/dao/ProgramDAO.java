package cz.fim.uhk.cinema.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import cz.fim.uhk.cinema.form.Program;

@Repository
public class ProgramDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addProgram(Program program) {
		sessionFactory.getCurrentSession().save(program);
	}

	public List<Program> listProgram() {

		return sessionFactory.getCurrentSession().createQuery("from Program")
				.list();
	}

	public void removeProgram(Integer id) {
		Program p = (Program) sessionFactory.getCurrentSession().load(
				Program.class, id);
		if (null != p) {
			sessionFactory.getCurrentSession().delete(p);
		}

	}
}
