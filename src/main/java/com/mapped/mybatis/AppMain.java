package com.mapped.mybatis;

import com.mapped.mybatis.dao.OrganDao;
import com.mapped.mybatis.dao.PersonDao;
import com.mapped.mybatis.dao.SsmDao;
import com.mapped.mybatis.entity.OrganDO;
import com.mapped.mybatis.entity.SsmDO;
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
        //ssm();
        //person();
        organ();
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

            System.out.println(dao.selectPerson(100));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void organ() {
        try (SqlSession session = sqlSessionFactory.openSession()) {

            OrganDao dao = session.getMapper(OrganDao.class);

            dao.listAll().forEach(System.out::println);

            dao.save(new OrganDO() {
                {
                    setName("就是个名字");
                }
            });

            session.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
