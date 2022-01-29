package ServicesImpl;

import DaoImpl.UserDaoImpl;
import Entity.User;
import ServicesIntf.UserServiceIntf;
import Util.Constant;

import java.io.File;
import java.util.List;

public class UserServiceImpl implements UserServiceIntf {
    UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public void edit(User newUser) {
        User oldUser = userDao.getUser(newUser.getUSER_ID());
        oldUser.setFIRSTNAME(newUser.getFIRSTNAME());
        oldUser.setLASTNAME(newUser.getLASTNAME());
        oldUser.setUSERNAME(newUser.getUSERNAME());
        oldUser.setPASSWORD(newUser.getPASSWORD());
        oldUser.setEMAIL(newUser.getEMAIL());
        oldUser.setADDRESS(newUser.getADDRESS());
        oldUser.setPHONE(newUser.getPHONE());
        oldUser.setROLE_ID(newUser.getROLE_ID());
        if (newUser.getAVATAR() != null) {
            //Xóa ảnh cũ
            String fileName = oldUser.getAVATAR();
            File file = new File(Constant.Path.AVATARS + File.separator + fileName);
            if (file.exists()) {
                file.delete();
            }
            //Thêm ảnh mới
            oldUser.setAVATAR(newUser.getAVATAR());
        }

        userDao.edit(oldUser);
    }

    @Override
    public void delete(int USER_ID) {
        userDao.delete(USER_ID);
    }

    @Override
    public User getUser(int USER_ID) {
        return userDao.getUser(USER_ID);
    }

    @Override
    public User getUser(String USERNAME_OR_EMAIL) {
        return userDao.getUser(USERNAME_OR_EMAIL);
    }

    @Override
    public User login(String USERNAME_OR_EMAIL, String PASSWORD) {
        User user = userDao.getUser(USERNAME_OR_EMAIL);
        if (user != null && PASSWORD.equals(user.getPASSWORD())) {
            return user;
        }

        return null;
    }

    @Override
    public boolean register(String USERNAME, String PASSWORD, String EMAIL) {
        if (checkExistUSERNAME(USERNAME) || checkExistEMAIL(EMAIL)) {
            return false;
        }
        insert(new User(USERNAME, PASSWORD, EMAIL));

        return true;
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public List<User> search(String keyword) {
        return userDao.search(keyword);
    }

    @Override
    public boolean checkExistUSERNAME(String USERNAME) {
        return userDao.checkExistUSERNAME(USERNAME);
    }

    @Override
    public boolean checkExistEMAIL(String EMAIL) {
        return userDao.checkExistEMAIL(EMAIL);
    }
}
