package com.mapped.mybatis;

import com.mapped.mybatis.dao.SsmDao;
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
        ssm();
    }

    private static void ssm() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            SsmDao dao = session.getMapper(SsmDao.class);

            dao.listAll().forEach(System.out::println);

            SsmDO ssmDO = new SsmDO();
            ssmDO.setSsm("ssm" + new java.util.Random().nextInt());
            dao.save(ssmDO);

            session.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
