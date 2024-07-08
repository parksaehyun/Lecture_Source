package board.services;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
public class BoardService implements InitializingBean, DisposableBean {

    public void write() {
        System.out.println("글쓰기!");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet()호출됨!");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy()호출됨!");
    }
}
