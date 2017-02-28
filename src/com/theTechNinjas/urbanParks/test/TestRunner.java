package com.theTechNinjas.urbanParks.test;

import java.util.Timer;
import java.util.TimerTask;

import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;

/**
 * This class extends JUnit's {@link Suite} runner to add custom functionality
 * and behavior to the {@link TestSuite}.
 *
 * @author Joshua Neighbarger | jneigh@uw.edu
 * @version 10 Feb 2017
 */
public class TestRunner extends Suite {

	public TestRunner(final Class<?> klass, final Class<?>[] suiteClasses) throws InitializationError {
		super(klass, suiteClasses);
	}

	public TestRunner(final Class<?> klass) throws InitializationError {
		super(klass, klass.getAnnotation(SuiteClasses.class).value());
	}

	@Override
	public void run(final RunNotifier r) {
		r.addListener(new FailureListener(r));
		super.run(r);
	}

	private class FailureListener extends RunListener {

		private static final long TIMEOUT = 2000;
		private final Timer timer;

		private FailureListener(final RunNotifier notifier) {
			this.timer = new Timer();
		}

		/**
		 * If a test takes longer than the TIMEOUT, as in an infinite loop, the
		 * test Suite will stop. This is necessary so that the {@link Suite}
		 * does not continue to run on the JVM.
		 */
		@Override
		public void testStarted(final Description d) {
			timer.purge();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					System.exit(1);
				}
			}, TIMEOUT);
		}

	}

}
