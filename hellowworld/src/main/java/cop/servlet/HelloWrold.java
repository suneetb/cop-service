/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cop.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
/**
 * <p>
 * A simple servlet 3 as client that sends several messages to a queue or a topic.
 * </p>
 * 
 * <p>
 * The servlet is registered and mapped to /HelloWorldMDBServletClient using the {@linkplain WebServlet
 * @HttpServlet}.
 * </p>
 * 
 * @author Serge Pagop (spagop@redhat.com)
 * 
 */
@WebServlet("/HelloWorldServlet")
public class HelloWrold extends HttpServlet {

    private static final long serialVersionUID = -8314035702649252239L;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        out.write("<h1> Current time is</h1>");
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            out.write("<p>Time Now is <em>" + dateFormat.format(date) + "</em></p>");
            out.write("<p><i>Go to your JBoss EAP server console or log to see the result of messages processing</i></p>");

        } catch (JMSException e) {
            e.printStackTrace();
            out.write("<h2>A problem occurred during the delivery of this message</h2>");
            out.write("</br>");
            out.write("<p><i>Go your the JBoss EAP server console or log to see the error stack trace</i></p>");
          }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
