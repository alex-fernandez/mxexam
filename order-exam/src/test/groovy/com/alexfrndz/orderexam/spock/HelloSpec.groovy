package com.alexfrndz.orderexam.spock;
import spock.lang.Specification
/**
 * Created by afernandez on 28/06/2017.
 */
class HelloSpec extends Specification {

    def hello = new Hello();

    def sayHello() {
        given: "A person's name is given as a method parameter."
        def greeting = hello.sayHello("Petri");

        expect: "Should say hello to the person whose name is given as a method parameter"
        greeting == "Hello Petri";
    }
}