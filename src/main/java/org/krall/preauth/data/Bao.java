package org.krall.preauth.data;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

/**
 * Created by lf84914 on 13/05/15.
 */
@Component
public class Bao {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getConfidentialInformation() {
        return "This is very secret data";
    }
}
