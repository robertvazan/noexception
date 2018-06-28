// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.sh instead.
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
