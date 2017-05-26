// Part of NoException: https://noexception.machinezoo.com
package com.machinezoo.noexception;

import static org.hamcrest.core.IsInstanceOf.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.awt.print.*;
import org.junit.*;
import com.machinezoo.noexception.throwing.*;
import lombok.*;

public class CheckedExceptionHandlerTest {
	@Test @SneakyThrows public void runnable_complete() {
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
	@Test @SneakyThrows public void supplier_complete() {
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
	@Test @SneakyThrows public void fromIntSupplier_complete() {
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
	@Test @SneakyThrows public void fromLongSupplier_complete() {
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
	@Test @SneakyThrows public void fromDoubleSupplier_complete() {
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
	@Test @SneakyThrows public void fromBooleanSupplier_complete() {
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
	@Test @SneakyThrows public void consumer_complete() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		@SuppressWarnings("unchecked") ThrowingConsumer<String> lambda = mock(ThrowingConsumer.class);
		collector.consumer(lambda).accept("input");
		verify(lambda, only()).accept("input");
		assertTrue(collector.empty());
	}
	@Test public void consumer_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.consumer(x -> {
				throw new PrinterException();
			}).accept("input");
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromIntConsumer_complete() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingIntConsumer lambda = mock(ThrowingIntConsumer.class);
		collector.fromIntConsumer(lambda).accept(2);
		verify(lambda, only()).accept(2);
		assertTrue(collector.empty());
	}
	@Test public void fromIntConsumer_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromIntConsumer(x -> {
				throw new PrinterException();
			}).accept(2);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromLongConsumer_complete() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingLongConsumer lambda = mock(ThrowingLongConsumer.class);
		collector.fromLongConsumer(lambda).accept(2);
		verify(lambda, only()).accept(2);
		assertTrue(collector.empty());
	}
	@Test public void fromLongConsumer_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromLongConsumer(x -> {
				throw new PrinterException();
			}).accept(2);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromDoubleConsumer_complete() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingDoubleConsumer lambda = mock(ThrowingDoubleConsumer.class);
		collector.fromDoubleConsumer(lambda).accept(2);
		verify(lambda, only()).accept(2);
		assertTrue(collector.empty());
	}
	@Test public void fromDoubleConsumer_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromDoubleConsumer(x -> {
				throw new PrinterException();
			}).accept(2);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromBiConsumer_complete() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		@SuppressWarnings("unchecked") ThrowingBiConsumer<String, String> lambda = mock(ThrowingBiConsumer.class);
		collector.fromBiConsumer(lambda).accept("input1", "input2");
		verify(lambda, only()).accept("input1", "input2");
		assertTrue(collector.empty());
	}
	@Test public void fromBiConsumer_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromBiConsumer((x, y) -> {
				throw new PrinterException();
			}).accept("input1", "input2");
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromObjIntConsumer_complete() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		@SuppressWarnings("unchecked") ThrowingObjIntConsumer<String> lambda = mock(ThrowingObjIntConsumer.class);
		collector.fromObjIntConsumer(lambda).accept("input", 2);
		verify(lambda, only()).accept("input", 2);
		assertTrue(collector.empty());
	}
	@Test public void fromObjIntConsumer_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromObjIntConsumer((x, y) -> {
				throw new PrinterException();
			}).accept("input", 2);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromObjLongConsumer_complete() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		@SuppressWarnings("unchecked") ThrowingObjLongConsumer<String> lambda = mock(ThrowingObjLongConsumer.class);
		collector.fromObjLongConsumer(lambda).accept("input", 2);
		verify(lambda, only()).accept("input", 2);
		assertTrue(collector.empty());
	}
	@Test public void fromObjLongConsumer_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromObjLongConsumer((x, y) -> {
				throw new PrinterException();
			}).accept("input", 2);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromObjDoubleConsumer_complete() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		@SuppressWarnings("unchecked") ThrowingObjDoubleConsumer<String> lambda = mock(ThrowingObjDoubleConsumer.class);
		collector.fromObjDoubleConsumer(lambda).accept("input", 2);
		verify(lambda, only()).accept("input", 2);
		assertTrue(collector.empty());
	}
	@Test public void fromObjDoubleConsumer_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromObjDoubleConsumer((x, y) -> {
				throw new PrinterException();
			}).accept("input", 2);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void predicate_complete() {
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
			collector.predicate(x -> {
				throw new PrinterException();
			}).test("input");
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromIntPredicate_complete() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingIntPredicate lambda = mock(ThrowingIntPredicate.class);
		when(lambda.test(2)).thenReturn(true);
		assertEquals(true, collector.fromIntPredicate(lambda).test(2));
		verify(lambda, only()).test(2);
		assertTrue(collector.empty());
	}
	@Test public void fromIntPredicate_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromIntPredicate(x -> {
				throw new PrinterException();
			}).test(2);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromLongPredicate_complete() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingLongPredicate lambda = mock(ThrowingLongPredicate.class);
		when(lambda.test(2)).thenReturn(true);
		assertEquals(true, collector.fromLongPredicate(lambda).test(2));
		verify(lambda, only()).test(2);
		assertTrue(collector.empty());
	}
	@Test public void fromLongPredicate_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromLongPredicate(x -> {
				throw new PrinterException();
			}).test(2);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromDoublePredicate_complete() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingDoublePredicate lambda = mock(ThrowingDoublePredicate.class);
		when(lambda.test(2)).thenReturn(true);
		assertEquals(true, collector.fromDoublePredicate(lambda).test(2));
		verify(lambda, only()).test(2);
		assertTrue(collector.empty());
	}
	@Test public void fromDoublePredicate_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromDoublePredicate(x -> {
				throw new PrinterException();
			}).test(2);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromBiPredicate_complete() {
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
			collector.fromBiPredicate((x, y) -> {
				throw new PrinterException();
			}).test("input1", "input2");
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void function_complete() {
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
			collector.function(x -> {
				throw new PrinterException();
			}).apply("input");
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromToIntFunction_complete() {
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
			collector.fromToIntFunction(x -> {
				throw new PrinterException();
			}).applyAsInt("input");
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromIntFunction_complete() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		@SuppressWarnings("unchecked") ThrowingIntFunction<String> lambda = mock(ThrowingIntFunction.class);
		when(lambda.apply(2)).thenReturn("value");
		assertEquals("value", collector.fromIntFunction(lambda).apply(2));
		verify(lambda, only()).apply(2);
		assertTrue(collector.empty());
	}
	@Test public void fromIntFunction_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromIntFunction(x -> {
				throw new PrinterException();
			}).apply(2);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromIntToLongFunction_complete() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingIntToLongFunction lambda = mock(ThrowingIntToLongFunction.class);
		when(lambda.applyAsLong(2)).thenReturn(2L);
		assertEquals(2L, collector.fromIntToLongFunction(lambda).applyAsLong(2));
		verify(lambda, only()).applyAsLong(2);
		assertTrue(collector.empty());
	}
	@Test public void fromIntToLongFunction_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromIntToLongFunction(x -> {
				throw new PrinterException();
			}).applyAsLong(2);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromIntToDoubleFunction_complete() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingIntToDoubleFunction lambda = mock(ThrowingIntToDoubleFunction.class);
		when(lambda.applyAsDouble(2)).thenReturn(2.0);
		assertEquals(2.0, collector.fromIntToDoubleFunction(lambda).applyAsDouble(2), 0.1);
		verify(lambda, only()).applyAsDouble(2);
		assertTrue(collector.empty());
	}
	@Test public void fromIntToDoubleFunction_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromIntToDoubleFunction(x -> {
				throw new PrinterException();
			}).applyAsDouble(2);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromToLongFunction_complete() {
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
			collector.fromToLongFunction(x -> {
				throw new PrinterException();
			}).applyAsLong("input");
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromLongFunction_complete() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		@SuppressWarnings("unchecked") ThrowingLongFunction<String> lambda = mock(ThrowingLongFunction.class);
		when(lambda.apply(2)).thenReturn("value");
		assertEquals("value", collector.fromLongFunction(lambda).apply(2));
		verify(lambda, only()).apply(2);
		assertTrue(collector.empty());
	}
	@Test public void fromLongFunction_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromLongFunction(x -> {
				throw new PrinterException();
			}).apply(2);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromLongToIntFunction_complete() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingLongToIntFunction lambda = mock(ThrowingLongToIntFunction.class);
		when(lambda.applyAsInt(2)).thenReturn(2);
		assertEquals(2, collector.fromLongToIntFunction(lambda).applyAsInt(2));
		verify(lambda, only()).applyAsInt(2);
		assertTrue(collector.empty());
	}
	@Test public void fromLongToIntFunction_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromLongToIntFunction(x -> {
				throw new PrinterException();
			}).applyAsInt(2);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromLongToDoubleFunction_complete() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingLongToDoubleFunction lambda = mock(ThrowingLongToDoubleFunction.class);
		when(lambda.applyAsDouble(2)).thenReturn(2.0);
		assertEquals(2.0, collector.fromLongToDoubleFunction(lambda).applyAsDouble(2), 0.1);
		verify(lambda, only()).applyAsDouble(2);
		assertTrue(collector.empty());
	}
	@Test public void fromLongToDoubleFunction_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromLongToDoubleFunction(x -> {
				throw new PrinterException();
			}).applyAsDouble(2);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromToDoubleFunction_complete() {
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
			collector.fromToDoubleFunction(x -> {
				throw new PrinterException();
			}).applyAsDouble("input");
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromDoubleFunction_complete() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		@SuppressWarnings("unchecked") ThrowingDoubleFunction<String> lambda = mock(ThrowingDoubleFunction.class);
		when(lambda.apply(2)).thenReturn("value");
		assertEquals("value", collector.fromDoubleFunction(lambda).apply(2));
		verify(lambda, only()).apply(2);
		assertTrue(collector.empty());
	}
	@Test public void fromDoubleFunction_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromDoubleFunction(x -> {
				throw new PrinterException();
			}).apply(2);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromDoubleToIntFunction_complete() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingDoubleToIntFunction lambda = mock(ThrowingDoubleToIntFunction.class);
		when(lambda.applyAsInt(2)).thenReturn(2);
		assertEquals(2, collector.fromDoubleToIntFunction(lambda).applyAsInt(2));
		verify(lambda, only()).applyAsInt(2);
		assertTrue(collector.empty());
	}
	@Test public void fromDoubleToIntFunction_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromDoubleToIntFunction(x -> {
				throw new PrinterException();
			}).applyAsInt(2);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromDoubleToLongFunction_complete() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingDoubleToLongFunction lambda = mock(ThrowingDoubleToLongFunction.class);
		when(lambda.applyAsLong(2)).thenReturn(2L);
		assertEquals(2L, collector.fromDoubleToLongFunction(lambda).applyAsLong(2));
		verify(lambda, only()).applyAsLong(2);
		assertTrue(collector.empty());
	}
	@Test public void fromDoubleToLongFunction_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromDoubleToLongFunction(x -> {
				throw new PrinterException();
			}).applyAsLong(2);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromUnaryOperator_complete() {
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
			collector.fromUnaryOperator(x -> {
				throw new PrinterException();
			}).apply("input");
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromIntUnaryOperator_complete() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingIntUnaryOperator lambda = mock(ThrowingIntUnaryOperator.class);
		when(lambda.applyAsInt(2)).thenReturn(2);
		assertEquals(2, collector.fromIntUnaryOperator(lambda).applyAsInt(2));
		verify(lambda, only()).applyAsInt(2);
		assertTrue(collector.empty());
	}
	@Test public void fromIntUnaryOperator_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromIntUnaryOperator(x -> {
				throw new PrinterException();
			}).applyAsInt(2);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromLongUnaryOperator_complete() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingLongUnaryOperator lambda = mock(ThrowingLongUnaryOperator.class);
		when(lambda.applyAsLong(2)).thenReturn(2L);
		assertEquals(2L, collector.fromLongUnaryOperator(lambda).applyAsLong(2));
		verify(lambda, only()).applyAsLong(2);
		assertTrue(collector.empty());
	}
	@Test public void fromLongUnaryOperator_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromLongUnaryOperator(x -> {
				throw new PrinterException();
			}).applyAsLong(2);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromDoubleUnaryOperator_complete() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingDoubleUnaryOperator lambda = mock(ThrowingDoubleUnaryOperator.class);
		when(lambda.applyAsDouble(2)).thenReturn(2.0);
		assertEquals(2.0, collector.fromDoubleUnaryOperator(lambda).applyAsDouble(2), 0.1);
		verify(lambda, only()).applyAsDouble(2);
		assertTrue(collector.empty());
	}
	@Test public void fromDoubleUnaryOperator_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromDoubleUnaryOperator(x -> {
				throw new PrinterException();
			}).applyAsDouble(2);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromBiFunction_complete() {
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
			collector.fromBiFunction((x, y) -> {
				throw new PrinterException();
			}).apply("input1", "input2");
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromToIntBiFunction_complete() {
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
			collector.fromToIntBiFunction((x, y) -> {
				throw new PrinterException();
			}).applyAsInt("input1", "input2");
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromToLongBiFunction_complete() {
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
			collector.fromToLongBiFunction((x, y) -> {
				throw new PrinterException();
			}).applyAsLong("input1", "input2");
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromToDoubleBiFunction_complete() {
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
			collector.fromToDoubleBiFunction((x, y) -> {
				throw new PrinterException();
			}).applyAsDouble("input1", "input2");
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromBinaryOperator_complete() {
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
			collector.fromBinaryOperator((x, y) -> {
				throw new PrinterException();
			}).apply("input1", "input2");
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromIntBinaryOperator_complete() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingIntBinaryOperator lambda = mock(ThrowingIntBinaryOperator.class);
		when(lambda.applyAsInt(21, 22)).thenReturn(2);
		assertEquals(2, collector.fromIntBinaryOperator(lambda).applyAsInt(21, 22));
		verify(lambda, only()).applyAsInt(21, 22);
		assertTrue(collector.empty());
	}
	@Test public void fromIntBinaryOperator_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromIntBinaryOperator((x, y) -> {
				throw new PrinterException();
			}).applyAsInt(21, 22);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromLongBinaryOperator_complete() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingLongBinaryOperator lambda = mock(ThrowingLongBinaryOperator.class);
		when(lambda.applyAsLong(21, 22)).thenReturn(2L);
		assertEquals(2L, collector.fromLongBinaryOperator(lambda).applyAsLong(21, 22));
		verify(lambda, only()).applyAsLong(21, 22);
		assertTrue(collector.empty());
	}
	@Test public void fromLongBinaryOperator_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromLongBinaryOperator((x, y) -> {
				throw new PrinterException();
			}).applyAsLong(21, 22);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void fromDoubleBinaryOperator_complete() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		ThrowingDoubleBinaryOperator lambda = mock(ThrowingDoubleBinaryOperator.class);
		when(lambda.applyAsDouble(21, 22)).thenReturn(2.0);
		assertEquals(2.0, collector.fromDoubleBinaryOperator(lambda).applyAsDouble(21, 22), 0.1);
		verify(lambda, only()).applyAsDouble(21, 22);
		assertTrue(collector.empty());
	}
	@Test public void fromDoubleBinaryOperator_exception() {
		CheckedExceptionCollector collector = new CheckedExceptionCollector();
		try {
			collector.fromDoubleBinaryOperator((x, y) -> {
				throw new PrinterException();
			}).applyAsDouble(21, 22);
			fail();
		} catch (CollectedException e) {
		}
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test @SneakyThrows public void run_complete() {
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
	@Test @SneakyThrows public void get_complete() {
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
	@Test @SneakyThrows public void getAsInt_complete() {
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
	@Test @SneakyThrows public void getAsLong_complete() {
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
	@Test @SneakyThrows public void getAsDouble_complete() {
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
	@Test @SneakyThrows public void getAsBoolean_complete() {
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
