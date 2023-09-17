package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    void singletonBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
        SingletonBean singletonBean01 = ac.getBean(SingletonBean.class);
        SingletonBean singletonBean02 = ac.getBean(SingletonBean.class);

        System.out.println("singletonBean1 = " + singletonBean01);
        System.out.println("singletonBean2 = " + singletonBean02);
        assertThat(singletonBean01).isSameAs(singletonBean02);
        ac.close();
    }

    @Scope("singleton")
    static class SingletonBean {
        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void detory() {
            System.out.println("SingletonBean.destory");
        }
    }
}
