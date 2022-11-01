// Part of NoException: https://noexception.machinezoo.com
// Generated code. Edit generate.py instead.
package com.machinezoo.noexception;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.function.*;
import org.junit.jupiter.api.*;
import com.machinezoo.closeablescope.CloseableScope;

public class ExceptionFilterTest {
	@Test
	public void runnable_complete() {
		var collector = new FilteredExceptionCollector(false);
		Runnable lambda = mock(Runnable.class);
		collector.runnable(lambda).run();
		verify(lambda, only()).run();
		assertTrue(collector.empty());
	}
	@Test
	public void runnable_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.runnable(() -> {
			throw new NumberFormatException();
		}).run());
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void runnable_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.runnable(() -> {
			throw new NumberFormatException();
		}).run());
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void supplier_complete() {
		var collector = new FilteredExceptionCollector(false);
		@SuppressWarnings("unchecked") Supplier<String> lambda = mock(Supplier.class);
		when(lambda.get()).thenReturn("value");
		assertEquals("value", collector.supplier(lambda).get());
		verify(lambda, only()).get();
		assertTrue(collector.empty());
	}
	@Test
	public void supplier_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.supplier(() -> {
			throw new NumberFormatException();
		}).get());
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void supplier_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.supplier(() -> {
			throw new NumberFormatException();
		}).get());
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromIntSupplier_complete() {
		var collector = new FilteredExceptionCollector(false);
		IntSupplier lambda = mock(IntSupplier.class);
		when(lambda.getAsInt()).thenReturn(2);
		assertEquals(2, collector.fromIntSupplier(lambda).getAsInt());
		verify(lambda, only()).getAsInt();
		assertTrue(collector.empty());
	}
	@Test
	public void fromIntSupplier_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromIntSupplier(() -> {
			throw new NumberFormatException();
		}).getAsInt());
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromIntSupplier_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromIntSupplier(() -> {
			throw new NumberFormatException();
		}).getAsInt());
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromLongSupplier_complete() {
		var collector = new FilteredExceptionCollector(false);
		LongSupplier lambda = mock(LongSupplier.class);
		when(lambda.getAsLong()).thenReturn(2L);
		assertEquals(2L, collector.fromLongSupplier(lambda).getAsLong());
		verify(lambda, only()).getAsLong();
		assertTrue(collector.empty());
	}
	@Test
	public void fromLongSupplier_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromLongSupplier(() -> {
			throw new NumberFormatException();
		}).getAsLong());
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromLongSupplier_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromLongSupplier(() -> {
			throw new NumberFormatException();
		}).getAsLong());
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromDoubleSupplier_complete() {
		var collector = new FilteredExceptionCollector(false);
		DoubleSupplier lambda = mock(DoubleSupplier.class);
		when(lambda.getAsDouble()).thenReturn(2.0);
		assertEquals(2.0, collector.fromDoubleSupplier(lambda).getAsDouble());
		verify(lambda, only()).getAsDouble();
		assertTrue(collector.empty());
	}
	@Test
	public void fromDoubleSupplier_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromDoubleSupplier(() -> {
			throw new NumberFormatException();
		}).getAsDouble());
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromDoubleSupplier_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromDoubleSupplier(() -> {
			throw new NumberFormatException();
		}).getAsDouble());
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromBooleanSupplier_complete() {
		var collector = new FilteredExceptionCollector(false);
		BooleanSupplier lambda = mock(BooleanSupplier.class);
		when(lambda.getAsBoolean()).thenReturn(true);
		assertEquals(true, collector.fromBooleanSupplier(lambda).getAsBoolean());
		verify(lambda, only()).getAsBoolean();
		assertTrue(collector.empty());
	}
	@Test
	public void fromBooleanSupplier_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromBooleanSupplier(() -> {
			throw new NumberFormatException();
		}).getAsBoolean());
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromBooleanSupplier_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromBooleanSupplier(() -> {
			throw new NumberFormatException();
		}).getAsBoolean());
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void consumer_complete() {
		var collector = new FilteredExceptionCollector(false);
		@SuppressWarnings("unchecked") Consumer<String> lambda = mock(Consumer.class);
		collector.consumer(lambda).accept("input");
		verify(lambda, only()).accept("input");
		assertTrue(collector.empty());
	}
	@Test
	public void consumer_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.consumer(t -> {
			throw new NumberFormatException();
		}).accept("input"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void consumer_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.consumer(t -> {
			throw new NumberFormatException();
		}).accept("input"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromIntConsumer_complete() {
		var collector = new FilteredExceptionCollector(false);
		IntConsumer lambda = mock(IntConsumer.class);
		collector.fromIntConsumer(lambda).accept(1);
		verify(lambda, only()).accept(1);
		assertTrue(collector.empty());
	}
	@Test
	public void fromIntConsumer_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromIntConsumer(v -> {
			throw new NumberFormatException();
		}).accept(1));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromIntConsumer_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromIntConsumer(v -> {
			throw new NumberFormatException();
		}).accept(1));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromLongConsumer_complete() {
		var collector = new FilteredExceptionCollector(false);
		LongConsumer lambda = mock(LongConsumer.class);
		collector.fromLongConsumer(lambda).accept(1L);
		verify(lambda, only()).accept(1L);
		assertTrue(collector.empty());
	}
	@Test
	public void fromLongConsumer_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromLongConsumer(v -> {
			throw new NumberFormatException();
		}).accept(1L));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromLongConsumer_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromLongConsumer(v -> {
			throw new NumberFormatException();
		}).accept(1L));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromDoubleConsumer_complete() {
		var collector = new FilteredExceptionCollector(false);
		DoubleConsumer lambda = mock(DoubleConsumer.class);
		collector.fromDoubleConsumer(lambda).accept(1.0);
		verify(lambda, only()).accept(1.0);
		assertTrue(collector.empty());
	}
	@Test
	public void fromDoubleConsumer_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromDoubleConsumer(v -> {
			throw new NumberFormatException();
		}).accept(1.0));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromDoubleConsumer_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromDoubleConsumer(v -> {
			throw new NumberFormatException();
		}).accept(1.0));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromBiConsumer_complete() {
		var collector = new FilteredExceptionCollector(false);
		@SuppressWarnings("unchecked") BiConsumer<String, String> lambda = mock(BiConsumer.class);
		collector.fromBiConsumer(lambda).accept("input1", "input2");
		verify(lambda, only()).accept("input1", "input2");
		assertTrue(collector.empty());
	}
	@Test
	public void fromBiConsumer_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromBiConsumer((t, u) -> {
			throw new NumberFormatException();
		}).accept("input1", "input2"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromBiConsumer_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromBiConsumer((t, u) -> {
			throw new NumberFormatException();
		}).accept("input1", "input2"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromObjIntConsumer_complete() {
		var collector = new FilteredExceptionCollector(false);
		@SuppressWarnings("unchecked") ObjIntConsumer<String> lambda = mock(ObjIntConsumer.class);
		collector.fromObjIntConsumer(lambda).accept("input", 1);
		verify(lambda, only()).accept("input", 1);
		assertTrue(collector.empty());
	}
	@Test
	public void fromObjIntConsumer_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromObjIntConsumer((t, v) -> {
			throw new NumberFormatException();
		}).accept("input", 1));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromObjIntConsumer_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromObjIntConsumer((t, v) -> {
			throw new NumberFormatException();
		}).accept("input", 1));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromObjLongConsumer_complete() {
		var collector = new FilteredExceptionCollector(false);
		@SuppressWarnings("unchecked") ObjLongConsumer<String> lambda = mock(ObjLongConsumer.class);
		collector.fromObjLongConsumer(lambda).accept("input", 1L);
		verify(lambda, only()).accept("input", 1L);
		assertTrue(collector.empty());
	}
	@Test
	public void fromObjLongConsumer_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromObjLongConsumer((t, v) -> {
			throw new NumberFormatException();
		}).accept("input", 1L));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromObjLongConsumer_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromObjLongConsumer((t, v) -> {
			throw new NumberFormatException();
		}).accept("input", 1L));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromObjDoubleConsumer_complete() {
		var collector = new FilteredExceptionCollector(false);
		@SuppressWarnings("unchecked") ObjDoubleConsumer<String> lambda = mock(ObjDoubleConsumer.class);
		collector.fromObjDoubleConsumer(lambda).accept("input", 1.0);
		verify(lambda, only()).accept("input", 1.0);
		assertTrue(collector.empty());
	}
	@Test
	public void fromObjDoubleConsumer_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromObjDoubleConsumer((t, v) -> {
			throw new NumberFormatException();
		}).accept("input", 1.0));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromObjDoubleConsumer_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromObjDoubleConsumer((t, v) -> {
			throw new NumberFormatException();
		}).accept("input", 1.0));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void predicate_complete() {
		var collector = new FilteredExceptionCollector(false);
		@SuppressWarnings("unchecked") Predicate<String> lambda = mock(Predicate.class);
		when(lambda.test("input")).thenReturn(true);
		assertEquals(true, collector.predicate(lambda).test("input"));
		verify(lambda, only()).test("input");
		assertTrue(collector.empty());
	}
	@Test
	public void predicate_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.predicate(t -> {
			throw new NumberFormatException();
		}).test("input"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void predicate_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.predicate(t -> {
			throw new NumberFormatException();
		}).test("input"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromIntPredicate_complete() {
		var collector = new FilteredExceptionCollector(false);
		IntPredicate lambda = mock(IntPredicate.class);
		when(lambda.test(1)).thenReturn(true);
		assertEquals(true, collector.fromIntPredicate(lambda).test(1));
		verify(lambda, only()).test(1);
		assertTrue(collector.empty());
	}
	@Test
	public void fromIntPredicate_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromIntPredicate(v -> {
			throw new NumberFormatException();
		}).test(1));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromIntPredicate_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromIntPredicate(v -> {
			throw new NumberFormatException();
		}).test(1));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromLongPredicate_complete() {
		var collector = new FilteredExceptionCollector(false);
		LongPredicate lambda = mock(LongPredicate.class);
		when(lambda.test(1L)).thenReturn(true);
		assertEquals(true, collector.fromLongPredicate(lambda).test(1L));
		verify(lambda, only()).test(1L);
		assertTrue(collector.empty());
	}
	@Test
	public void fromLongPredicate_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromLongPredicate(v -> {
			throw new NumberFormatException();
		}).test(1L));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromLongPredicate_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromLongPredicate(v -> {
			throw new NumberFormatException();
		}).test(1L));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromDoublePredicate_complete() {
		var collector = new FilteredExceptionCollector(false);
		DoublePredicate lambda = mock(DoublePredicate.class);
		when(lambda.test(1.0)).thenReturn(true);
		assertEquals(true, collector.fromDoublePredicate(lambda).test(1.0));
		verify(lambda, only()).test(1.0);
		assertTrue(collector.empty());
	}
	@Test
	public void fromDoublePredicate_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromDoublePredicate(v -> {
			throw new NumberFormatException();
		}).test(1.0));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromDoublePredicate_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromDoublePredicate(v -> {
			throw new NumberFormatException();
		}).test(1.0));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromBiPredicate_complete() {
		var collector = new FilteredExceptionCollector(false);
		@SuppressWarnings("unchecked") BiPredicate<String, String> lambda = mock(BiPredicate.class);
		when(lambda.test("input1", "input2")).thenReturn(true);
		assertEquals(true, collector.fromBiPredicate(lambda).test("input1", "input2"));
		verify(lambda, only()).test("input1", "input2");
		assertTrue(collector.empty());
	}
	@Test
	public void fromBiPredicate_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromBiPredicate((t, u) -> {
			throw new NumberFormatException();
		}).test("input1", "input2"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromBiPredicate_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromBiPredicate((t, u) -> {
			throw new NumberFormatException();
		}).test("input1", "input2"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void function_complete() {
		var collector = new FilteredExceptionCollector(false);
		@SuppressWarnings("unchecked") Function<String, String> lambda = mock(Function.class);
		when(lambda.apply("input")).thenReturn("value");
		assertEquals("value", collector.function(lambda).apply("input"));
		verify(lambda, only()).apply("input");
		assertTrue(collector.empty());
	}
	@Test
	public void function_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.function(t -> {
			throw new NumberFormatException();
		}).apply("input"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void function_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.function(t -> {
			throw new NumberFormatException();
		}).apply("input"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromToIntFunction_complete() {
		var collector = new FilteredExceptionCollector(false);
		@SuppressWarnings("unchecked") ToIntFunction<String> lambda = mock(ToIntFunction.class);
		when(lambda.applyAsInt("input")).thenReturn(2);
		assertEquals(2, collector.fromToIntFunction(lambda).applyAsInt("input"));
		verify(lambda, only()).applyAsInt("input");
		assertTrue(collector.empty());
	}
	@Test
	public void fromToIntFunction_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromToIntFunction(v -> {
			throw new NumberFormatException();
		}).applyAsInt("input"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromToIntFunction_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromToIntFunction(v -> {
			throw new NumberFormatException();
		}).applyAsInt("input"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromIntFunction_complete() {
		var collector = new FilteredExceptionCollector(false);
		@SuppressWarnings("unchecked") IntFunction<String> lambda = mock(IntFunction.class);
		when(lambda.apply(1)).thenReturn("value");
		assertEquals("value", collector.fromIntFunction(lambda).apply(1));
		verify(lambda, only()).apply(1);
		assertTrue(collector.empty());
	}
	@Test
	public void fromIntFunction_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromIntFunction(v -> {
			throw new NumberFormatException();
		}).apply(1));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromIntFunction_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromIntFunction(v -> {
			throw new NumberFormatException();
		}).apply(1));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromIntToLongFunction_complete() {
		var collector = new FilteredExceptionCollector(false);
		IntToLongFunction lambda = mock(IntToLongFunction.class);
		when(lambda.applyAsLong(1)).thenReturn(2L);
		assertEquals(2L, collector.fromIntToLongFunction(lambda).applyAsLong(1));
		verify(lambda, only()).applyAsLong(1);
		assertTrue(collector.empty());
	}
	@Test
	public void fromIntToLongFunction_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromIntToLongFunction(v -> {
			throw new NumberFormatException();
		}).applyAsLong(1));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromIntToLongFunction_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromIntToLongFunction(v -> {
			throw new NumberFormatException();
		}).applyAsLong(1));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromIntToDoubleFunction_complete() {
		var collector = new FilteredExceptionCollector(false);
		IntToDoubleFunction lambda = mock(IntToDoubleFunction.class);
		when(lambda.applyAsDouble(1)).thenReturn(2.0);
		assertEquals(2.0, collector.fromIntToDoubleFunction(lambda).applyAsDouble(1));
		verify(lambda, only()).applyAsDouble(1);
		assertTrue(collector.empty());
	}
	@Test
	public void fromIntToDoubleFunction_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromIntToDoubleFunction(v -> {
			throw new NumberFormatException();
		}).applyAsDouble(1));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromIntToDoubleFunction_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromIntToDoubleFunction(v -> {
			throw new NumberFormatException();
		}).applyAsDouble(1));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromToLongFunction_complete() {
		var collector = new FilteredExceptionCollector(false);
		@SuppressWarnings("unchecked") ToLongFunction<String> lambda = mock(ToLongFunction.class);
		when(lambda.applyAsLong("input")).thenReturn(2L);
		assertEquals(2L, collector.fromToLongFunction(lambda).applyAsLong("input"));
		verify(lambda, only()).applyAsLong("input");
		assertTrue(collector.empty());
	}
	@Test
	public void fromToLongFunction_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromToLongFunction(v -> {
			throw new NumberFormatException();
		}).applyAsLong("input"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromToLongFunction_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromToLongFunction(v -> {
			throw new NumberFormatException();
		}).applyAsLong("input"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromLongFunction_complete() {
		var collector = new FilteredExceptionCollector(false);
		@SuppressWarnings("unchecked") LongFunction<String> lambda = mock(LongFunction.class);
		when(lambda.apply(1L)).thenReturn("value");
		assertEquals("value", collector.fromLongFunction(lambda).apply(1L));
		verify(lambda, only()).apply(1L);
		assertTrue(collector.empty());
	}
	@Test
	public void fromLongFunction_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromLongFunction(v -> {
			throw new NumberFormatException();
		}).apply(1L));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromLongFunction_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromLongFunction(v -> {
			throw new NumberFormatException();
		}).apply(1L));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromLongToIntFunction_complete() {
		var collector = new FilteredExceptionCollector(false);
		LongToIntFunction lambda = mock(LongToIntFunction.class);
		when(lambda.applyAsInt(1L)).thenReturn(2);
		assertEquals(2, collector.fromLongToIntFunction(lambda).applyAsInt(1L));
		verify(lambda, only()).applyAsInt(1L);
		assertTrue(collector.empty());
	}
	@Test
	public void fromLongToIntFunction_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromLongToIntFunction(v -> {
			throw new NumberFormatException();
		}).applyAsInt(1L));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromLongToIntFunction_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromLongToIntFunction(v -> {
			throw new NumberFormatException();
		}).applyAsInt(1L));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromLongToDoubleFunction_complete() {
		var collector = new FilteredExceptionCollector(false);
		LongToDoubleFunction lambda = mock(LongToDoubleFunction.class);
		when(lambda.applyAsDouble(1L)).thenReturn(2.0);
		assertEquals(2.0, collector.fromLongToDoubleFunction(lambda).applyAsDouble(1L));
		verify(lambda, only()).applyAsDouble(1L);
		assertTrue(collector.empty());
	}
	@Test
	public void fromLongToDoubleFunction_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromLongToDoubleFunction(v -> {
			throw new NumberFormatException();
		}).applyAsDouble(1L));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromLongToDoubleFunction_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromLongToDoubleFunction(v -> {
			throw new NumberFormatException();
		}).applyAsDouble(1L));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromToDoubleFunction_complete() {
		var collector = new FilteredExceptionCollector(false);
		@SuppressWarnings("unchecked") ToDoubleFunction<String> lambda = mock(ToDoubleFunction.class);
		when(lambda.applyAsDouble("input")).thenReturn(2.0);
		assertEquals(2.0, collector.fromToDoubleFunction(lambda).applyAsDouble("input"));
		verify(lambda, only()).applyAsDouble("input");
		assertTrue(collector.empty());
	}
	@Test
	public void fromToDoubleFunction_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromToDoubleFunction(v -> {
			throw new NumberFormatException();
		}).applyAsDouble("input"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromToDoubleFunction_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromToDoubleFunction(v -> {
			throw new NumberFormatException();
		}).applyAsDouble("input"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromDoubleFunction_complete() {
		var collector = new FilteredExceptionCollector(false);
		@SuppressWarnings("unchecked") DoubleFunction<String> lambda = mock(DoubleFunction.class);
		when(lambda.apply(1.0)).thenReturn("value");
		assertEquals("value", collector.fromDoubleFunction(lambda).apply(1.0));
		verify(lambda, only()).apply(1.0);
		assertTrue(collector.empty());
	}
	@Test
	public void fromDoubleFunction_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromDoubleFunction(v -> {
			throw new NumberFormatException();
		}).apply(1.0));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromDoubleFunction_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromDoubleFunction(v -> {
			throw new NumberFormatException();
		}).apply(1.0));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromDoubleToIntFunction_complete() {
		var collector = new FilteredExceptionCollector(false);
		DoubleToIntFunction lambda = mock(DoubleToIntFunction.class);
		when(lambda.applyAsInt(1.0)).thenReturn(2);
		assertEquals(2, collector.fromDoubleToIntFunction(lambda).applyAsInt(1.0));
		verify(lambda, only()).applyAsInt(1.0);
		assertTrue(collector.empty());
	}
	@Test
	public void fromDoubleToIntFunction_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromDoubleToIntFunction(v -> {
			throw new NumberFormatException();
		}).applyAsInt(1.0));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromDoubleToIntFunction_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromDoubleToIntFunction(v -> {
			throw new NumberFormatException();
		}).applyAsInt(1.0));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromDoubleToLongFunction_complete() {
		var collector = new FilteredExceptionCollector(false);
		DoubleToLongFunction lambda = mock(DoubleToLongFunction.class);
		when(lambda.applyAsLong(1.0)).thenReturn(2L);
		assertEquals(2L, collector.fromDoubleToLongFunction(lambda).applyAsLong(1.0));
		verify(lambda, only()).applyAsLong(1.0);
		assertTrue(collector.empty());
	}
	@Test
	public void fromDoubleToLongFunction_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromDoubleToLongFunction(v -> {
			throw new NumberFormatException();
		}).applyAsLong(1.0));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromDoubleToLongFunction_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromDoubleToLongFunction(v -> {
			throw new NumberFormatException();
		}).applyAsLong(1.0));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromUnaryOperator_complete() {
		var collector = new FilteredExceptionCollector(false);
		@SuppressWarnings("unchecked") UnaryOperator<String> lambda = mock(UnaryOperator.class);
		when(lambda.apply("input")).thenReturn("value");
		assertEquals("value", collector.fromUnaryOperator(lambda).apply("input"));
		verify(lambda, only()).apply("input");
		assertTrue(collector.empty());
	}
	@Test
	public void fromUnaryOperator_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromUnaryOperator(o -> {
			throw new NumberFormatException();
		}).apply("input"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromUnaryOperator_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromUnaryOperator(o -> {
			throw new NumberFormatException();
		}).apply("input"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromIntUnaryOperator_complete() {
		var collector = new FilteredExceptionCollector(false);
		IntUnaryOperator lambda = mock(IntUnaryOperator.class);
		when(lambda.applyAsInt(1)).thenReturn(2);
		assertEquals(2, collector.fromIntUnaryOperator(lambda).applyAsInt(1));
		verify(lambda, only()).applyAsInt(1);
		assertTrue(collector.empty());
	}
	@Test
	public void fromIntUnaryOperator_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromIntUnaryOperator(o -> {
			throw new NumberFormatException();
		}).applyAsInt(1));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromIntUnaryOperator_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromIntUnaryOperator(o -> {
			throw new NumberFormatException();
		}).applyAsInt(1));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromLongUnaryOperator_complete() {
		var collector = new FilteredExceptionCollector(false);
		LongUnaryOperator lambda = mock(LongUnaryOperator.class);
		when(lambda.applyAsLong(1L)).thenReturn(2L);
		assertEquals(2L, collector.fromLongUnaryOperator(lambda).applyAsLong(1L));
		verify(lambda, only()).applyAsLong(1L);
		assertTrue(collector.empty());
	}
	@Test
	public void fromLongUnaryOperator_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromLongUnaryOperator(o -> {
			throw new NumberFormatException();
		}).applyAsLong(1L));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromLongUnaryOperator_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromLongUnaryOperator(o -> {
			throw new NumberFormatException();
		}).applyAsLong(1L));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromDoubleUnaryOperator_complete() {
		var collector = new FilteredExceptionCollector(false);
		DoubleUnaryOperator lambda = mock(DoubleUnaryOperator.class);
		when(lambda.applyAsDouble(1.0)).thenReturn(2.0);
		assertEquals(2.0, collector.fromDoubleUnaryOperator(lambda).applyAsDouble(1.0));
		verify(lambda, only()).applyAsDouble(1.0);
		assertTrue(collector.empty());
	}
	@Test
	public void fromDoubleUnaryOperator_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromDoubleUnaryOperator(o -> {
			throw new NumberFormatException();
		}).applyAsDouble(1.0));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromDoubleUnaryOperator_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromDoubleUnaryOperator(o -> {
			throw new NumberFormatException();
		}).applyAsDouble(1.0));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromBiFunction_complete() {
		var collector = new FilteredExceptionCollector(false);
		@SuppressWarnings("unchecked") BiFunction<String, String, String> lambda = mock(BiFunction.class);
		when(lambda.apply("input1", "input2")).thenReturn("value");
		assertEquals("value", collector.fromBiFunction(lambda).apply("input1", "input2"));
		verify(lambda, only()).apply("input1", "input2");
		assertTrue(collector.empty());
	}
	@Test
	public void fromBiFunction_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromBiFunction((t, u) -> {
			throw new NumberFormatException();
		}).apply("input1", "input2"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromBiFunction_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromBiFunction((t, u) -> {
			throw new NumberFormatException();
		}).apply("input1", "input2"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromToIntBiFunction_complete() {
		var collector = new FilteredExceptionCollector(false);
		@SuppressWarnings("unchecked") ToIntBiFunction<String, String> lambda = mock(ToIntBiFunction.class);
		when(lambda.applyAsInt("input1", "input2")).thenReturn(2);
		assertEquals(2, collector.fromToIntBiFunction(lambda).applyAsInt("input1", "input2"));
		verify(lambda, only()).applyAsInt("input1", "input2");
		assertTrue(collector.empty());
	}
	@Test
	public void fromToIntBiFunction_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromToIntBiFunction((t, u) -> {
			throw new NumberFormatException();
		}).applyAsInt("input1", "input2"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromToIntBiFunction_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromToIntBiFunction((t, u) -> {
			throw new NumberFormatException();
		}).applyAsInt("input1", "input2"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromToLongBiFunction_complete() {
		var collector = new FilteredExceptionCollector(false);
		@SuppressWarnings("unchecked") ToLongBiFunction<String, String> lambda = mock(ToLongBiFunction.class);
		when(lambda.applyAsLong("input1", "input2")).thenReturn(2L);
		assertEquals(2L, collector.fromToLongBiFunction(lambda).applyAsLong("input1", "input2"));
		verify(lambda, only()).applyAsLong("input1", "input2");
		assertTrue(collector.empty());
	}
	@Test
	public void fromToLongBiFunction_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromToLongBiFunction((t, u) -> {
			throw new NumberFormatException();
		}).applyAsLong("input1", "input2"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromToLongBiFunction_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromToLongBiFunction((t, u) -> {
			throw new NumberFormatException();
		}).applyAsLong("input1", "input2"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromToDoubleBiFunction_complete() {
		var collector = new FilteredExceptionCollector(false);
		@SuppressWarnings("unchecked") ToDoubleBiFunction<String, String> lambda = mock(ToDoubleBiFunction.class);
		when(lambda.applyAsDouble("input1", "input2")).thenReturn(2.0);
		assertEquals(2.0, collector.fromToDoubleBiFunction(lambda).applyAsDouble("input1", "input2"));
		verify(lambda, only()).applyAsDouble("input1", "input2");
		assertTrue(collector.empty());
	}
	@Test
	public void fromToDoubleBiFunction_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromToDoubleBiFunction((t, u) -> {
			throw new NumberFormatException();
		}).applyAsDouble("input1", "input2"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromToDoubleBiFunction_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromToDoubleBiFunction((t, u) -> {
			throw new NumberFormatException();
		}).applyAsDouble("input1", "input2"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromBinaryOperator_complete() {
		var collector = new FilteredExceptionCollector(false);
		@SuppressWarnings("unchecked") BinaryOperator<String> lambda = mock(BinaryOperator.class);
		when(lambda.apply("input1", "input2")).thenReturn("value");
		assertEquals("value", collector.fromBinaryOperator(lambda).apply("input1", "input2"));
		verify(lambda, only()).apply("input1", "input2");
		assertTrue(collector.empty());
	}
	@Test
	public void fromBinaryOperator_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromBinaryOperator((l, r) -> {
			throw new NumberFormatException();
		}).apply("input1", "input2"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromBinaryOperator_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromBinaryOperator((l, r) -> {
			throw new NumberFormatException();
		}).apply("input1", "input2"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromIntBinaryOperator_complete() {
		var collector = new FilteredExceptionCollector(false);
		IntBinaryOperator lambda = mock(IntBinaryOperator.class);
		when(lambda.applyAsInt(11, 12)).thenReturn(2);
		assertEquals(2, collector.fromIntBinaryOperator(lambda).applyAsInt(11, 12));
		verify(lambda, only()).applyAsInt(11, 12);
		assertTrue(collector.empty());
	}
	@Test
	public void fromIntBinaryOperator_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromIntBinaryOperator((l, r) -> {
			throw new NumberFormatException();
		}).applyAsInt(11, 12));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromIntBinaryOperator_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromIntBinaryOperator((l, r) -> {
			throw new NumberFormatException();
		}).applyAsInt(11, 12));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromLongBinaryOperator_complete() {
		var collector = new FilteredExceptionCollector(false);
		LongBinaryOperator lambda = mock(LongBinaryOperator.class);
		when(lambda.applyAsLong(11L, 12L)).thenReturn(2L);
		assertEquals(2L, collector.fromLongBinaryOperator(lambda).applyAsLong(11L, 12L));
		verify(lambda, only()).applyAsLong(11L, 12L);
		assertTrue(collector.empty());
	}
	@Test
	public void fromLongBinaryOperator_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromLongBinaryOperator((l, r) -> {
			throw new NumberFormatException();
		}).applyAsLong(11L, 12L));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromLongBinaryOperator_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromLongBinaryOperator((l, r) -> {
			throw new NumberFormatException();
		}).applyAsLong(11L, 12L));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromDoubleBinaryOperator_complete() {
		var collector = new FilteredExceptionCollector(false);
		DoubleBinaryOperator lambda = mock(DoubleBinaryOperator.class);
		when(lambda.applyAsDouble(1.1, 1.2)).thenReturn(2.0);
		assertEquals(2.0, collector.fromDoubleBinaryOperator(lambda).applyAsDouble(1.1, 1.2));
		verify(lambda, only()).applyAsDouble(1.1, 1.2);
		assertTrue(collector.empty());
	}
	@Test
	public void fromDoubleBinaryOperator_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromDoubleBinaryOperator((l, r) -> {
			throw new NumberFormatException();
		}).applyAsDouble(1.1, 1.2));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromDoubleBinaryOperator_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.fromDoubleBinaryOperator((l, r) -> {
			throw new NumberFormatException();
		}).applyAsDouble(1.1, 1.2));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void comparator_complete() {
		var collector = new FilteredExceptionCollector(false);
		@SuppressWarnings("unchecked") Comparator<String> lambda = mock(Comparator.class);
		when(lambda.compare("input1", "input2")).thenReturn(2);
		assertEquals(2, collector.comparator(lambda).compare("input1", "input2"));
		verify(lambda, only()).compare("input1", "input2");
		assertTrue(collector.empty());
	}
	@Test
	public void comparator_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.comparator((l, r) -> {
			throw new NumberFormatException();
		}).compare("input1", "input2"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void comparator_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.comparator((l, r) -> {
			throw new NumberFormatException();
		}).compare("input1", "input2"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void closeable_complete() {
		var collector = new FilteredExceptionCollector(false);
		CloseableScope lambda = mock(CloseableScope.class);
		collector.closeable(lambda).close();
		verify(lambda, only()).close();
		assertTrue(collector.empty());
	}
	@Test
	public void closeable_rethrow() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.closeable(() -> {
			throw new NumberFormatException();
		}).close());
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void closeable_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.closeable(() -> {
			throw new NumberFormatException();
		}).close());
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void run_complete() {
		var collector = new FilteredExceptionCollector(false);
		Runnable lambda = mock(Runnable.class);
		collector.run(lambda);
		verify(lambda, only()).run();
		assertTrue(collector.empty());
	}
	@Test
	public void run_pass() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.run(() -> {
			throw new NumberFormatException();
		}));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void run_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.run(() -> {
			throw new NumberFormatException();
		}));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void get_complete() {
		var collector = new FilteredExceptionCollector(false);
		@SuppressWarnings("unchecked") Supplier<String> lambda = mock(Supplier.class);
		when(lambda.get()).thenReturn("value");
		assertEquals("value", collector.get(lambda));
		verify(lambda, only()).get();
		assertTrue(collector.empty());
	}
	@Test
	public void get_pass() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.get(() -> {
			throw new NumberFormatException();
		}));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void get_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.get(() -> {
			throw new NumberFormatException();
		}));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void getAsInt_complete() {
		var collector = new FilteredExceptionCollector(false);
		IntSupplier lambda = mock(IntSupplier.class);
		when(lambda.getAsInt()).thenReturn(2);
		assertEquals(2, collector.getAsInt(lambda));
		verify(lambda, only()).getAsInt();
		assertTrue(collector.empty());
	}
	@Test
	public void getAsInt_pass() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.getAsInt(() -> {
			throw new NumberFormatException();
		}));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void getAsInt_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.getAsInt(() -> {
			throw new NumberFormatException();
		}));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void getAsLong_complete() {
		var collector = new FilteredExceptionCollector(false);
		LongSupplier lambda = mock(LongSupplier.class);
		when(lambda.getAsLong()).thenReturn(2L);
		assertEquals(2L, collector.getAsLong(lambda));
		verify(lambda, only()).getAsLong();
		assertTrue(collector.empty());
	}
	@Test
	public void getAsLong_pass() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.getAsLong(() -> {
			throw new NumberFormatException();
		}));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void getAsLong_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.getAsLong(() -> {
			throw new NumberFormatException();
		}));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void getAsDouble_complete() {
		var collector = new FilteredExceptionCollector(false);
		DoubleSupplier lambda = mock(DoubleSupplier.class);
		when(lambda.getAsDouble()).thenReturn(2.0);
		assertEquals(2.0, collector.getAsDouble(lambda));
		verify(lambda, only()).getAsDouble();
		assertTrue(collector.empty());
	}
	@Test
	public void getAsDouble_pass() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.getAsDouble(() -> {
			throw new NumberFormatException();
		}));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void getAsDouble_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.getAsDouble(() -> {
			throw new NumberFormatException();
		}));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void getAsBoolean_complete() {
		var collector = new FilteredExceptionCollector(false);
		BooleanSupplier lambda = mock(BooleanSupplier.class);
		when(lambda.getAsBoolean()).thenReturn(true);
		assertEquals(true, collector.getAsBoolean(lambda));
		verify(lambda, only()).getAsBoolean();
		assertTrue(collector.empty());
	}
	@Test
	public void getAsBoolean_pass() {
		var collector = new FilteredExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.getAsBoolean(() -> {
			throw new NumberFormatException();
		}));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void getAsBoolean_replace() {
		var collector = new FilteredExceptionCollector(true);
		assertThrows(CollectedException.class, () -> collector.getAsBoolean(() -> {
			throw new NumberFormatException();
		}));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
}
