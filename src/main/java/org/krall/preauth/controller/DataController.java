package org.krall.preauth.controller;

import org.krall.preauth.data.Bao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lf84914 on 13/05/15.
 */
@Controller
@RequestMapping(value = "/api")
public class DataController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private Bao importantService;

    @RequestMapping("/public")
    @ResponseBody
    public String getPublicData() {
        return "this is public data";
    }

    @RequestMapping("/mixed")
    @ResponseBody
    public String getMixedData() {
        String out = "";
        out += getPublicInformation() + "\n";

        try {
            out += getConfidentialInformation();
        } catch (AccessDeniedException se) {
            logger.debug("Ignoring the exception in order to mask the content", se);
        }

        return out;
    }

    public String getPublicInformation() {
        return "This is public data, anyone can read it, I don't care";
    }


    @RequestMapping("/confidential")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseBody
    public String getConfidentialData() {
        return "this is confidential data";
    }

    public String getConfidentialInformation() {
        return importantService.getConfidentialInformation();
    }


    public void setImportantService(Bao importantService) {
        this.importantService = importantService;
    }
}
