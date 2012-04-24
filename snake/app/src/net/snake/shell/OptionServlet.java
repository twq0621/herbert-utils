package net.snake.shell;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OptionServlet extends HttpServlet {

	private static final long serialVersionUID = 968367344317488953L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String op1 = req.getParameter("1");
		if (op1 != null && !op1.equals("")) {
			op1 = op1.trim();
			if (op1.equals("true")) {
				Options.EnableGMCmd = true;
			} else if (op1.equals("false")) {
				Options.EnableGMCmd = false;
			}
		}
		String op2 = req.getParameter("2");
		if (op2 != null && !op2.equals("")) {
			op2 = op2.trim();
			if (op2.equals("true")) {
				Options.ProcessorPerformance = true;
			} else if (op2.equals("false")) {
				Options.ProcessorPerformance = false;
			}
		}
		String op3 = req.getParameter("3");
		if (op3 != null && !op3.equals("")) {
			op3 = op3.trim();
			if (op3.equals("true")) {
				Options.IsCrossServ = true;
			} else if (op3.equals("false")) {
				Options.IsCrossServ = false;
			}
		}
		String op4 = req.getParameter("4");
		if (op4 != null && !op4.equals("")) {
			op4 = op4.trim();
			boolean numb = op4.matches("\\d+");
			if (numb) {
				Options.ServerId = Integer.parseInt(op4);
			}
		}
		String op5 = req.getParameter("5");
		if (op5 != null && !op5.equals("")) {
			op5 = op5.trim();
			if (op5.equals("true")) {
				Options.FresherCard_Check = true;
			} else if (op5.equals("false")) {
				Options.FresherCard_Check = false;
			}
		}
		String op6 = req.getParameter("6");
		if (op6 != null && !op6.equals("")) {
			op6 = op6.trim();
			if (op6.equals("true")) {
				Options.AntiAddicted = true;
			} else if (op6.equals("false")) {
				Options.AntiAddicted = false;
			}
		}
		String op7 = req.getParameter("7");
		if (op7 != null && !op7.equals("")) {
			op7 = op7.trim();
			boolean numb = op7.matches("\\d+");
			if (numb) {
				Options.Fresher_Familytown = Integer.parseInt(op4);
			}
		}
		String op8 = req.getParameter("8");
		if (op8 != null && !op8.equals("")) {
			op8 = op8.trim();
			boolean numb = op8.matches("\\d+");
			if (numb) {
				Options.Shock_Timeout = Integer.parseInt(op4);
			}
		}
		String op9 = req.getParameter("9");
		if (op9 != null && !op9.equals("")) {
			op9 = op9.trim();
			boolean numb = op9.matches("\\d+");
			if (numb) {
				Options.Shock_Timeout_Monster = Integer.parseInt(op4);
			}
		}
		String op10 = req.getParameter("10");
		if (op10 != null && !op10.equals("")) {
			op10 = op10.trim();
			boolean numb = op10.matches("\\d+");
			if (numb) {
				Options.Shock_AttackProb = Integer.parseInt(op4);
			}
		}
		String op11 = req.getParameter("11");
		if (op11 != null && !op11.equals("")) {
			op11 = op11.trim();
			boolean numb = op11.matches("\\d+");
			if (numb) {
				Options.Relive_Timeout = Integer.parseInt(op4);
			}
		}
		String op12 = req.getParameter("12");
		if (op12 != null && !op12.equals("")) {
			op12 = op12.trim();
			boolean numb = op12.matches("\\d+");
			if (numb) {
				Options.Msg_Heart = Integer.parseInt(op4);
			}
		}
		String op13 = req.getParameter("13");
		if (op13 != null && !op13.equals("")) {
			op13 = op13.trim();
			boolean numb = op13.matches("\\d+");
			if (numb) {
				Options.MAX_SP = Integer.parseInt(op4);
			}
		}
		String op14 = req.getParameter("14");
		if (op14 != null && !op14.equals("")) {
			op14 = op14.trim();
			boolean numb = op14.matches("\\d+");
			if (numb) {
				Options.KillGradeLimit = Integer.parseInt(op4);
			}
		}
		String op15 = req.getParameter("15");
		if (op15 != null && !op15.equals("")) {
			op15 = op15.trim();
			boolean numb = op15.matches("\\d+");
			if (numb) {
				Options.JUMP_DISTANCE = Integer.parseInt(op4);
			}
		}

	}

}
