// Part of NoException: https://noexception.machinezoo.com
/**
 * NoException library lets you handle exceptions in a concise, testable, and consistent manner.
 * Look first in the {@link com.machinezoo.noexception} package, especially the {@link com.machinezoo.noexception.Exceptions} class.
 * 
 * @see <a href="https://noexception.machinezoo.com/">NoException tutorial</a>
 */
module com.machinezoo.noexception {
	exports com.machinezoo.noexception;
	exports com.machinezoo.noexception.optional;
	exports com.machinezoo.noexception.throwing;
	requires transitive org.slf4j;
}
