// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.awt.print.*;
import org.junit.jupiter.api.*;
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
		assertThrows(CollectedException.class, () -> collector.runnable(() -> {
			throw new PrinterException();
		}).run());
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void runnable_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().runnable(() -> {
			throw new IllegalArgumentException();
		}).run());
	}
	@Test public void runnable_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().runnable(() -> {
			throw new AssertionError();
		}).run());
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
		assertThrows(CollectedException.class, () -> collector.supplier(() -> {
			throw new PrinterException();
		}).get());
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void supplier_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().supplier(() -> {
			throw new IllegalArgumentException();
		}).get());
	}
	@Test public void supplier_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().supplier(() -> {
			throw new AssertionError();
		}).get());
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
		assertThrows(CollectedException.class, () -> collector.fromIntSupplier(() -> {
			throw new PrinterException();
		}).getAsInt());
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromIntSupplier_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromIntSupplier(() -> {
			throw new IllegalArgumentException();
		}).getAsInt());
	}
	@Test public void fromIntSupplier_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromIntSupplier(() -> {
			throw new AssertionError();
		}).getAsInt());
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
		assertThrows(CollectedException.class, () -> collector.fromLongSupplier(() -> {
			throw new PrinterException();
		}).getAsLong());
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromLongSupplier_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromLongSupplier(() -> {
			throw new IllegalArgumentException();
		}).getAsLong());
	}
	@Test public void fromLongSupplier_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromLongSupplier(() -> {
			throw new AssertionError();
		}).getAsLong());
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
		assertThrows(CollectedException.class, () -> collector.fromDoubleSupplier(() -> {
			throw new PrinterException();
		}).getAsDouble());
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromDoubleSupplier_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromDoubleSupplier(() -> {
			throw new IllegalArgumentException();
		}).getAsDouble());
	}
	@Test public void fromDoubleSupplier_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromDoubleSupplier(() -> {
			throw new AssertionError();
		}).getAsDouble());
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
		assertThrows(CollectedException.class, () -> collector.fromBooleanSupplier(() -> {
			throw new PrinterException();
		}).getAsBoolean());
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromBooleanSupplier_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromBooleanSupplier(() -> {
			throw new IllegalArgumentException();
		}).getAsBoolean());
	}
	@Test public void fromBooleanSupplier_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromBooleanSupplier(() -> {
			throw new AssertionError();
		}).getAsBoolean());
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
		assertThrows(CollectedException.class, () -> collector.consumer(t -> {
			throw new PrinterException();
		}).accept("input"));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void consumer_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().consumer(t -> {
			throw new IllegalArgumentException();
		}).accept("input"));
	}
	@Test public void consumer_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().consumer(t -> {
			throw new AssertionError();
		}).accept("input"));
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
		assertThrows(CollectedException.class, () -> collector.fromIntConsumer(v -> {
			throw new PrinterException();
		}).accept(1));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromIntConsumer_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromIntConsumer(v -> {
			throw new IllegalArgumentException();
		}).accept(1));
	}
	@Test public void fromIntConsumer_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromIntConsumer(v -> {
			throw new AssertionError();
		}).accept(1));
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
		assertThrows(CollectedException.class, () -> collector.fromLongConsumer(v -> {
			throw new PrinterException();
		}).accept(1L));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromLongConsumer_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromLongConsumer(v -> {
			throw new IllegalArgumentException();
		}).accept(1L));
	}
	@Test public void fromLongConsumer_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromLongConsumer(v -> {
			throw new AssertionError();
		}).accept(1L));
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
		assertThrows(CollectedException.class, () -> collector.fromDoubleConsumer(v -> {
			throw new PrinterException();
		}).accept(1.0));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromDoubleConsumer_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromDoubleConsumer(v -> {
			throw new IllegalArgumentException();
		}).accept(1.0));
	}
	@Test public void fromDoubleConsumer_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromDoubleConsumer(v -> {
			throw new AssertionError();
		}).accept(1.0));
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
		assertThrows(CollectedException.class, () -> collector.fromBiConsumer((t, u) -> {
			throw new PrinterException();
		}).accept("input1", "input2"));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromBiConsumer_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromBiConsumer((t, u) -> {
			throw new IllegalArgumentException();
		}).accept("input1", "input2"));
	}
	@Test public void fromBiConsumer_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromBiConsumer((t, u) -> {
			throw new AssertionError();
		}).accept("input1", "input2"));
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
		assertThrows(CollectedException.class, () -> collector.fromObjIntConsumer((t, v) -> {
			throw new PrinterException();
		}).accept("input", 1));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromObjIntConsumer_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromObjIntConsumer((t, v) -> {
			throw new IllegalArgumentException();
		}).accept("input", 1));
	}
	@Test public void fromObjIntConsumer_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromObjIntConsumer((t, v) -> {
			throw new AssertionError();
		}).accept("input", 1));
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
		assertThrows(CollectedException.class, () -> collector.fromObjLongConsumer((t, v) -> {
			throw new PrinterException();
		}).accept("input", 1L));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromObjLongConsumer_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromObjLongConsumer((t, v) -> {
			throw new IllegalArgumentException();
		}).accept("input", 1L));
	}
	@Test public void fromObjLongConsumer_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromObjLongConsumer((t, v) -> {
			throw new AssertionError();
		}).accept("input", 1L));
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
		assertThrows(CollectedException.class, () -> collector.fromObjDoubleConsumer((t, v) -> {
			throw new PrinterException();
		}).accept("input", 1.0));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromObjDoubleConsumer_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromObjDoubleConsumer((t, v) -> {
			throw new IllegalArgumentException();
		}).accept("input", 1.0));
	}
	@Test public void fromObjDoubleConsumer_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromObjDoubleConsumer((t, v) -> {
			throw new AssertionError();
		}).accept("input", 1.0));
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
		assertThrows(CollectedException.class, () -> collector.predicate(t -> {
			throw new PrinterException();
		}).test("input"));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void predicate_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().predicate(t -> {
			throw new IllegalArgumentException();
		}).test("input"));
	}
	@Test public void predicate_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().predicate(t -> {
			throw new AssertionError();
		}).test("input"));
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
		assertThrows(CollectedException.class, () -> collector.fromIntPredicate(v -> {
			throw new PrinterException();
		}).test(1));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromIntPredicate_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromIntPredicate(v -> {
			throw new IllegalArgumentException();
		}).test(1));
	}
	@Test public void fromIntPredicate_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromIntPredicate(v -> {
			throw new AssertionError();
		}).test(1));
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
		assertThrows(CollectedException.class, () -> collector.fromLongPredicate(v -> {
			throw new PrinterException();
		}).test(1L));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromLongPredicate_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromLongPredicate(v -> {
			throw new IllegalArgumentException();
		}).test(1L));
	}
	@Test public void fromLongPredicate_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromLongPredicate(v -> {
			throw new AssertionError();
		}).test(1L));
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
		assertThrows(CollectedException.class, () -> collector.fromDoublePredicate(v -> {
			throw new PrinterException();
		}).test(1.0));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromDoublePredicate_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromDoublePredicate(v -> {
			throw new IllegalArgumentException();
		}).test(1.0));
	}
	@Test public void fromDoublePredicate_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromDoublePredicate(v -> {
			throw new AssertionError();
		}).test(1.0));
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
		assertThrows(CollectedException.class, () -> collector.fromBiPredicate((t, u) -> {
			throw new PrinterException();
		}).test("input1", "input2"));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromBiPredicate_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromBiPredicate((t, u) -> {
			throw new IllegalArgumentException();
		}).test("input1", "input2"));
	}
	@Test public void fromBiPredicate_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromBiPredicate((t, u) -> {
			throw new AssertionError();
		}).test("input1", "input2"));
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
		assertThrows(CollectedException.class, () -> collector.function(t -> {
			throw new PrinterException();
		}).apply("input"));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void function_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().function(t -> {
			throw new IllegalArgumentException();
		}).apply("input"));
	}
	@Test public void function_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().function(t -> {
			throw new AssertionError();
		}).apply("input"));
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
		assertThrows(CollectedException.class, () -> collector.fromToIntFunction(v -> {
			throw new PrinterException();
		}).applyAsInt("input"));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromToIntFunction_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromToIntFunction(v -> {
			throw new IllegalArgumentException();
		}).applyAsInt("input"));
	}
	@Test public void fromToIntFunction_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromToIntFunction(v -> {
			throw new AssertionError();
		}).applyAsInt("input"));
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
		assertThrows(CollectedException.class, () -> collector.fromIntFunction(v -> {
			throw new PrinterException();
		}).apply(1));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromIntFunction_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromIntFunction(v -> {
			throw new IllegalArgumentException();
		}).apply(1));
	}
	@Test public void fromIntFunction_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromIntFunction(v -> {
			throw new AssertionError();
		}).apply(1));
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
		assertThrows(CollectedException.class, () -> collector.fromIntToLongFunction(v -> {
			throw new PrinterException();
		}).applyAsLong(1));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromIntToLongFunction_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromIntToLongFunction(v -> {
			throw new IllegalArgumentException();
		}).applyAsLong(1));
	}
	@Test public void fromIntToLongFunction_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromIntToLongFunction(v -> {
			throw new AssertionError();
		}).applyAsLong(1));
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
		assertThrows(CollectedException.class, () -> collector.fromIntToDoubleFunction(v -> {
			throw new PrinterException();
		}).applyAsDouble(1));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromIntToDoubleFunction_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromIntToDoubleFunction(v -> {
			throw new IllegalArgumentException();
		}).applyAsDouble(1));
	}
	@Test public void fromIntToDoubleFunction_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromIntToDoubleFunction(v -> {
			throw new AssertionError();
		}).applyAsDouble(1));
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
		assertThrows(CollectedException.class, () -> collector.fromToLongFunction(v -> {
			throw new PrinterException();
		}).applyAsLong("input"));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromToLongFunction_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromToLongFunction(v -> {
			throw new IllegalArgumentException();
		}).applyAsLong("input"));
	}
	@Test public void fromToLongFunction_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromToLongFunction(v -> {
			throw new AssertionError();
		}).applyAsLong("input"));
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
		assertThrows(CollectedException.class, () -> collector.fromLongFunction(v -> {
			throw new PrinterException();
		}).apply(1L));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromLongFunction_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromLongFunction(v -> {
			throw new IllegalArgumentException();
		}).apply(1L));
	}
	@Test public void fromLongFunction_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromLongFunction(v -> {
			throw new AssertionError();
		}).apply(1L));
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
		assertThrows(CollectedException.class, () -> collector.fromLongToIntFunction(v -> {
			throw new PrinterException();
		}).applyAsInt(1L));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromLongToIntFunction_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromLongToIntFunction(v -> {
			throw new IllegalArgumentException();
		}).applyAsInt(1L));
	}
	@Test public void fromLongToIntFunction_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromLongToIntFunction(v -> {
			throw new AssertionError();
		}).applyAsInt(1L));
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
		assertThrows(CollectedException.class, () -> collector.fromLongToDoubleFunction(v -> {
			throw new PrinterException();
		}).applyAsDouble(1L));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromLongToDoubleFunction_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromLongToDoubleFunction(v -> {
			throw new IllegalArgumentException();
		}).applyAsDouble(1L));
	}
	@Test public void fromLongToDoubleFunction_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromLongToDoubleFunction(v -> {
			throw new AssertionError();
		}).applyAsDouble(1L));
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
		assertThrows(CollectedException.class, () -> collector.fromToDoubleFunction(v -> {
			throw new PrinterException();
		}).applyAsDouble("input"));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromToDoubleFunction_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromToDoubleFunction(v -> {
			throw new IllegalArgumentException();
		}).applyAsDouble("input"));
	}
	@Test public void fromToDoubleFunction_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromToDoubleFunction(v -> {
			throw new AssertionError();
		}).applyAsDouble("input"));
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
		assertThrows(CollectedException.class, () -> collector.fromDoubleFunction(v -> {
			throw new PrinterException();
		}).apply(1.0));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromDoubleFunction_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromDoubleFunction(v -> {
			throw new IllegalArgumentException();
		}).apply(1.0));
	}
	@Test public void fromDoubleFunction_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromDoubleFunction(v -> {
			throw new AssertionError();
		}).apply(1.0));
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
		assertThrows(CollectedException.class, () -> collector.fromDoubleToIntFunction(v -> {
			throw new PrinterException();
		}).applyAsInt(1.0));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromDoubleToIntFunction_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromDoubleToIntFunction(v -> {
			throw new IllegalArgumentException();
		}).applyAsInt(1.0));
	}
	@Test public void fromDoubleToIntFunction_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromDoubleToIntFunction(v -> {
			throw new AssertionError();
		}).applyAsInt(1.0));
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
		assertThrows(CollectedException.class, () -> collector.fromDoubleToLongFunction(v -> {
			throw new PrinterException();
		}).applyAsLong(1.0));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromDoubleToLongFunction_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromDoubleToLongFunction(v -> {
			throw new IllegalArgumentException();
		}).applyAsLong(1.0));
	}
	@Test public void fromDoubleToLongFunction_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromDoubleToLongFunction(v -> {
			throw new AssertionError();
		}).applyAsLong(1.0));
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
		assertThrows(CollectedException.class, () -> collector.fromUnaryOperator(o -> {
			throw new PrinterException();
		}).apply("input"));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromUnaryOperator_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromUnaryOperator(o -> {
			throw new IllegalArgumentException();
		}).apply("input"));
	}
	@Test public void fromUnaryOperator_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromUnaryOperator(o -> {
			throw new AssertionError();
		}).apply("input"));
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
		assertThrows(CollectedException.class, () -> collector.fromIntUnaryOperator(o -> {
			throw new PrinterException();
		}).applyAsInt(1));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromIntUnaryOperator_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromIntUnaryOperator(o -> {
			throw new IllegalArgumentException();
		}).applyAsInt(1));
	}
	@Test public void fromIntUnaryOperator_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromIntUnaryOperator(o -> {
			throw new AssertionError();
		}).applyAsInt(1));
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
		assertThrows(CollectedException.class, () -> collector.fromLongUnaryOperator(o -> {
			throw new PrinterException();
		}).applyAsLong(1L));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromLongUnaryOperator_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromLongUnaryOperator(o -> {
			throw new IllegalArgumentException();
		}).applyAsLong(1L));
	}
	@Test public void fromLongUnaryOperator_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromLongUnaryOperator(o -> {
			throw new AssertionError();
		}).applyAsLong(1L));
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
		assertThrows(CollectedException.class, () -> collector.fromDoubleUnaryOperator(o -> {
			throw new PrinterException();
		}).applyAsDouble(1.0));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromDoubleUnaryOperator_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromDoubleUnaryOperator(o -> {
			throw new IllegalArgumentException();
		}).applyAsDouble(1.0));
	}
	@Test public void fromDoubleUnaryOperator_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromDoubleUnaryOperator(o -> {
			throw new AssertionError();
		}).applyAsDouble(1.0));
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
		assertThrows(CollectedException.class, () -> collector.fromBiFunction((t, u) -> {
			throw new PrinterException();
		}).apply("input1", "input2"));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromBiFunction_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromBiFunction((t, u) -> {
			throw new IllegalArgumentException();
		}).apply("input1", "input2"));
	}
	@Test public void fromBiFunction_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromBiFunction((t, u) -> {
			throw new AssertionError();
		}).apply("input1", "input2"));
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
		assertThrows(CollectedException.class, () -> collector.fromToIntBiFunction((t, u) -> {
			throw new PrinterException();
		}).applyAsInt("input1", "input2"));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromToIntBiFunction_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromToIntBiFunction((t, u) -> {
			throw new IllegalArgumentException();
		}).applyAsInt("input1", "input2"));
	}
	@Test public void fromToIntBiFunction_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromToIntBiFunction((t, u) -> {
			throw new AssertionError();
		}).applyAsInt("input1", "input2"));
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
		assertThrows(CollectedException.class, () -> collector.fromToLongBiFunction((t, u) -> {
			throw new PrinterException();
		}).applyAsLong("input1", "input2"));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromToLongBiFunction_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromToLongBiFunction((t, u) -> {
			throw new IllegalArgumentException();
		}).applyAsLong("input1", "input2"));
	}
	@Test public void fromToLongBiFunction_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromToLongBiFunction((t, u) -> {
			throw new AssertionError();
		}).applyAsLong("input1", "input2"));
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
		assertThrows(CollectedException.class, () -> collector.fromToDoubleBiFunction((t, u) -> {
			throw new PrinterException();
		}).applyAsDouble("input1", "input2"));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromToDoubleBiFunction_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromToDoubleBiFunction((t, u) -> {
			throw new IllegalArgumentException();
		}).applyAsDouble("input1", "input2"));
	}
	@Test public void fromToDoubleBiFunction_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromToDoubleBiFunction((t, u) -> {
			throw new AssertionError();
		}).applyAsDouble("input1", "input2"));
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
		assertThrows(CollectedException.class, () -> collector.fromBinaryOperator((l, r) -> {
			throw new PrinterException();
		}).apply("input1", "input2"));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromBinaryOperator_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromBinaryOperator((l, r) -> {
			throw new IllegalArgumentException();
		}).apply("input1", "input2"));
	}
	@Test public void fromBinaryOperator_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromBinaryOperator((l, r) -> {
			throw new AssertionError();
		}).apply("input1", "input2"));
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
		assertThrows(CollectedException.class, () -> collector.fromIntBinaryOperator((l, r) -> {
			throw new PrinterException();
		}).applyAsInt(11, 12));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromIntBinaryOperator_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromIntBinaryOperator((l, r) -> {
			throw new IllegalArgumentException();
		}).applyAsInt(11, 12));
	}
	@Test public void fromIntBinaryOperator_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromIntBinaryOperator((l, r) -> {
			throw new AssertionError();
		}).applyAsInt(11, 12));
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
		assertThrows(CollectedException.class, () -> collector.fromLongBinaryOperator((l, r) -> {
			throw new PrinterException();
		}).applyAsLong(11L, 12L));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromLongBinaryOperator_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromLongBinaryOperator((l, r) -> {
			throw new IllegalArgumentException();
		}).applyAsLong(11L, 12L));
	}
	@Test public void fromLongBinaryOperator_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromLongBinaryOperator((l, r) -> {
			throw new AssertionError();
		}).applyAsLong(11L, 12L));
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
		assertThrows(CollectedException.class, () -> collector.fromDoubleBinaryOperator((l, r) -> {
			throw new PrinterException();
		}).applyAsDouble(1.1, 1.2));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void fromDoubleBinaryOperator_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().fromDoubleBinaryOperator((l, r) -> {
			throw new IllegalArgumentException();
		}).applyAsDouble(1.1, 1.2));
	}
	@Test public void fromDoubleBinaryOperator_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().fromDoubleBinaryOperator((l, r) -> {
			throw new AssertionError();
		}).applyAsDouble(1.1, 1.2));
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
		assertThrows(CollectedException.class, () -> collector.comparator((l, r) -> {
			throw new PrinterException();
		}).compare("input1", "input2"));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
	@Test public void comparator_runtime() {
		assertThrows(IllegalArgumentException.class, () -> new CheckedExceptionCollector().comparator((l, r) -> {
			throw new IllegalArgumentException();
		}).compare("input1", "input2"));
	}
	@Test public void comparator_error() {
		assertThrows(AssertionError.class, () -> new CheckedExceptionCollector().comparator((l, r) -> {
			throw new AssertionError();
		}).compare("input1", "input2"));
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
		assertThrows(CollectedException.class, () -> collector.run(() -> {
			throw new PrinterException();
		}));
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
		assertThrows(CollectedException.class, () -> collector.get(() -> {
			throw new PrinterException();
		}));
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
		assertThrows(CollectedException.class, () -> collector.getAsInt(() -> {
			throw new PrinterException();
		}));
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
		assertThrows(CollectedException.class, () -> collector.getAsLong(() -> {
			throw new PrinterException();
		}));
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
		assertThrows(CollectedException.class, () -> collector.getAsDouble(() -> {
			throw new PrinterException();
		}));
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
		assertThrows(CollectedException.class, () -> collector.getAsBoolean(() -> {
			throw new PrinterException();
		}));
		assertThat(collector.single(), instanceOf(PrinterException.class));
	}
}
