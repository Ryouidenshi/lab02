package com.devcolibri.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@WebServlet(urlPatterns = "/download")
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String[]> params = req.getParameterMap();
        String[] getParams = params.get("path");
        File file = new File(getParams[0]);
        resp.setHeader("Content-disposition", "attachment; filename=_"+file.getName());

        OutputStream out = resp.getOutputStream();
        FileInputStream in = new FileInputStream(file);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > -1) {
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
    }
}

