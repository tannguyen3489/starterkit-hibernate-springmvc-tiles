package com.example.starter.dao.impl;

import com.example.starter.dao.UserDao;
import com.example.starter.entity.App;
import com.example.starter.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A helper class, used to populate the embedded H2 database with test data at startup.  Because of the 
 * Servlet 3.0 @WebListener annotation, the "contextInitialized" method will be invoked automatically when 
 * the servlet container launches.
 */
@Repository
public class AppDao implements UserDao, InitializingBean {
	
	Logger logger = LoggerFactory.getLogger(AppDao.class);

	@Autowired
	private SessionFactory sessionFactory;
	/**
	 * This method is invoked automatically when the servlet engine first starts.
	 */
	public void test1() {
		Session session = sessionFactory.getCurrentSession();

		App app1 = new App("The Cloud", "cloud.jpg", "The Cloud is a place of magic and wonder.  Businesses run smoothly in the Cloud.  Developers no longer need system administrators, or food and water for that matter.  You can watch television on your tablet device from the comfort of your own sofa, without having to look up at the television.  Download the Cloud app, from the Cloud, and harness this awesome power today!");
		session.save(app1);
		logger.info("Persisting " + app1.getName());

		App app2 = new App("Sales Closer", "pointing.jpg", "A high-powered productivity app for high-powered sales professionals.  Track your high-powered leads, and manage your high-powered schedule.  When you are out on the town doing high-powered networking, you want to show your high-powered sales prospects that you are high-powered too.");
		session.save(app2);
		logger.info("Persisting " + app2.getName());

		App app3 = new App("World Tournament Football", "ball.jpg", "This game app offers all the excitement of football (soccer), except that it's played on a touch screen rather than your feet.  So there isn't any of the kicking, or the running, or any of the physical exercise at all.  Other than that, it's pretty much the same.");
		session.save(app3);
		logger.info("Persisting " + app3.getName());
		
		App app4 = new App("Yet Another Crystal Game", "brilliant.jpg", "A dazzling game app, in which you connect crystals of the same color to make them go away.  It's sort of like Tetris.  Actually, it's sort of like the other dozen or so other games today where you connect crystals of the same color.");
		session.save(app4);
		logger.info("Persisting " + app4.getName());
		
		App app5 = new App("Pencil Sharpener", "pencil.jpg", "Sharpen your pencils, by sticking them into your phone's Bluetooth plug and pushing a button.  This app really pushes your phone's hardware to its limits!");
		session.save(app5);
		logger.info("Persisting " + app5.getName());
		
		App app6 = new App("Stapler Tracker", "stapler.jpg", "Is someone always taking your stapler?  It's a common problem in many office spaces.  This business productivity app will help you manage your stapler at all times, so that you will never have to deal with a \"case of the Mondays\" again.");
		session.save(app6);
		logger.info("Persisting " + app6.getName());
		
		App app7 = new App("Frustrated Flamingos", "flamingo.jpg", "A fun little game app, where you throw large birds around for no apparent reason.  Why else do you think they're so frustrated?");
		session.save(app7);
		logger.info("Persisting " + app7.getName());
		
		App app8 = new App("Grype Video Conferencing", "laptop.jpg", "Make free local and international calls, with video, using this app and your home Internet connection.  Better yet, make free calls using your employer's Internet connection!");
		session.save(app8);
		logger.info("Persisting " + app8.getName());
		
		App app9 = new App("E-Book Reader", "book.jpg", "Read books on your computer, or on the go from your mobile device with this powerful e-reader app.  We recommend \"Hibernate Search by Example\", from Packt Publishing.");
		session.save(app9);
		logger.info("Persisting " + app9.getName());
		
		App app10 = new App("Dome Web Browser", "orangeswirls.jpg", "This amazing app allows us to track all of your online activity.  We can figure out where you live, what you had for breakfast this morning, or what your closest secrets are.  The app also includes a web browser.");
		session.save(app10);
		logger.info("Persisting " + app10.getName());
		
		App app11 = new App("Athena Internet Radio", "jamming.jpg", "Listen to your favorite songs on streaming Internet radio!  When you like a song, this app will play more songs similar to that one.  Or at least it plays more songs... to be honest, sometimes they're not all that similar.  :(");
		session.save(app11);
		logger.info("Persisting " + app11.getName());
		
		App app12 = new App("Map Journey", "compass.jpg", "Do you need directions to help you reach a destination?  This GPS app will definitely produce enough turn-by-turn directions to get you there!  Eventually.");
		session.save(app12);
		logger.info("Persisting " + app12.getName());
		
		session.getTransaction().commit();
		
		//
		// Close and cleanup the Hibernate session
		//
		session.close();
	}

	public void afterPropertiesSet() throws Exception {

	}

	public User findUserByUsername(String username) {
		return null;
	}

	public List<App> findUserById(long userId) {
		return null;
	}

	public List<App> searchByLucene(String queryKey) throws InterruptedException {
		return null;
	}
}
