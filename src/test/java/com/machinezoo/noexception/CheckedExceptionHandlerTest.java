// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception;

import static org.hamcrest.core.IsInstanceOf.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.awt.print.*;
import org.junit.*;
import com.machinezoo.noexception.throwing.*;

public class CheckedExceptionHandlerTest {
	@Test public void runnable_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingRunnable lambda = mock(ThrowingRunnable.class);
		collector.runnable(lambda).run();
		verify(lambda, only()).run();
		assertTrue(collector.empty());
	}
	@Test public void runnable_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.runnable(() -> {
				throw new PrinterException();
			}).run();
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void runnable_runtime() {
		try {
			new CheckedExceptionCollector().runnable(() -> {
				throw new IllegalArgumentException();
			}).run();
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void runnable_error() {
		try {
			new CheckedExceptionCollector().runnable(() -> {
				throw new AssertionError();
			}).run();
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void supplier_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		@SuppressWarnings("unchecked") ThrowingSupplier<String> lambda = mock(ThrowingSupplier.class);
		when(lambda.get()).thenReturn("value");
		assertEquals("value", collector.supplier(lambda).get());
		verify(lambda, only()).get();
		assertTrue(collector.empty());
	}
	@Test public void supplier_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.supplier(() -> {
				throw new PrinterException();
			}).get();
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void supplier_runtime() {
		try {
			new CheckedExceptionCollector().supplier(() -> {
				throw new IllegalArgumentException();
			}).get();
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void supplier_error() {
		try {
			new CheckedExceptionCollector().supplier(() -> {
				throw new AssertionError();
			}).get();
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromIntSupplier_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingIntSupplier lambda = mock(ThrowingIntSupplier.class);
		when(lambda.getAsInt()).thenReturn(2);
		assertEquals(2, collector.fromIntSupplier(lambda).getAsInt());
		verify(lambda, only()).getAsInt();
		assertTrue(collector.empty());
	}
	@Test public void fromIntSupplier_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromIntSupplier(() -> {
				throw new PrinterException();
			}).getAsInt();
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromIntSupplier_runtime() {
		try {
			new CheckedExceptionCollector().fromIntSupplier(() -> {
				throw new IllegalArgumentException();
			}).getAsInt();
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromIntSupplier_error() {
		try {
			new CheckedExceptionCollector().fromIntSupplier(() -> {
				throw new AssertionError();
			}).getAsInt();
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromLongSupplier_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingLongSupplier lambda = mock(ThrowingLongSupplier.class);
		when(lambda.getAsLong()).thenReturn(2L);
		assertEquals(2L, collector.fromLongSupplier(lambda).getAsLong());
		verify(lambda, only()).getAsLong();
		assertTrue(collector.empty());
	}
	@Test public void fromLongSupplier_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromLongSupplier(() -> {
				throw new PrinterException();
			}).getAsLong();
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromLongSupplier_runtime() {
		try {
			new CheckedExceptionCollector().fromLongSupplier(() -> {
				throw new IllegalArgumentException();
			}).getAsLong();
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromLongSupplier_error() {
		try {
			new CheckedExceptionCollector().fromLongSupplier(() -> {
				throw new AssertionError();
			}).getAsLong();
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromDoubleSupplier_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingDoubleSupplier lambda = mock(ThrowingDoubleSupplier.class);
		when(lambda.getAsDouble()).thenReturn(2.0);
		assertEquals(2.0, collector.fromDoubleSupplier(lambda).getAsDouble(), 0.1);
		verify(lambda, only()).getAsDouble();
		assertTrue(collector.empty());
	}
	@Test public void fromDoubleSupplier_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromDoubleSupplier(() -> {
				throw new PrinterException();
			}).getAsDouble();
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromDoubleSupplier_runtime() {
		try {
			new CheckedExceptionCollector().fromDoubleSupplier(() -> {
				throw new IllegalArgumentException();
			}).getAsDouble();
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromDoubleSupplier_error() {
		try {
			new CheckedExceptionCollector().fromDoubleSupplier(() -> {
				throw new AssertionError();
			}).getAsDouble();
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromBooleanSupplier_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingBooleanSupplier lambda = mock(ThrowingBooleanSupplier.class);
		when(lambda.getAsBoolean()).thenReturn(true);
		assertEquals(true, collector.fromBooleanSupplier(lambda).getAsBoolean());
		verify(lambda, only()).getAsBoolean();
		assertTrue(collector.empty());
	}
	@Test public void fromBooleanSupplier_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromBooleanSupplier(() -> {
				throw new PrinterException();
			}).getAsBoolean();
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromBooleanSupplier_runtime() {
		try {
			new CheckedExceptionCollector().fromBooleanSupplier(() -> {
				throw new IllegalArgumentException();
			}).getAsBoolean();
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromBooleanSupplier_error() {
		try {
			new CheckedExceptionCollector().fromBooleanSupplier(() -> {
				throw new AssertionError();
			}).getAsBoolean();
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void consumer_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		@SuppressWarnings("unchecked") ThrowingConsumer<String> lambda = mock(ThrowingConsumer.class);
		collector.consumer(lambda).accept("input");
		verify(lambda, only()).accept("input");
		assertTrue(collector.empty());
	}
	@Test public void consumer_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.consumer(t -> {
				throw new PrinterException();
			}).accept("input");
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void consumer_runtime() {
		try {
			new CheckedExceptionCollector().consumer(t -> {
				throw new IllegalArgumentException();
			}).accept("input");
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void consumer_error() {
		try {
			new CheckedExceptionCollector().consumer(t -> {
				throw new AssertionError();
			}).accept("input");
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromIntConsumer_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingIntConsumer lambda = mock(ThrowingIntConsumer.class);
		collector.fromIntConsumer(lambda).accept(1);
		verify(lambda, only()).accept(1);
		assertTrue(collector.empty());
	}
	@Test public void fromIntConsumer_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromIntConsumer(v -> {
				throw new PrinterException();
			}).accept(1);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromIntConsumer_runtime() {
		try {
			new CheckedExceptionCollector().fromIntConsumer(v -> {
				throw new IllegalArgumentException();
			}).accept(1);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromIntConsumer_error() {
		try {
			new CheckedExceptionCollector().fromIntConsumer(v -> {
				throw new AssertionError();
			}).accept(1);
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromLongConsumer_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingLongConsumer lambda = mock(ThrowingLongConsumer.class);
		collector.fromLongConsumer(lambda).accept(1L);
		verify(lambda, only()).accept(1L);
		assertTrue(collector.empty());
	}
	@Test public void fromLongConsumer_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromLongConsumer(v -> {
				throw new PrinterException();
			}).accept(1L);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromLongConsumer_runtime() {
		try {
			new CheckedExceptionCollector().fromLongConsumer(v -> {
				throw new IllegalArgumentException();
			}).accept(1L);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromLongConsumer_error() {
		try {
			new CheckedExceptionCollector().fromLongConsumer(v -> {
				throw new AssertionError();
			}).accept(1L);
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromDoubleConsumer_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingDoubleConsumer lambda = mock(ThrowingDoubleConsumer.class);
		collector.fromDoubleConsumer(lambda).accept(1.0);
		verify(lambda, only()).accept(1.0);
		assertTrue(collector.empty());
	}
	@Test public void fromDoubleConsumer_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromDoubleConsumer(v -> {
				throw new PrinterException();
			}).accept(1.0);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromDoubleConsumer_runtime() {
		try {
			new CheckedExceptionCollector().fromDoubleConsumer(v -> {
				throw new IllegalArgumentException();
			}).accept(1.0);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromDoubleConsumer_error() {
		try {
			new CheckedExceptionCollector().fromDoubleConsumer(v -> {
				throw new AssertionError();
			}).accept(1.0);
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromBiConsumer_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		@SuppressWarnings("unchecked") ThrowingBiConsumer<String, String> lambda = mock(ThrowingBiConsumer.class);
		collector.fromBiConsumer(lambda).accept("input1", "input2");
		verify(lambda, only()).accept("input1", "input2");
		assertTrue(collector.empty());
	}
	@Test public void fromBiConsumer_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromBiConsumer((t, u) -> {
				throw new PrinterException();
			}).accept("input1", "input2");
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromBiConsumer_runtime() {
		try {
			new CheckedExceptionCollector().fromBiConsumer((t, u) -> {
				throw new IllegalArgumentException();
			}).accept("input1", "input2");
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromBiConsumer_error() {
		try {
			new CheckedExceptionCollector().fromBiConsumer((t, u) -> {
				throw new AssertionError();
			}).accept("input1", "input2");
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromObjIntConsumer_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		@SuppressWarnings("unchecked") ThrowingObjIntConsumer<String> lambda = mock(ThrowingObjIntConsumer.class);
		collector.fromObjIntConsumer(lambda).accept("input", 1);
		verify(lambda, only()).accept("input", 1);
		assertTrue(collector.empty());
	}
	@Test public void fromObjIntConsumer_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromObjIntConsumer((t, v) -> {
				throw new PrinterException();
			}).accept("input", 1);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromObjIntConsumer_runtime() {
		try {
			new CheckedExceptionCollector().fromObjIntConsumer((t, v) -> {
				throw new IllegalArgumentException();
			}).accept("input", 1);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromObjIntConsumer_error() {
		try {
			new CheckedExceptionCollector().fromObjIntConsumer((t, v) -> {
				throw new AssertionError();
			}).accept("input", 1);
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromObjLongConsumer_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		@SuppressWarnings("unchecked") ThrowingObjLongConsumer<String> lambda = mock(ThrowingObjLongConsumer.class);
		collector.fromObjLongConsumer(lambda).accept("input", 1L);
		verify(lambda, only()).accept("input", 1L);
		assertTrue(collector.empty());
	}
	@Test public void fromObjLongConsumer_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromObjLongConsumer((t, v) -> {
				throw new PrinterException();
			}).accept("input", 1L);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromObjLongConsumer_runtime() {
		try {
			new CheckedExceptionCollector().fromObjLongConsumer((t, v) -> {
				throw new IllegalArgumentException();
			}).accept("input", 1L);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromObjLongConsumer_error() {
		try {
			new CheckedExceptionCollector().fromObjLongConsumer((t, v) -> {
				throw new AssertionError();
			}).accept("input", 1L);
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromObjDoubleConsumer_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		@SuppressWarnings("unchecked") ThrowingObjDoubleConsumer<String> lambda = mock(ThrowingObjDoubleConsumer.class);
		collector.fromObjDoubleConsumer(lambda).accept("input", 1.0);
		verify(lambda, only()).accept("input", 1.0);
		assertTrue(collector.empty());
	}
	@Test public void fromObjDoubleConsumer_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromObjDoubleConsumer((t, v) -> {
				throw new PrinterException();
			}).accept("input", 1.0);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromObjDoubleConsumer_runtime() {
		try {
			new CheckedExceptionCollector().fromObjDoubleConsumer((t, v) -> {
				throw new IllegalArgumentException();
			}).accept("input", 1.0);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromObjDoubleConsumer_error() {
		try {
			new CheckedExceptionCollector().fromObjDoubleConsumer((t, v) -> {
				throw new AssertionError();
			}).accept("input", 1.0);
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void predicate_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		@SuppressWarnings("unchecked") ThrowingPredicate<String> lambda = mock(ThrowingPredicate.class);
		when(lambda.test("input")).thenReturn(true);
		assertEquals(true, collector.predicate(lambda).test("input"));
		verify(lambda, only()).test("input");
		assertTrue(collector.empty());
	}
	@Test public void predicate_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.predicate(t -> {
				throw new PrinterException();
			}).test("input");
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void predicate_runtime() {
		try {
			new CheckedExceptionCollector().predicate(t -> {
				throw new IllegalArgumentException();
			}).test("input");
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void predicate_error() {
		try {
			new CheckedExceptionCollector().predicate(t -> {
				throw new AssertionError();
			}).test("input");
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromIntPredicate_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingIntPredicate lambda = mock(ThrowingIntPredicate.class);
		when(lambda.test(1)).thenReturn(true);
		assertEquals(true, collector.fromIntPredicate(lambda).test(1));
		verify(lambda, only()).test(1);
		assertTrue(collector.empty());
	}
	@Test public void fromIntPredicate_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromIntPredicate(v -> {
				throw new PrinterException();
			}).test(1);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromIntPredicate_runtime() {
		try {
			new CheckedExceptionCollector().fromIntPredicate(v -> {
				throw new IllegalArgumentException();
			}).test(1);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromIntPredicate_error() {
		try {
			new CheckedExceptionCollector().fromIntPredicate(v -> {
				throw new AssertionError();
			}).test(1);
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromLongPredicate_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingLongPredicate lambda = mock(ThrowingLongPredicate.class);
		when(lambda.test(1L)).thenReturn(true);
		assertEquals(true, collector.fromLongPredicate(lambda).test(1L));
		verify(lambda, only()).test(1L);
		assertTrue(collector.empty());
	}
	@Test public void fromLongPredicate_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromLongPredicate(v -> {
				throw new PrinterException();
			}).test(1L);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromLongPredicate_runtime() {
		try {
			new CheckedExceptionCollector().fromLongPredicate(v -> {
				throw new IllegalArgumentException();
			}).test(1L);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromLongPredicate_error() {
		try {
			new CheckedExceptionCollector().fromLongPredicate(v -> {
				throw new AssertionError();
			}).test(1L);
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromDoublePredicate_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingDoublePredicate lambda = mock(ThrowingDoublePredicate.class);
		when(lambda.test(1.0)).thenReturn(true);
		assertEquals(true, collector.fromDoublePredicate(lambda).test(1.0));
		verify(lambda, only()).test(1.0);
		assertTrue(collector.empty());
	}
	@Test public void fromDoublePredicate_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromDoublePredicate(v -> {
				throw new PrinterException();
			}).test(1.0);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromDoublePredicate_runtime() {
		try {
			new CheckedExceptionCollector().fromDoublePredicate(v -> {
				throw new IllegalArgumentException();
			}).test(1.0);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromDoublePredicate_error() {
		try {
			new CheckedExceptionCollector().fromDoublePredicate(v -> {
				throw new AssertionError();
			}).test(1.0);
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromBiPredicate_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		@SuppressWarnings("unchecked") ThrowingBiPredicate<String, String> lambda = mock(ThrowingBiPredicate.class);
		when(lambda.test("input1", "input2")).thenReturn(true);
		assertEquals(true, collector.fromBiPredicate(lambda).test("input1", "input2"));
		verify(lambda, only()).test("input1", "input2");
		assertTrue(collector.empty());
	}
	@Test public void fromBiPredicate_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromBiPredicate((t, u) -> {
				throw new PrinterException();
			}).test("input1", "input2");
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromBiPredicate_runtime() {
		try {
			new CheckedExceptionCollector().fromBiPredicate((t, u) -> {
				throw new IllegalArgumentException();
			}).test("input1", "input2");
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromBiPredicate_error() {
		try {
			new CheckedExceptionCollector().fromBiPredicate((t, u) -> {
				throw new AssertionError();
			}).test("input1", "input2");
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void function_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		@SuppressWarnings("unchecked") ThrowingFunction<String, String> lambda = mock(ThrowingFunction.class);
		when(lambda.apply("input")).thenReturn("value");
		assertEquals("value", collector.function(lambda).apply("input"));
		verify(lambda, only()).apply("input");
		assertTrue(collector.empty());
	}
	@Test public void function_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.function(t -> {
				throw new PrinterException();
			}).apply("input");
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void function_runtime() {
		try {
			new CheckedExceptionCollector().function(t -> {
				throw new IllegalArgumentException();
			}).apply("input");
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void function_error() {
		try {
			new CheckedExceptionCollector().function(t -> {
				throw new AssertionError();
			}).apply("input");
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromToIntFunction_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		@SuppressWarnings("unchecked") ThrowingToIntFunction<String> lambda = mock(ThrowingToIntFunction.class);
		when(lambda.applyAsInt("input")).thenReturn(2);
		assertEquals(2, collector.fromToIntFunction(lambda).applyAsInt("input"));
		verify(lambda, only()).applyAsInt("input");
		assertTrue(collector.empty());
	}
	@Test public void fromToIntFunction_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromToIntFunction(v -> {
				throw new PrinterException();
			}).applyAsInt("input");
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromToIntFunction_runtime() {
		try {
			new CheckedExceptionCollector().fromToIntFunction(v -> {
				throw new IllegalArgumentException();
			}).applyAsInt("input");
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromToIntFunction_error() {
		try {
			new CheckedExceptionCollector().fromToIntFunction(v -> {
				throw new AssertionError();
			}).applyAsInt("input");
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromIntFunction_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		@SuppressWarnings("unchecked") ThrowingIntFunction<String> lambda = mock(ThrowingIntFunction.class);
		when(lambda.apply(1)).thenReturn("value");
		assertEquals("value", collector.fromIntFunction(lambda).apply(1));
		verify(lambda, only()).apply(1);
		assertTrue(collector.empty());
	}
	@Test public void fromIntFunction_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromIntFunction(v -> {
				throw new PrinterException();
			}).apply(1);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromIntFunction_runtime() {
		try {
			new CheckedExceptionCollector().fromIntFunction(v -> {
				throw new IllegalArgumentException();
			}).apply(1);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromIntFunction_error() {
		try {
			new CheckedExceptionCollector().fromIntFunction(v -> {
				throw new AssertionError();
			}).apply(1);
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromIntToLongFunction_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingIntToLongFunction lambda = mock(ThrowingIntToLongFunction.class);
		when(lambda.applyAsLong(1)).thenReturn(2L);
		assertEquals(2L, collector.fromIntToLongFunction(lambda).applyAsLong(1));
		verify(lambda, only()).applyAsLong(1);
		assertTrue(collector.empty());
	}
	@Test public void fromIntToLongFunction_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromIntToLongFunction(v -> {
				throw new PrinterException();
			}).applyAsLong(1);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromIntToLongFunction_runtime() {
		try {
			new CheckedExceptionCollector().fromIntToLongFunction(v -> {
				throw new IllegalArgumentException();
			}).applyAsLong(1);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromIntToLongFunction_error() {
		try {
			new CheckedExceptionCollector().fromIntToLongFunction(v -> {
				throw new AssertionError();
			}).applyAsLong(1);
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromIntToDoubleFunction_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingIntToDoubleFunction lambda = mock(ThrowingIntToDoubleFunction.class);
		when(lambda.applyAsDouble(1)).thenReturn(2.0);
		assertEquals(2.0, collector.fromIntToDoubleFunction(lambda).applyAsDouble(1), 0.1);
		verify(lambda, only()).applyAsDouble(1);
		assertTrue(collector.empty());
	}
	@Test public void fromIntToDoubleFunction_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromIntToDoubleFunction(v -> {
				throw new PrinterException();
			}).applyAsDouble(1);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromIntToDoubleFunction_runtime() {
		try {
			new CheckedExceptionCollector().fromIntToDoubleFunction(v -> {
				throw new IllegalArgumentException();
			}).applyAsDouble(1);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromIntToDoubleFunction_error() {
		try {
			new CheckedExceptionCollector().fromIntToDoubleFunction(v -> {
				throw new AssertionError();
			}).applyAsDouble(1);
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromToLongFunction_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		@SuppressWarnings("unchecked") ThrowingToLongFunction<String> lambda = mock(ThrowingToLongFunction.class);
		when(lambda.applyAsLong("input")).thenReturn(2L);
		assertEquals(2L, collector.fromToLongFunction(lambda).applyAsLong("input"));
		verify(lambda, only()).applyAsLong("input");
		assertTrue(collector.empty());
	}
	@Test public void fromToLongFunction_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromToLongFunction(v -> {
				throw new PrinterException();
			}).applyAsLong("input");
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromToLongFunction_runtime() {
		try {
			new CheckedExceptionCollector().fromToLongFunction(v -> {
				throw new IllegalArgumentException();
			}).applyAsLong("input");
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromToLongFunction_error() {
		try {
			new CheckedExceptionCollector().fromToLongFunction(v -> {
				throw new AssertionError();
			}).applyAsLong("input");
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromLongFunction_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		@SuppressWarnings("unchecked") ThrowingLongFunction<String> lambda = mock(ThrowingLongFunction.class);
		when(lambda.apply(1L)).thenReturn("value");
		assertEquals("value", collector.fromLongFunction(lambda).apply(1L));
		verify(lambda, only()).apply(1L);
		assertTrue(collector.empty());
	}
	@Test public void fromLongFunction_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromLongFunction(v -> {
				throw new PrinterException();
			}).apply(1L);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromLongFunction_runtime() {
		try {
			new CheckedExceptionCollector().fromLongFunction(v -> {
				throw new IllegalArgumentException();
			}).apply(1L);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromLongFunction_error() {
		try {
			new CheckedExceptionCollector().fromLongFunction(v -> {
				throw new AssertionError();
			}).apply(1L);
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromLongToIntFunction_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingLongToIntFunction lambda = mock(ThrowingLongToIntFunction.class);
		when(lambda.applyAsInt(1L)).thenReturn(2);
		assertEquals(2, collector.fromLongToIntFunction(lambda).applyAsInt(1L));
		verify(lambda, only()).applyAsInt(1L);
		assertTrue(collector.empty());
	}
	@Test public void fromLongToIntFunction_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromLongToIntFunction(v -> {
				throw new PrinterException();
			}).applyAsInt(1L);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromLongToIntFunction_runtime() {
		try {
			new CheckedExceptionCollector().fromLongToIntFunction(v -> {
				throw new IllegalArgumentException();
			}).applyAsInt(1L);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromLongToIntFunction_error() {
		try {
			new CheckedExceptionCollector().fromLongToIntFunction(v -> {
				throw new AssertionError();
			}).applyAsInt(1L);
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromLongToDoubleFunction_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingLongToDoubleFunction lambda = mock(ThrowingLongToDoubleFunction.class);
		when(lambda.applyAsDouble(1L)).thenReturn(2.0);
		assertEquals(2.0, collector.fromLongToDoubleFunction(lambda).applyAsDouble(1L), 0.1);
		verify(lambda, only()).applyAsDouble(1L);
		assertTrue(collector.empty());
	}
	@Test public void fromLongToDoubleFunction_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromLongToDoubleFunction(v -> {
				throw new PrinterException();
			}).applyAsDouble(1L);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromLongToDoubleFunction_runtime() {
		try {
			new CheckedExceptionCollector().fromLongToDoubleFunction(v -> {
				throw new IllegalArgumentException();
			}).applyAsDouble(1L);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromLongToDoubleFunction_error() {
		try {
			new CheckedExceptionCollector().fromLongToDoubleFunction(v -> {
				throw new AssertionError();
			}).applyAsDouble(1L);
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromToDoubleFunction_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		@SuppressWarnings("unchecked") ThrowingToDoubleFunction<String> lambda = mock(ThrowingToDoubleFunction.class);
		when(lambda.applyAsDouble("input")).thenReturn(2.0);
		assertEquals(2.0, collector.fromToDoubleFunction(lambda).applyAsDouble("input"), 0.1);
		verify(lambda, only()).applyAsDouble("input");
		assertTrue(collector.empty());
	}
	@Test public void fromToDoubleFunction_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromToDoubleFunction(v -> {
				throw new PrinterException();
			}).applyAsDouble("input");
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromToDoubleFunction_runtime() {
		try {
			new CheckedExceptionCollector().fromToDoubleFunction(v -> {
				throw new IllegalArgumentException();
			}).applyAsDouble("input");
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromToDoubleFunction_error() {
		try {
			new CheckedExceptionCollector().fromToDoubleFunction(v -> {
				throw new AssertionError();
			}).applyAsDouble("input");
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromDoubleFunction_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		@SuppressWarnings("unchecked") ThrowingDoubleFunction<String> lambda = mock(ThrowingDoubleFunction.class);
		when(lambda.apply(1.0)).thenReturn("value");
		assertEquals("value", collector.fromDoubleFunction(lambda).apply(1.0));
		verify(lambda, only()).apply(1.0);
		assertTrue(collector.empty());
	}
	@Test public void fromDoubleFunction_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromDoubleFunction(v -> {
				throw new PrinterException();
			}).apply(1.0);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromDoubleFunction_runtime() {
		try {
			new CheckedExceptionCollector().fromDoubleFunction(v -> {
				throw new IllegalArgumentException();
			}).apply(1.0);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromDoubleFunction_error() {
		try {
			new CheckedExceptionCollector().fromDoubleFunction(v -> {
				throw new AssertionError();
			}).apply(1.0);
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromDoubleToIntFunction_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingDoubleToIntFunction lambda = mock(ThrowingDoubleToIntFunction.class);
		when(lambda.applyAsInt(1.0)).thenReturn(2);
		assertEquals(2, collector.fromDoubleToIntFunction(lambda).applyAsInt(1.0));
		verify(lambda, only()).applyAsInt(1.0);
		assertTrue(collector.empty());
	}
	@Test public void fromDoubleToIntFunction_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromDoubleToIntFunction(v -> {
				throw new PrinterException();
			}).applyAsInt(1.0);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromDoubleToIntFunction_runtime() {
		try {
			new CheckedExceptionCollector().fromDoubleToIntFunction(v -> {
				throw new IllegalArgumentException();
			}).applyAsInt(1.0);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromDoubleToIntFunction_error() {
		try {
			new CheckedExceptionCollector().fromDoubleToIntFunction(v -> {
				throw new AssertionError();
			}).applyAsInt(1.0);
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromDoubleToLongFunction_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingDoubleToLongFunction lambda = mock(ThrowingDoubleToLongFunction.class);
		when(lambda.applyAsLong(1.0)).thenReturn(2L);
		assertEquals(2L, collector.fromDoubleToLongFunction(lambda).applyAsLong(1.0));
		verify(lambda, only()).applyAsLong(1.0);
		assertTrue(collector.empty());
	}
	@Test public void fromDoubleToLongFunction_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromDoubleToLongFunction(v -> {
				throw new PrinterException();
			}).applyAsLong(1.0);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromDoubleToLongFunction_runtime() {
		try {
			new CheckedExceptionCollector().fromDoubleToLongFunction(v -> {
				throw new IllegalArgumentException();
			}).applyAsLong(1.0);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromDoubleToLongFunction_error() {
		try {
			new CheckedExceptionCollector().fromDoubleToLongFunction(v -> {
				throw new AssertionError();
			}).applyAsLong(1.0);
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromUnaryOperator_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		@SuppressWarnings("unchecked") ThrowingUnaryOperator<String> lambda = mock(ThrowingUnaryOperator.class);
		when(lambda.apply("input")).thenReturn("value");
		assertEquals("value", collector.fromUnaryOperator(lambda).apply("input"));
		verify(lambda, only()).apply("input");
		assertTrue(collector.empty());
	}
	@Test public void fromUnaryOperator_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromUnaryOperator(o -> {
				throw new PrinterException();
			}).apply("input");
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromUnaryOperator_runtime() {
		try {
			new CheckedExceptionCollector().fromUnaryOperator(o -> {
				throw new IllegalArgumentException();
			}).apply("input");
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromUnaryOperator_error() {
		try {
			new CheckedExceptionCollector().fromUnaryOperator(o -> {
				throw new AssertionError();
			}).apply("input");
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromIntUnaryOperator_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingIntUnaryOperator lambda = mock(ThrowingIntUnaryOperator.class);
		when(lambda.applyAsInt(1)).thenReturn(2);
		assertEquals(2, collector.fromIntUnaryOperator(lambda).applyAsInt(1));
		verify(lambda, only()).applyAsInt(1);
		assertTrue(collector.empty());
	}
	@Test public void fromIntUnaryOperator_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromIntUnaryOperator(o -> {
				throw new PrinterException();
			}).applyAsInt(1);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromIntUnaryOperator_runtime() {
		try {
			new CheckedExceptionCollector().fromIntUnaryOperator(o -> {
				throw new IllegalArgumentException();
			}).applyAsInt(1);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromIntUnaryOperator_error() {
		try {
			new CheckedExceptionCollector().fromIntUnaryOperator(o -> {
				throw new AssertionError();
			}).applyAsInt(1);
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromLongUnaryOperator_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingLongUnaryOperator lambda = mock(ThrowingLongUnaryOperator.class);
		when(lambda.applyAsLong(1L)).thenReturn(2L);
		assertEquals(2L, collector.fromLongUnaryOperator(lambda).applyAsLong(1L));
		verify(lambda, only()).applyAsLong(1L);
		assertTrue(collector.empty());
	}
	@Test public void fromLongUnaryOperator_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromLongUnaryOperator(o -> {
				throw new PrinterException();
			}).applyAsLong(1L);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromLongUnaryOperator_runtime() {
		try {
			new CheckedExceptionCollector().fromLongUnaryOperator(o -> {
				throw new IllegalArgumentException();
			}).applyAsLong(1L);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromLongUnaryOperator_error() {
		try {
			new CheckedExceptionCollector().fromLongUnaryOperator(o -> {
				throw new AssertionError();
			}).applyAsLong(1L);
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromDoubleUnaryOperator_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingDoubleUnaryOperator lambda = mock(ThrowingDoubleUnaryOperator.class);
		when(lambda.applyAsDouble(1.0)).thenReturn(2.0);
		assertEquals(2.0, collector.fromDoubleUnaryOperator(lambda).applyAsDouble(1.0), 0.1);
		verify(lambda, only()).applyAsDouble(1.0);
		assertTrue(collector.empty());
	}
	@Test public void fromDoubleUnaryOperator_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromDoubleUnaryOperator(o -> {
				throw new PrinterException();
			}).applyAsDouble(1.0);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromDoubleUnaryOperator_runtime() {
		try {
			new CheckedExceptionCollector().fromDoubleUnaryOperator(o -> {
				throw new IllegalArgumentException();
			}).applyAsDouble(1.0);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromDoubleUnaryOperator_error() {
		try {
			new CheckedExceptionCollector().fromDoubleUnaryOperator(o -> {
				throw new AssertionError();
			}).applyAsDouble(1.0);
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromBiFunction_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		@SuppressWarnings("unchecked") ThrowingBiFunction<String, String, String> lambda = mock(ThrowingBiFunction.class);
		when(lambda.apply("input1", "input2")).thenReturn("value");
		assertEquals("value", collector.fromBiFunction(lambda).apply("input1", "input2"));
		verify(lambda, only()).apply("input1", "input2");
		assertTrue(collector.empty());
	}
	@Test public void fromBiFunction_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromBiFunction((t, u) -> {
				throw new PrinterException();
			}).apply("input1", "input2");
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromBiFunction_runtime() {
		try {
			new CheckedExceptionCollector().fromBiFunction((t, u) -> {
				throw new IllegalArgumentException();
			}).apply("input1", "input2");
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromBiFunction_error() {
		try {
			new CheckedExceptionCollector().fromBiFunction((t, u) -> {
				throw new AssertionError();
			}).apply("input1", "input2");
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromToIntBiFunction_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		@SuppressWarnings("unchecked") ThrowingToIntBiFunction<String, String> lambda = mock(ThrowingToIntBiFunction.class);
		when(lambda.applyAsInt("input1", "input2")).thenReturn(2);
		assertEquals(2, collector.fromToIntBiFunction(lambda).applyAsInt("input1", "input2"));
		verify(lambda, only()).applyAsInt("input1", "input2");
		assertTrue(collector.empty());
	}
	@Test public void fromToIntBiFunction_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromToIntBiFunction((t, u) -> {
				throw new PrinterException();
			}).applyAsInt("input1", "input2");
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromToIntBiFunction_runtime() {
		try {
			new CheckedExceptionCollector().fromToIntBiFunction((t, u) -> {
				throw new IllegalArgumentException();
			}).applyAsInt("input1", "input2");
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromToIntBiFunction_error() {
		try {
			new CheckedExceptionCollector().fromToIntBiFunction((t, u) -> {
				throw new AssertionError();
			}).applyAsInt("input1", "input2");
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromToLongBiFunction_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		@SuppressWarnings("unchecked") ThrowingToLongBiFunction<String, String> lambda = mock(ThrowingToLongBiFunction.class);
		when(lambda.applyAsLong("input1", "input2")).thenReturn(2L);
		assertEquals(2L, collector.fromToLongBiFunction(lambda).applyAsLong("input1", "input2"));
		verify(lambda, only()).applyAsLong("input1", "input2");
		assertTrue(collector.empty());
	}
	@Test public void fromToLongBiFunction_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromToLongBiFunction((t, u) -> {
				throw new PrinterException();
			}).applyAsLong("input1", "input2");
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromToLongBiFunction_runtime() {
		try {
			new CheckedExceptionCollector().fromToLongBiFunction((t, u) -> {
				throw new IllegalArgumentException();
			}).applyAsLong("input1", "input2");
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromToLongBiFunction_error() {
		try {
			new CheckedExceptionCollector().fromToLongBiFunction((t, u) -> {
				throw new AssertionError();
			}).applyAsLong("input1", "input2");
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromToDoubleBiFunction_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		@SuppressWarnings("unchecked") ThrowingToDoubleBiFunction<String, String> lambda = mock(ThrowingToDoubleBiFunction.class);
		when(lambda.applyAsDouble("input1", "input2")).thenReturn(2.0);
		assertEquals(2.0, collector.fromToDoubleBiFunction(lambda).applyAsDouble("input1", "input2"), 0.1);
		verify(lambda, only()).applyAsDouble("input1", "input2");
		assertTrue(collector.empty());
	}
	@Test public void fromToDoubleBiFunction_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromToDoubleBiFunction((t, u) -> {
				throw new PrinterException();
			}).applyAsDouble("input1", "input2");
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromToDoubleBiFunction_runtime() {
		try {
			new CheckedExceptionCollector().fromToDoubleBiFunction((t, u) -> {
				throw new IllegalArgumentException();
			}).applyAsDouble("input1", "input2");
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromToDoubleBiFunction_error() {
		try {
			new CheckedExceptionCollector().fromToDoubleBiFunction((t, u) -> {
				throw new AssertionError();
			}).applyAsDouble("input1", "input2");
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromBinaryOperator_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		@SuppressWarnings("unchecked") ThrowingBinaryOperator<String> lambda = mock(ThrowingBinaryOperator.class);
		when(lambda.apply("input1", "input2")).thenReturn("value");
		assertEquals("value", collector.fromBinaryOperator(lambda).apply("input1", "input2"));
		verify(lambda, only()).apply("input1", "input2");
		assertTrue(collector.empty());
	}
	@Test public void fromBinaryOperator_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromBinaryOperator((l, r) -> {
				throw new PrinterException();
			}).apply("input1", "input2");
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromBinaryOperator_runtime() {
		try {
			new CheckedExceptionCollector().fromBinaryOperator((l, r) -> {
				throw new IllegalArgumentException();
			}).apply("input1", "input2");
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromBinaryOperator_error() {
		try {
			new CheckedExceptionCollector().fromBinaryOperator((l, r) -> {
				throw new AssertionError();
			}).apply("input1", "input2");
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromIntBinaryOperator_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingIntBinaryOperator lambda = mock(ThrowingIntBinaryOperator.class);
		when(lambda.applyAsInt(11, 12)).thenReturn(2);
		assertEquals(2, collector.fromIntBinaryOperator(lambda).applyAsInt(11, 12));
		verify(lambda, only()).applyAsInt(11, 12);
		assertTrue(collector.empty());
	}
	@Test public void fromIntBinaryOperator_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromIntBinaryOperator((l, r) -> {
				throw new PrinterException();
			}).applyAsInt(11, 12);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromIntBinaryOperator_runtime() {
		try {
			new CheckedExceptionCollector().fromIntBinaryOperator((l, r) -> {
				throw new IllegalArgumentException();
			}).applyAsInt(11, 12);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromIntBinaryOperator_error() {
		try {
			new CheckedExceptionCollector().fromIntBinaryOperator((l, r) -> {
				throw new AssertionError();
			}).applyAsInt(11, 12);
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromLongBinaryOperator_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingLongBinaryOperator lambda = mock(ThrowingLongBinaryOperator.class);
		when(lambda.applyAsLong(11L, 12L)).thenReturn(2L);
		assertEquals(2L, collector.fromLongBinaryOperator(lambda).applyAsLong(11L, 12L));
		verify(lambda, only()).applyAsLong(11L, 12L);
		assertTrue(collector.empty());
	}
	@Test public void fromLongBinaryOperator_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromLongBinaryOperator((l, r) -> {
				throw new PrinterException();
			}).applyAsLong(11L, 12L);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromLongBinaryOperator_runtime() {
		try {
			new CheckedExceptionCollector().fromLongBinaryOperator((l, r) -> {
				throw new IllegalArgumentException();
			}).applyAsLong(11L, 12L);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromLongBinaryOperator_error() {
		try {
			new CheckedExceptionCollector().fromLongBinaryOperator((l, r) -> {
				throw new AssertionError();
			}).applyAsLong(11L, 12L);
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void fromDoubleBinaryOperator_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingDoubleBinaryOperator lambda = mock(ThrowingDoubleBinaryOperator.class);
		when(lambda.applyAsDouble(1.1, 1.2)).thenReturn(2.0);
		assertEquals(2.0, collector.fromDoubleBinaryOperator(lambda).applyAsDouble(1.1, 1.2), 0.1);
		verify(lambda, only()).applyAsDouble(1.1, 1.2);
		assertTrue(collector.empty());
	}
	@Test public void fromDoubleBinaryOperator_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromDoubleBinaryOperator((l, r) -> {
				throw new PrinterException();
			}).applyAsDouble(1.1, 1.2);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromDoubleBinaryOperator_runtime() {
		try {
			new CheckedExceptionCollector().fromDoubleBinaryOperator((l, r) -> {
				throw new IllegalArgumentException();
			}).applyAsDouble(1.1, 1.2);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void fromDoubleBinaryOperator_error() {
		try {
			new CheckedExceptionCollector().fromDoubleBinaryOperator((l, r) -> {
				throw new AssertionError();
			}).applyAsDouble(1.1, 1.2);
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void comparator_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		@SuppressWarnings("unchecked") ThrowingComparator<String> lambda = mock(ThrowingComparator.class);
		when(lambda.compare("input1", "input2")).thenReturn(2);
		assertEquals(2, collector.comparator(lambda).compare("input1", "input2"));
		verify(lambda, only()).compare("input1", "input2");
		assertTrue(collector.empty());
	}
	@Test public void comparator_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.comparator((l, r) -> {
				throw new PrinterException();
			}).compare("input1", "input2");
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void comparator_runtime() {
		try {
			new CheckedExceptionCollector().comparator((l, r) -> {
				throw new IllegalArgumentException();
			}).compare("input1", "input2");
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	@Test public void comparator_error() {
		try {
			new CheckedExceptionCollector().comparator((l, r) -> {
				throw new AssertionError();
			}).compare("input1", "input2");
			fail();
		} catch (AssertionError e) {
		}
	}
	@Test public void run_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingRunnable lambda = mock(ThrowingRunnable.class);
		collector.run(lambda);
		verify(lambda, only()).run();
		assertTrue(collector.empty());
	}
	@Test public void run_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.run(() -> {
				throw new PrinterException();
			});
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void get_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		@SuppressWarnings("unchecked") ThrowingSupplier<String> lambda = mock(ThrowingSupplier.class);
		when(lambda.get()).thenReturn("value");
		assertEquals("value", collector.get(lambda));
		verify(lambda, only()).get();
		assertTrue(collector.empty());
	}
	@Test public void get_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.get(() -> {
				throw new PrinterException();
			});
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void getAsInt_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingIntSupplier lambda = mock(ThrowingIntSupplier.class);
		when(lambda.getAsInt()).thenReturn(2);
		assertEquals(2, collector.getAsInt(lambda));
		verify(lambda, only()).getAsInt();
		assertTrue(collector.empty());
	}
	@Test public void getAsInt_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.getAsInt(() -> {
				throw new PrinterException();
			});
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void getAsLong_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingLongSupplier lambda = mock(ThrowingLongSupplier.class);
		when(lambda.getAsLong()).thenReturn(2L);
		assertEquals(2L, collector.getAsLong(lambda));
		verify(lambda, only()).getAsLong();
		assertTrue(collector.empty());
	}
	@Test public void getAsLong_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.getAsLong(() -> {
				throw new PrinterException();
			});
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void getAsDouble_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingDoubleSupplier lambda = mock(ThrowingDoubleSupplier.class);
		when(lambda.getAsDouble()).thenReturn(2.0);
		assertEquals(2.0, collector.getAsDouble(lambda), 0.1);
		verify(lambda, only()).getAsDouble();
		assertTrue(collector.empty());
	}
	@Test public void getAsDouble_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.getAsDouble(() -> {
				throw new PrinterException();
			});
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void getAsBoolean_complete() throws Throwable {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingBooleanSupplier lambda = mock(ThrowingBooleanSupplier.class);
		when(lambda.getAsBoolean()).thenReturn(true);
		assertEquals(true, collector.getAsBoolean(lambda));
		verify(lambda, only()).getAsBoolean();
		assertTrue(collector.empty());
	}
	@Test public void getAsBoolean_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.getAsBoolean(() -> {
				throw new PrinterException();
			});
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
}
