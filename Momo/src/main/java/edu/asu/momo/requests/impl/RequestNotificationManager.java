package edu.asu.momo.requests.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import edu.asu.momo.core.Team;
import edu.asu.momo.core.TimeChangeRequest;
import edu.asu.momo.core.TimeRequest;
import edu.asu.momo.db.IUserManager;
import edu.asu.momo.notifications.EmailNotificationSender;
import edu.asu.momo.requests.IRequestNotificationManager;
import edu.asu.momo.teams.ITeamsManager;
import edu.asu.momo.user.User;
import edu.asu.momo.web.Constants;
import edu.asu.momo.web.HomeController;

@Service
@PropertySource("classpath:emailtext.properties")
public class RequestNotificationManager implements IRequestNotificationManager {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private EmailNotificationSender notificationSender;
	
	@Autowired
	private ITeamsManager teamsManager;
	
	@Autowired
	private IUserManager userManager;
	
	@Autowired
	private Environment env;
	
	
	/* (non-Javadoc)
	 * @see edu.asu.momo.requests.impl.IRequestNotificationManager#sendTimeChangeRequestNotificationToManager(java.lang.String, edu.asu.momo.core.TimeChangeRequest)
	 */
	@Override
	public void sendTimeChangeRequestNotificationToManager(String username, TimeChangeRequest request) {
		List<Team> teams = teamsManager.getTeamsOfUser(username);
		User user = userManager.getUserById(username);
		
		if (user == null) {
			logger.error("Couldn't send email. No user found for " + username + ".");
			return;
		}
		
		for (Team team : teams) {
			if (team.getManagers() != null) {
				for (String managerId : team.getManagers()) {
					User manager = userManager.getUserById(managerId);
					if (manager != null && manager.getEmail() != null && !manager.getEmail().trim().isEmpty()) {
						
						/*
						 * build email text
						 */
						String text = env.getProperty("time.change.request.text");
						text = text.replace(EmailConstants.MANAGER, manager.getName());
						text = text.replace(EmailConstants.USER, user.getName());
						
						/*
						 * build html
						 */
						String textHtml = env.getProperty("time.change.request.text.html");
						textHtml = textHtml.replace(EmailConstants.MANAGER, manager.getName());
						textHtml = textHtml.replace(EmailConstants.USER, user.getName());
						textHtml = textHtml.replace(EmailConstants.COMMENTS, request.getRequestNotes());
						
						/*
						 * build date
						 */
						SimpleDateFormat format = new SimpleDateFormat("EE, MMM dd, yyyy");
						text = text.replace(EmailConstants.DATE, format.format(request.getOldStartDate()));
						textHtml = textHtml.replace(EmailConstants.DATE, format.format(request.getOldStartDate()));
						
						/*
						 * build start/end
						 */
						DateFormat timeFormat = DateFormat.getTimeInstance();
						text = text.replace(EmailConstants.CHANGE_DATE_START, timeFormat.format(request.getOldStartDate()));
						text = text.replace(EmailConstants.CHANGE_DATE_END, timeFormat.format(request.getOldEndDate()));
						textHtml = textHtml.replace(EmailConstants.CHANGE_DATE_START, timeFormat.format(request.getOldStartDate()));
						textHtml = textHtml.replace(EmailConstants.CHANGE_DATE_END, timeFormat.format(request.getOldEndDate()));
						
						/*
						 * build link
						 */
						String webapp = env.getProperty("webapp_url");
						String link = webapp + Constants.VIEW_REQUEST + request.getId();
						text = text.replace(EmailConstants.LINK, link);
						textHtml = textHtml.replace(EmailConstants.LINK, link);
						
						/*
						 * build email subject
						 */
						String title = env.getProperty("time.change.request.title");
						title = title.replace(EmailConstants.USER, user.getName());
						
						notificationSender.sendNotificationEmail(manager.getEmail(), title , text, textHtml);
					}
					else {
						logger.error("Couldn't send email. No manager found for " + managerId + ".");
					}
				}
			}
		}
	}
	
	@Override
	public void sendApprovalEmail(String username, String managerId, TimeChangeRequest request) {
		User user = userManager.getUserById(username);
		User manager = userManager.getUserById(managerId);
		
		if (user == null || manager == null) {
			logger.error("Couldn't send email to " + user + " with id " + username + ". Manager is " + manager + "with id " + managerId + ".");
			return;
		}
		
		if (user.getEmail() != null && !user.getEmail().trim().isEmpty()) {
			/*
			 * build email text
			 */
			String text = env.getProperty("time.approval.text");
			text = text.replace(EmailConstants.MANAGER, manager.getName());
			text = text.replace(EmailConstants.USER, user.getName());
			
			/*
			 * build html text
			 */
			String textHtml = env.getProperty("time.approval.text.html");
			textHtml = textHtml.replace(EmailConstants.MANAGER, manager.getName());
			textHtml = textHtml.replace(EmailConstants.USER, user.getName());
			textHtml = textHtml.replace(EmailConstants.COMMENTS, request.getReviewNotes());
			
			/*
			 * build dates
			 */
			SimpleDateFormat format = new SimpleDateFormat("EE, MMM dd, yyyy");
			
			text = text.replace(EmailConstants.DATE, format.format(request.getOldStartDate()));
			textHtml = textHtml.replace(EmailConstants.DATE, format.format(request.getOldStartDate()));
			
			DateFormat timeFormat = DateFormat.getTimeInstance();
			text = text.replace(EmailConstants.CHANGE_DATE_START, timeFormat.format(request.getOldStartDate()));
			text = text.replace(EmailConstants.CHANGE_DATE_END, timeFormat.format(request.getOldEndDate()));
			textHtml = textHtml.replace(EmailConstants.CHANGE_DATE_START, timeFormat.format(request.getOldStartDate()));
			textHtml = textHtml.replace(EmailConstants.CHANGE_DATE_END, timeFormat.format(request.getOldEndDate()));
			
			/*
			 * build link
			 */
			String webapp = env.getProperty("webapp_url");
			String link = webapp + Constants.VIEW_REQUEST + request.getId();
			text = text.replace(EmailConstants.LINK, link);
			textHtml = textHtml.replace(EmailConstants.LINK, link);
			
			/*
			 * build email subject
			 */
			String title = env.getProperty("time.approval.title");
			
			notificationSender.sendNotificationEmail(user.getEmail(), title , text, textHtml);
		}		
	}
	
	@Override
	public void sendRejectionEmail(String username, String managerId, TimeChangeRequest request) {
		User user = userManager.getUserById(username);
		User manager = userManager.getUserById(managerId);
		
		if (user == null || manager == null) {
			logger.error("Couldn't send email to " + user + " with id " + username + ". Manager is " + manager + "with id " + managerId + ".");
			return;
		}
		
		if (user.getEmail() != null && !user.getEmail().trim().isEmpty()) {
			/*
			 * build email text
			 */
			String text = env.getProperty("time.rejection.text");
			text = text.replace(EmailConstants.MANAGER, manager.getName());
			text = text.replace(EmailConstants.USER, user.getName());
			
			/*
			 * build email html
			 */
			String textHtml = env.getProperty("time.rejection.text.html");
			textHtml = textHtml.replace(EmailConstants.MANAGER, manager.getName());
			textHtml = textHtml.replace(EmailConstants.USER, user.getName());
			textHtml = textHtml.replace(EmailConstants.COMMENTS, request.getReviewNotes());
			
			/*
			 * build date
			 */
			SimpleDateFormat format = new SimpleDateFormat("EE, MMM dd, yyyy");
			text = text.replace(EmailConstants.DATE, format.format(request.getOldStartDate()));
			textHtml = textHtml.replace(EmailConstants.DATE, format.format(request.getOldStartDate()));
			
			/*
			 * build start/end
			 */
			DateFormat timeFormat = DateFormat.getTimeInstance();
			text = text.replace(EmailConstants.CHANGE_DATE_START, timeFormat.format(request.getOldStartDate()));
			text = text.replace(EmailConstants.CHANGE_DATE_END, timeFormat.format(request.getOldEndDate()));
			textHtml = textHtml.replace(EmailConstants.CHANGE_DATE_START, timeFormat.format(request.getOldStartDate()));
			textHtml = textHtml.replace(EmailConstants.CHANGE_DATE_END, timeFormat.format(request.getOldEndDate()));
			
			/*
			 * build link
			 */
			String webapp = env.getProperty("webapp_url");
			String link = webapp + Constants.VIEW_REQUEST + request.getId();
			text = text.replace(EmailConstants.LINK, link);
			textHtml = textHtml.replace(EmailConstants.LINK, link);
			
			/*
			 * build email subject
			 */
			String title = env.getProperty("time.rejection.title");
			
			notificationSender.sendNotificationEmail(user.getEmail(), title , text, textHtml);
		}
	}
}
