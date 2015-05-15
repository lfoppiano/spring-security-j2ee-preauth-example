package org.krall.preauth.controller;

import org.krall.preauth.data.Bao;
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

    Bao importantService = new Bao();

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

        out += getConfidentialInformation();

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


    // doesn't work
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getConfidentialInformation() {
        return importantService.getConfidentialInformation();
    }
}
