package com.eco.util;

import java.io.UnsupportedEncodingException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.eco.model.ProductOrder;
import com.eco.model.User;
import com.eco.service.UserService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class CommonUtil {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	UserService userService;

	
	//Forgot password(send mail)
	public Boolean sendMail(String url, String reciepentEmail) throws UnsupportedEncodingException, MessagingException {

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("raunakkr.saha@gmail.com", "Eco Cart");
		helper.setTo(reciepentEmail);

		String content = "<p>Hello,</p>" + "<p>You have requested to reset your password.</p>"
				+ "<p>Click the link below to change your password:</p>" + "<p><a href=\"" + url
				+ "\">Change my password</a></p>";
		helper.setSubject("Password Reset");
		helper.setText(content, true);
		mailSender.send(message);
		return true;
	}

	public static String generateUrl(HttpServletRequest request) {

		// http://localhost:8080/forgot-password
		String siteUrl = request.getRequestURL().toString();

		return siteUrl.replace(request.getServletPath(), "");

	}

	String msg=null;

	//Order status message
	private String OrderStatusMessage(String status) {
		 msg = "<p>Hello [[name]],</p>";

		if (OrderStatus.IN_PROGRESS.getName().equals(status)) {
			msg += "<p>We're working on your order. It's currently <b>In Progress</b>.</p>"
					+ "<p>We'll notify you once it's ready for the next step.</p>";
		} else if (OrderStatus.ORDER_RECIVED.getName().equals(status)) {
			msg += "<p>Your order has been successfully <b>Received</b>.</p>"
					+ "<p>We're getting it ready for you. Stay tuned for further updates!</p>";
		} else if (OrderStatus.PRODUCT_PACKED.getName().equals(status)) {
			msg += "<p>Your order has been <b>Packed</b> and is ready to be shipped!</p>"
					+ "<p>We’ll let you know once it’s on the way.</p>";
		} else if (OrderStatus.OUT_FOR_DELIVERY.getName().equals(status)) {
			msg += "<p>Good news! Your order is <b>Out for Delivery</b>.</p>"
					+ "<p>It will reach you soon. Please keep an eye out for the delivery.</p>";
		} else if (OrderStatus.DELIVERED.getName().equals(status)) {
			msg += "<p>Your order has been successfully <b>Delivered</b>.</p>"
					+ "<p>We hope you enjoy your purchase!</p>";
		} else if (OrderStatus.CANCEL.getName().equals(status)) {
			msg += "<p>Unfortunately, your order has been <b>Cancelled</b>.</p>"
					+ "<p>If you didn’t request this, please contact our support team.</p>";
		} else if (OrderStatus.SUCCESS.getName().equals(status)) {
			msg += "<p>Your order has been successfully <b>Placed</b>!</p>"
					+ "<p>We’ll keep you updated on the status of your order.</p>";
		} else {
			msg += "<p>Unknown order status. Please contact support for more information.</p>";
		}
		
		msg += "</p>" +"<p><b>Product Details:</b></p>" + "<p>Name :  [[productName]] </p>" + "<p>Category : [[category]] </p>"
				+ "<p>Quantity : [[quantity]] </p>" + "<p>Price : [[price]] </p>"
				+ "<p>Payment Type : [[paymentType]] </p>";
		return msg;
	}

	//Send mail(Order status)

	public Boolean sendMailForProductOrder(ProductOrder order, String status) throws Exception {

//		msg="<p>Hello [[name]],</p>"
//				+ "<p>Your order is <b>[[orderStatus]]</b>.</p>"
//				+ "<p><b>Product Details:</b></p>"
//				+ "<p>Name : [[productName]]</p>"
//				+ "<p>Category : [[category]]</p>"
//				+ "<p>Quantity : [[quantity]]</p>"
//				+ "<p>Price : [[price]]</p>"
//				+ "<p>Payment Type : [[paymentType]]</p>";
		  msg = OrderStatusMessage(status);


		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("raunakkr.saha@gmail.com", "Eco Cart");
		helper.setTo(order.getOrderAddress().getEmail());

		msg = msg.replace("[[name]]", order.getOrderAddress().getFirstName());
		msg = msg.replace("[[orderStatus]]", status);
		msg = msg.replace("[[productName]]", order.getProduct().getTitle());
		msg = msg.replace("[[category]]", order.getProduct().getCategory());
		msg = msg.replace("[[quantity]]", order.getQuantity().toString());
		msg = msg.replace("[[price]]", order.getPrice().toString());
		msg = msg.replace("[[paymentType]]", order.getPaymentType());

		helper.setSubject("Product Order Status");
		helper.setText(msg, true);
		mailSender.send(message);
		return true;
	}
	
	
	public User getLoggedInUserDetails(Principal p) {
		String email = p.getName();
		User userDtls = userService.getUserByEmail(email);
		return userDtls;
	}

}
