package com.mapped.mybatis;

import com.mapped.mybatis.dao.OrganDao;
import com.mapped.mybatis.dao.PersonDao;
import com.mapped.mybatis.dao.SsmDao;
import com.mapped.mybatis.dao.UserDao;
import com.mapped.mybatis.entity.OrganDO;
import com.mapped.mybatis.entity.PersonDO;
import com.mapped.mybatis.entity.SsmDO;
import com.mapped.mybatis.entity.UserDO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public class AppMain {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // ssm();
        // person();
        // organ();
        user();
    }

    private static void user() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserDao userDao = session.getMapper(UserDao.class);

            UserDO userDO = new UserDO();
            userDO.setName("小树林");
            userDO.setPassword("xiaoshulin");

            System.out.println("Before: " + userDO);
            userDao.insert(userDO);
            System.out.println("After: " + userDO);

            System.out.println("sleep begin");
            Thread.sleep(10000);
            System.out.println("sleep end");

            System.out.println("lastInsertId: " + userDao.getLastInsertId());

            System.out.println("-- Before: " + userDO);
            userDao.insert(userDO);
            System.out.println("-- After: " + userDO);

            System.out.println("-- sleep begin");
            Thread.sleep(10000);
            System.out.println("-- sleep end");

            System.out.println("-- lastInsertId: " + userDao.getLastInsertId());

            System.out.println("withMap: " + userDao.selectWithMap(userDO.getId()));
            System.out.println("withPOJO: " + userDao.selectWithPOJO(userDO.getId()));
            System.out.println("withResultMap: " + userDao.selectWithResultMap(userDO.getId()));

            session.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void ssm() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            SsmDao dao = session.getMapper(SsmDao.class);

            dao.listAll().forEach(System.out::println);

            SsmDO ssmDO = new SsmDO();
            ssmDO.setSsm("ssm-中文" + new java.util.Random().nextInt());
            dao.save(ssmDO);

            session.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void person() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            PersonDao dao = session.getMapper(PersonDao.class);

            System.out.println(dao.selectPerson(11));

            PersonDO personDO = new PersonDO();
            personDO.setName("个破烂的");

            int idSave = dao.save(personDO);
            System.out.println("idSave=" + idSave + ", personDO=" + personDO);

            idSave = dao.save(personDO);
            System.out.println("idSave=" + idSave + ", personDO=" + personDO);

            int idSaveNone = dao.saveNone(personDO);
            System.out.println("idSaveNone=" + idSaveNone + ", personDO=" + personDO);

            session.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void organ() {
        try (SqlSession session = sqlSessionFactory.openSession()) {

            OrganDao dao = session.getMapper(OrganDao.class);

            dao.listAll().forEach(System.out::println);

            Integer id = dao.save(new OrganDO() {
                {
                    setName("就是个名字");
                }
            });

            System.out.println("id=" + id);


            session.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
