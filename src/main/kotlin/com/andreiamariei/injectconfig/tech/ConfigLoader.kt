package com.andreiamariei.injectconfig.tech

import com.andreiamariei.injectconfig.MyServiceImplOne
import com.andreiamariei.injectconfig.MyServiceImplTwo
import com.andreiamariei.injectconfig.MyServiceInterface
import org.springframework.aop.scope.ScopedProxyUtils
import org.springframework.beans.factory.FactoryBean
import org.springframework.beans.factory.config.BeanDefinitionHolder
import org.springframework.beans.factory.config.BeanFactoryPostProcessor
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.beans.factory.config.ConstructorArgumentValues
import org.springframework.beans.factory.support.BeanDefinitionRegistry
import org.springframework.beans.factory.support.RootBeanDefinition
import org.springframework.stereotype.Component


@Component
class ConfigLoader : BeanFactoryPostProcessor {
    override fun postProcessBeanFactory(beanFactory: ConfigurableListableBeanFactory) {
        val beanDefinitionRegistry = beanFactory as BeanDefinitionRegistry
        val myServiceInterface = MyServiceInterface::class.java
        val myServiceImplOne = beanFactory.getBean(MyServiceImplOne::class.java)
        val myServiceImplTwo = beanFactory.getBean(MyServiceImplTwo::class.java)
        val myServiceImplList = listOf(myServiceImplOne, myServiceImplTwo)

        registerConfigChooser(myServiceInterface, myServiceImplList, beanDefinitionRegistry)
    }

    private fun registerConfigChooser(myServiceInterface: Class<MyServiceInterface>, myServiceImplList: List<MyServiceInterface>, beanDefinitionRegistry: BeanDefinitionRegistry) {
        val cav = ConstructorArgumentValues()
        cav.addIndexedArgumentValue(0, myServiceInterface)
        cav.addIndexedArgumentValue(1, myServiceImplList)

        val rootBeanDefinition = RootBeanDefinition(ConfigChooser::class.java, cav, null)
        rootBeanDefinition.isPrimary = true
        rootBeanDefinition.targetType = myServiceInterface
        rootBeanDefinition.scope = "request"

        val proxyDef = ScopedProxyUtils.createScopedProxy(
                BeanDefinitionHolder(rootBeanDefinition, "myService"),
                beanDefinitionRegistry,
                true)

        beanDefinitionRegistry.registerBeanDefinition("myService", proxyDef.beanDefinition)
    }
}

class ConfigChooser<T>(
        private val serviceInterface: Class<T>,
        private val serviceInstances: List<T>
) : FactoryBean<T> {
    override fun getObject(): T {
        return if (Math.random() < 0.5) {
            serviceInstances[0]
        } else {
            serviceInstances[1]
        }
    }

    override fun getObjectType(): Class<*>? {
        return serviceInterface::class.java
    }

    override fun isSingleton() = false
}