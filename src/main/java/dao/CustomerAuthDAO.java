package dao;

import model.CustomerAuth;

public interface CustomerAuthDAO {
    public int insert(CustomerAuth customerAuth);
    public CustomerAuth selectBySelector(String selector);
    public int update(CustomerAuth customerAuth);
    public int deleteBySelector(String selector);
}
