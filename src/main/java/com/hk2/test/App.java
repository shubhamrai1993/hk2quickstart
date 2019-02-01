package com.hk2.test;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.jvnet.hk2.annotations.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ) {
        ServiceLocator locator = ServiceLocatorUtilities.createAndPopulateServiceLocator();
        ServiceLocatorUtilities.addClasses(locator, MyClass.class, MyServiceImpl.class);
        MyClass myClass = locator.getService(MyClass.class);
        myClass.execute();
    }


    @Service
    public static class MyClass {
        public static int count = 0;
        @Inject
        private MyService service;

        public MyClass() {
            System.out.println(this.getClass().getName() + " " + count++);
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
