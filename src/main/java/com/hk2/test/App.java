package com.hk2.test;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.jvnet.hk2.annotations.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ) {
        ServiceLocator locator = ServiceLocatorUtilities.createAndPopulateServiceLocator();
        // The order of the classes being added determines which service implementing the many contracts will be picked up
        ServiceLocatorUtilities.addClasses(locator, MyClass.class, MyServiceImpl2.class, MyServiceImpl.class);
        MyClass myClass = locator.getService(MyClass.class);
        myClass.execute();
    }


    @Service
    public static class MyClass {
        public static int count = 0;
        @Inject
        @Named("MyServiceImpl")
        private MyService service;

        public MyClass() {
            System.out.println(this.getClass().getName() + " : constructor : " + count++);
        }

        public void execute() {
            System.out.println(this.getClass().getName() + " " + count++);
            service.execute();
        }

        @PostConstruct
        public void increment() {
            System.out.println(this.getClass().getName() + " incremented " + count++);
        }
    }
}
