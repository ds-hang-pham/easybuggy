package org.t246osslab.easybuggy.vulnerabilities;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.t246osslab.easybuggy.core.servlets.AbstractServlet;
import org.apache.commons.text.StringEscapeUtils;
<<<<<<< HEAD
import java.util.regex.*;
import org.apache.commons.validator.routines.UrlValidator;
=======
>>>>>>> 8847c68 (xss-attack)

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/xss" })
public class XSSServlet extends AbstractServlet {
	
	public String changeInput(String input)  {
        String escapeString = StringEscapeUtils.escapeHtml4(input);
        return escapeString;
	}
<<<<<<< HEAD

    public Boolean isUrl(String input) {
        String regex = "(?:src|href)=\"(.*?)\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        String url = null;
        if (matcher.find()) {
            url = matcher.group(1);
        }
        if(!StringUtils.isBlank(url)) {
        	UrlValidator urlValidator = new UrlValidator();
        	return urlValidator.isValid(url);
        } 
         return true;
    }
=======
>>>>>>> 8847c68 (xss-attack)
	
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        try {
            String string = req.getParameter("string");
            Locale locale = req.getLocale();
            
            StringBuilder bodyHtml = new StringBuilder();

            bodyHtml.append("<form action=\"xss\" method=\"post\">");
            bodyHtml.append(getMsg("description.reverse.string", locale));
            bodyHtml.append("<br><br>");
            bodyHtml.append(getMsg("label.string", locale) + ": ");
            bodyHtml.append("<input type=\"text\" name=\"string\" size=\"100\" maxlength=\"100\">");
            bodyHtml.append("<br><br>");
            bodyHtml.append("<input type=\"submit\" value=\"" + getMsg("label.submit", locale) + "\">");
            bodyHtml.append("<br><br>");

            if (!StringUtils.isBlank(string)) {
                // Reverse the given string
                String reversedName = StringUtils.reverse(string);
                String escapeString = changeInput(reversedName);
<<<<<<< HEAD
                if(isUrl(escapeString)) {
                    bodyHtml.append(getMsg("label.reversed.string", locale) + " : "
                            + escapeString);
                } else {
                	bodyHtml.append(getMsg("msg.enter.string", locale));
                }
=======
                bodyHtml.append(getMsg("label.reversed.string", locale) + " : "
                        + escapeString);
>>>>>>> 8847c68 (xss-attack)
            } else {
                bodyHtml.append(getMsg("msg.enter.string", locale));
            }
            bodyHtml.append("<br><br>");
            bodyHtml.append(getInfoMsg("msg.note.xss", locale));
            bodyHtml.append("</form>");

            responseToClient(req, res, getMsg("title.xss.page", locale), bodyHtml.toString());

        } catch (Exception e) {
            log.error("Exception occurs: ", e);
        }
    }
}
