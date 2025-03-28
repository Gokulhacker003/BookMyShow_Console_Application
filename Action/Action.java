package Action;

import User.*;
public interface Action {
    User login(String Id);
    void addUser(String id);
    void UserOps(User user);
}
