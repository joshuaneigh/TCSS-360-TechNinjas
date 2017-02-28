package com.theTechNinjas.urbanParks.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(TestRunner.class)
@Suite.SuiteClasses({ UserStoriesTest.class, TestBR1a.class, TestBR1b.class, TestBR2d.class, TestBR3c.class,
    ControllerGettersTest.class})

/**
 * This class is used to declare the JUnit Test Suite. The SuiteClasses must be
 * declared with class literals, meaning that this class must be maintained; the
 * Java Language Specifications (JLS) will not allow anything but literals in
 * annotations, including static final constants. After modifying the suite,
 * please be sure to reformat the file and change the date in this JavaDoc.
 *
 * @author Joshua Neighbarger | jneigh@uw.edu
 * @author John Bako | jsbako90@uw.edu
 * @author Jasmine Dacones | jazzyd25@uw.edu
 * @author Youcef Bennour | ybennour@uw.edu
 * @author Michael Loundagin | loundm@uw.edu
 * 
 * @version 23 Feb 2017
 */
public class TestSuite {
}
