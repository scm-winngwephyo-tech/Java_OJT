package spring.security.demo.dao;

import spring.security.demo.entity.Authority;

public interface AuthorityDAO {

    public void dbSaveAuthority(Authority authority);

    public long dbGetAuthorityCount();
}
