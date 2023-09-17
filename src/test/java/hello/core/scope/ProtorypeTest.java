package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.annotation.PrototypeAspectInstanceFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProtorypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext((ProtorypeBean.class));
        System.out.println("find prototypeBean1");
        ProtorypeBean protorypeBean1 = ac.getBean(ProtorypeBean.class);
        System.out.println("find prototypeBean2");
        ProtorypeBean protorypeBean2 = ac.getBean(ProtorypeBean.class);
        System.out.println("prototypeBean1 = " + protorypeBean1);
        System.out.println("prototypeBean2 = " + protorypeBean2);
        assertThat(protorypeBean1).isNotSameAs(protorypeBean2);
        ac.close();
    }

    @Scope("prototype")
    static class ProtorypeBean {
        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destory() {
            System.out.println("SingletonBean.destory");
        }
    }
}
