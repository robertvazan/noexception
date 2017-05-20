package com.machinezoo.noexception;

import java.util.function.*;
import org.slf4j.*;

public final class Exceptions {
	private static final Logger logger = LoggerFactory.getLogger(Exceptions.class);
	private static final ExceptionPassThrough pass = new ExceptionPassThrough();
	private static final ExceptionLogger log = new ExceptionLogger(logger, "Caught exception");
	private static final ExceptionSilencer silence = new ExceptionSilencer();
	private static final ExceptionSmuggler sneak = new ExceptionSmuggler();
	private static final ExceptionWrapper wrap = new ExceptionWrapper();
	private Exceptions() {
	}
	public static ExceptionHandler pass() {
		return pass;
	}
	public static ExceptionHandler log() {
		return log;
	}
	public static ExceptionHandler log(Logger logger) {
		return new ExceptionLogger(logger, "Caught exception");
	}
	public static ExceptionHandler log(Logger logger, String message) {
		return new ExceptionLogger(logger, message);
	}
	public static ExceptionHandler silence() {
		return silence;
	}
	public static CheckedExceptionHandler sneak() {
		return sneak;
	}
	public static CheckedExceptionHandler wrap() {
		return wrap;
	}
	public static CheckedExceptionHandler wrap(Function<Throwable, RuntimeException> wrapper) {
		return new ExceptionTransform(wrapper);
	}
}
