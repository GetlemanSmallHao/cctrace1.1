package com.cctrace.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cctrace.image.VerifyCode;

public class VerifyCodeServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		VerifyCode vc = new VerifyCode();
		BufferedImage image = vc.getImage();
		String text = vc.getText();
		HttpSession session = request.getSession();
		System.out.println("验证码是"+text);
		session.setAttribute("verifyCode", text);
		System.out.println("sessionScope中的是"+request.getSession().getAttribute("verifyCode"));
		vc.output(image, response.getOutputStream());
	}

}
