package spring.security.demo.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import spring.security.demo.dao.AuthorityDAO;
import spring.security.demo.dao.UserDAO;
import spring.security.demo.entity.Authority;
import spring.security.demo.entity.User;

@Component
public class DeploymentListener {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthorityDAO authorityDAO;

    @Autowired
    private UserDAO userDAO;

    @PostConstruct
    public void addInitialData() {
        // adding default data
        if (this.authorityDAO.dbGetAuthorityCount() <= 0 && this.userDAO.dbGetUserCount() <= 0) {
            List<Authority> adminAuthorities = new ArrayList<Authority>();
            Authority adminAuthority = new Authority(null, "ROLE_ADMIN");
            this.authorityDAO.dbSaveAuthority(adminAuthority);
            adminAuthorities.add(adminAuthority);
            User admin = new User(null, "admin", passwordEncoder.encode("123"), adminAuthorities);
            this.userDAO.dbSaveUser(admin);
            List<Authority> userAuthorities = new ArrayList<Authority>();
            Authority userAuthority = new Authority(null, "ROLE_USER");
            this.authorityDAO.dbSaveAuthority(userAuthority);
            userAuthorities.add(userAuthority);
            User user = new User(null, "user", passwordEncoder.encode("123"), userAuthorities);
            this.userDAO.dbSaveUser(user);
        }
    }
}
