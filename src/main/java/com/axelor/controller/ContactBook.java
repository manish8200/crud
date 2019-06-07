package com.axelor.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.axelor.domains.ContactDetails;
import com.axelor.service.ContactServiceImpl;

/**
 * Servlet implementation class ContactBook
 */

@WebServlet("/contactbook")
public class ContactBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ContactServiceImpl contactService = new ContactServiceImpl();
	private static String INSERT_OR_EDIT = "/index.jsp";
	private static String LIST_USER = "/contactlist.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContactBook() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String forward = "";
		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("listContact")) {
			forward = LIST_USER;
			request.setAttribute("list", contactService.getAllcontacts());
		} else if (action.equalsIgnoreCase("delete")) {

			String id = request.getParameter("id");
			int cid = Integer.parseInt(id);
			contactService.deleteContact(cid);
			forward = LIST_USER;
			request.setAttribute("list", contactService.getAllcontacts());
		} else if (action.equalsIgnoreCase("edit")) {
			forward = INSERT_OR_EDIT;
			String id = request.getParameter("id");
			int cid = Integer.parseInt(id);
			ContactDetails contactDetails = contactService.getContactDetailsById(cid);
			request.setAttribute("ContactDetailObj", contactDetails);

		} else {

			forward = INSERT_OR_EDIT;

		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		ContactDetails cd=new ContactDetails();
		
		String fullname = request.getParameter("fullname");
		String mobileno = request.getParameter("mobileno");

		String id = request.getParameter("contactid");

		int list = contactService.CheckContact(fullname);

		

		System.out.println("count of list is " + cd.getFullName());
		System.out.println("count of list is " + cd.getFullName());
	 if (id == null || id.isEmpty()){
			if (list < 1){
				contactService.addContact(fullname, mobileno);
			} else {

				String error = fullname + "Duplicates name";
				RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT);
				request.setAttribute("name", error);
				view.forward(request, response);
				
				
			}
		} else if(id != null) {
			
			// System.out.println("Name Duplicates");
			if (fullname == cd.getFullName()){
				int cid = Integer.parseInt(id);
				contactService.updateContact(cid, fullname, mobileno);

			} else if(list < 1) {
				
				int cid = Integer.parseInt(id);
				contactService.updateContact(cid, fullname, mobileno);
			}
			else {
				String error = fullname + "Duplicates name";
				RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT);
				request.setAttribute("name", error);
				view.forward(request, response);
			}
		}
		

		RequestDispatcher view = request.getRequestDispatcher(LIST_USER);
		request.setAttribute("list", contactService.getAllcontacts());

		view.forward(request, response);

	}

}