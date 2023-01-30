package io.github.js.self.invocation;

import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import static org.assertj.core.api.Assertions.assertThat;

// 해결 1
@EnableAspectJAutoProxy(exposeProxy=true)
@SpringBootTest
class BusinessServiceImplTest {

    @Autowired
    private BusinessService businessService;

    @Test
    void selfInvocation_테스트() {
        businessService.ready();
        assertThat(AopUtils.isCglibProxy(businessService)).isTrue();
        // TODO: CGLIB 으로 생성되는 이유찾기
//        assertThat(Proxy.isProxyClass(businessService.getClass())).isTrue();
    }

    @Test
    void ProxyFactory_프록시_생성_테스트() {
        BusinessService target = new BusinessServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
//        proxyFactory.setProxyTargetClass(true);
        BusinessService proxy = (BusinessService) proxyFactory.getProxy();

        assertThat(AopUtils.isAopProxy(proxy)).isTrue();
        assertThat(AopUtils.isCglibProxy(proxy)).isFalse();
        assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();
    }
}