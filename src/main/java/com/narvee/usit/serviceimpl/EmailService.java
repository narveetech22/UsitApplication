package com.narvee.usit.serviceimpl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.narvee.usit.entity.Email;
import com.narvee.usit.entity.RecInterviews;
import com.narvee.usit.entity.ReqSubmission;
import com.narvee.usit.entity.Requirements;
import com.narvee.usit.entity.Users;
import com.narvee.usit.repository.IUsersRepository;

@Configuration
public class EmailService {
	// commented for mail error

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private IUsersRepository userRepo;

	public void sendMailWithInlineResources(String to, String subject, String fileToAttach) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
				mimeMessage.setFrom(new InternetAddress("saikiran@narveetech.com"));
				mimeMessage.setSubject(subject);
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
				String ar = null;
				if (fileToAttach.equals("Resetpassword")) {
					ar = "Please click below link to change the reset the password </br></br><a href='http://localhost:4200/reset-password'>Click here </a>";
				} else {
					ar = "This is your new password = >" + fileToAttach;
				}
				// String resetPasswordLink = Utility.getSiteURL(request) +
				// "/reset_password?token=" + token;
				// sendEmail(email, resetPasswordLink);
				helper.setText(ar, true);
				System.out.println(ar);
//	            FileSystemResource res = new FileSystemResource(new File(fileToAttach));
//	            helper.addInline("identifier1234", res);
			}
		};
		try {
			mailSender.send(preparator);
		} catch (MailException ex) {
			// simply log it and go on...
			System.err.println(ex.getMessage());
		}
	}

	public void mailsender(Email email) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				mimeMessage.setRecipient(Message.RecipientType.TO,
						new InternetAddress("saikiranbsoftworldusa@gmail.com"));
				mimeMessage.setFrom(new InternetAddress("saikiran@narveetech.com"));
				mimeMessage.setSubject(email.getSubject());
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

				String ar = null;
//	            if(fileToAttach.equals("Resetpassword")) {
//	             ar = "Please click below link to change the reset the password </br></br><a href='http://localhost:4200/reset-password'>Click here </a>";
//	            }
//	            else {
//	            ar = "This is your new password = >"+fileToAttach;
//	            }
				// String resetPasswordLink = Utility.getSiteURL(request) +
				// "/reset_password?token=" + token;
				// sendEmail(email, resetPasswordLink);
				helper.setText(email.getBody(), true);
				// mimeMessage.setContent(email.getBody(), "multipart/mixed");
				System.out.println(ar);
				MimeBodyPart messageBodyPart = new MimeBodyPart();
				// System.out.println("E:\\stores\\"+email.getAttachment()+"
				// ===================");
				if (email.getAttachment() != "" || email.getAttachment() != null || !email.getAttachment().isBlank()
						|| !email.getAttachment().isEmpty()) {
					// File att = new File(new File("E:\\stores\\"), email.getAttachment());
					// messageBodyPart.attachFile(att);
					///

					try {
						String content = new String(
								Files.readAllBytes(Paths.get("e:\\stores\\" + email.getAttachment())));
						System.out.println(content + " ==========");
						FileSystemResource resource = new FileSystemResource(new File(content));
						// helper.addInline("image001", resource);
						helper.addAttachment(content, resource);
					} catch (Exception e) {
						System.out.print("No file exists");
					}

					// System.out.println("E:\\stores\\"+email.getAttachment()+"
					// ===================");
					// FileSystemResource res = new FileSystemResource(new
					// File("E:\\stores\\"+email.getAttachment()));
					// helper.addInline("identifier1234", res);
				}

//	            FileSystemResource res = new FileSystemResource(new File(fileToAttach));
//	            helper.addInline("identifier1234", res);
			}

		};
		try {
			mailSender.send(preparator);
		} catch (MailException ex) {
			// simply log it and go on...
			System.err.println(ex.getMessage());

		}
	}

	// submisiion entry mail
	public void sendEmail(ReqSubmission submission) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		Users user = userRepo.findById(submission.getSubmittedby().getUserid()).get();
		Users user2 = userRepo.findById(submission.getUsers().getUserid()).get();
		String bcc = user2.getEmail();
		message.addRecipient(RecipientType.BCC, new InternetAddress(bcc));
		message.addRecipient(RecipientType.CC, new InternetAddress("saikiran@narveetech.com"));
		String recipientEmail = user.getEmail();
		System.out.println(recipientEmail + " ppppppppppppppppppp");
		helper.setFrom("saikiran@narveetech.com", "Submission followUp mail");
		helper.setTo(recipientEmail);

		String subject = "Here's the submission status";
		String name = submission.getSubmittedby().getFullname();

		String content = "<p>Dear " + name + ",</p>  <p> You have requested to reset your password.</p>"
				+ "<p>Click the link below to change your password:</p>" + "<p><a href=\"" + submission
				+ "\">Change my password</a></p>" + "<br>" + "<p>Ignore this email if you do remember your password, "
				+ "or you have not made the request.</p>";

		helper.setSubject(subject);

		helper.setText(content, true);

		mailSender.send(message);
	}

	// interview entry mail
	public void sendEmail2(RecInterviews interviews) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		Users user = userRepo.findById(interviews.getUsers().getUserid()).get();
		// Users user2 = userRepo.findById(interviews.getUsers().getUserid()).get();
		// String bcc = user2.getEmail();
		// message.addRecipient(RecipientType.BCC, new InternetAddress(bcc));
		message.addRecipient(RecipientType.CC, new InternetAddress("saikiran@narveetech.com"));
		String recipientEmail = user.getEmail();
		System.out.println(recipientEmail + " ppppppppppppppppppp");
		helper.setFrom("saikiran@narveetech.com", "Interview Schedule mail");
		helper.setTo(recipientEmail);

		String subject = "Interview Schedule mail";
		String name = "Ream";

		String content = "<p>Dear " + name + ",</p>  <p> You have requested to reset your password.</p>"
				+ "<p>Click the link below to change your password:</p>" + "<p><a href=\"" + interviews
				+ "\">Change my password</a></p>" + "<br>" + "<p>Ignore this email if you do remember your password, "
				+ "or you have not made the request.</p>";

		helper.setSubject(subject);

		helper.setText(content, true);

		mailSender.send(message);

	}

	public void sendSubStatusToTeam333(String to, String subject, String fileToAttach) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
				mimeMessage.setFrom(new InternetAddress("saikiran@narveetech.com"));
				mimeMessage.setSubject(subject);
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
				String ar = null;
				if (fileToAttach.equals("Resetpassword")) {
					ar = "Please click below link to change the reset the password </br></br><a href='http://localhost:4200/reset-password'>Click here </a>";
				} else {
					ar = "This is your new password = >" + fileToAttach;
				}
				// String resetPasswordLink = Utility.getSiteURL(request) +
				// "/reset_password?token=" + token;
				// sendEmail(email, resetPasswordLink);
				helper.setText(ar, true);
				System.out.println(ar);
//	            FileSystemResource res = new FileSystemResource(new File(fileToAttach));
//	            helper.addInline("identifier1234", res);
			}
		};
		try {
			mailSender.send(preparator);
		} catch (MailException ex) {
			// simply log it and go on...
			System.err.println(ex.getMessage());
		}
	}

	// Recruiting mails
	// Send mail regarding requirements
	public void requirementMail(Requirements requirements) throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		Users user2 = userRepo.findById(requirements.getUpdatedby()).get();
		String bcc = user2.getEmail();
		message.addRecipient(RecipientType.BCC, new InternetAddress(bcc));

		message.addRecipient(RecipientType.CC, new InternetAddress("saikiran@narveetech.com"));
		Users user = userRepo.findById(requirements.getUsers().getUserid()).get();
		String name = user.getFullname();
		String recipientEmail = user.getEmail();
		helper.setFrom("saikiran@narveetech.com", "Requirements followUp mail");
		helper.setTo(recipientEmail);
		String subject = "Here's the Requirements Details";
		String content = "<p>Dear " + name + ",</p>  <p> You have requested to reset your password.</p>"
				+ "<p>Click the link below to change your password:</p>" + "<p><a href=\"" + recipientEmail
				+ "\">Change my password</a></p>" + "<br>" + "<p>Ignore this email if you do remember your password, "
				+ "or you have not made the request.</p>";

		helper.setSubject(subject);

		helper.setText(content, true);

		mailSender.send(message);
		System.out.println("Requirement mail ");
	}

}
