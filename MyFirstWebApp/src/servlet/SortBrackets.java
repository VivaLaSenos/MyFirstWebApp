package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дмитрий on 28.02.2016.
 */
public class SortBrackets extends HttpServlet {

    List<String> list = new ArrayList<>();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintStream out = new PrintStream(response.getOutputStream());
        String aStr = request.getParameter("count");
        try {
            int count = Integer.parseInt(aStr);
            out.println("Количество пар скобок равно : " + count);
            makeBrackets(count, 0, 0, "");
            if (list.size() > 0){
                for (String str: list) {
                    out.println(str);
                }
            }
        }catch (NumberFormatException e){
            out.println(e.toString());
        }finally {
            list.clear();
        }
    }

    public void makeBrackets(int n, int opn, int close, String s) {
        if (opn + close == 2 * n) {
            list.add(s);
        }

        if (opn < n) {
            makeBrackets(n, opn + 1, close, s + '(');
        }

        if (opn > close) {
            makeBrackets(n, opn, close + 1, s + ')');
        }
    }
}
