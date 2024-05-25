package configs;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class DBConn2 {
    private static SqlSessionFactory factory2;

    static {
        try {
            Reader reader2 = Resources.getResourceAsReader("configs/mybatis-config.xml");

            factory2 = new SqlSessionFactoryBuilder().build(reader2);

        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSession(boolean autoCommit) {
        return factory2.openSession(autoCommit);
    }

    public static SqlSession getSession() {
        return getSession(true);
    }
}
