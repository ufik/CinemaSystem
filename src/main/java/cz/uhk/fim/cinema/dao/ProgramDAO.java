package cz.uhk.fim.cinema.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cz.uhk.fim.cinema.entity.Program;

@Repository
public class ProgramDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addProgram(Program program) {
		sessionFactory.getCurrentSession().saveOrUpdate(program);
	}
	
	public List<Program> listProgram() {
		
		return sessionFactory.getCurrentSession().createQuery("FROM Program ORDER BY date DESC")
				.list();
	}
	
	public List<Program> listProgram(Date from, Date to) {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Program.class);
        
		criteria.add(Restrictions.ge("date", from)); 
		criteria.add(Restrictions.lt("date", to));
		
		criteria.addOrder( Order.asc("date") );
		
		return criteria.list();
	}
	
	public List<Program> listActualProgram() {

		return sessionFactory.getCurrentSession().createQuery("FROM Program WHERE date >= NOW() ORDER BY date ASC")
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
