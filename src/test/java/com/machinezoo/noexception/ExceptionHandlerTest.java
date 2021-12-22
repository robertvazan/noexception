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
import com.machinezoo.noexception.optional.*;

public class ExceptionHandlerTest {
	@Test
	public void runnable_complete() {
		var collector = new ExceptionCollector(true);
		Runnable lambda = mock(Runnable.class);
		collector.runnable(lambda).run();
		verify(lambda, only()).run();
		assertTrue(collector.empty());
	}
	@Test
	public void runnable_swallowException() {
		var collector = new ExceptionCollector(true);
		collector.runnable(() -> {
			throw new NumberFormatException();
		}).run();
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void runnable_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.runnable(() -> {
			throw new NumberFormatException();
		}).run());
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void supplier_complete() {
		var collector = new ExceptionCollector(true);
		@SuppressWarnings("unchecked") Supplier<String> lambda = mock(Supplier.class);
		when(lambda.get()).thenReturn("value");
		assertEquals(Optional.of("value"), collector.supplier(lambda).get());
		verify(lambda, only()).get();
		assertTrue(collector.empty());
	}
	@Test
	public void supplier_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(Optional.empty(), collector.supplier(() -> {
			throw new NumberFormatException();
		}).get());
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void supplier_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.supplier(() -> {
			throw new NumberFormatException();
		}).get());
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromIntSupplier_complete() {
		var collector = new ExceptionCollector(true);
		IntSupplier lambda = mock(IntSupplier.class);
		when(lambda.getAsInt()).thenReturn(2);
		assertEquals(OptionalInt.of(2), collector.fromIntSupplier(lambda).get());
		verify(lambda, only()).getAsInt();
		assertTrue(collector.empty());
	}
	@Test
	public void fromIntSupplier_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(OptionalInt.empty(), collector.fromIntSupplier(() -> {
			throw new NumberFormatException();
		}).get());
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromIntSupplier_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromIntSupplier(() -> {
			throw new NumberFormatException();
		}).get());
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromLongSupplier_complete() {
		var collector = new ExceptionCollector(true);
		LongSupplier lambda = mock(LongSupplier.class);
		when(lambda.getAsLong()).thenReturn(2L);
		assertEquals(OptionalLong.of(2L), collector.fromLongSupplier(lambda).get());
		verify(lambda, only()).getAsLong();
		assertTrue(collector.empty());
	}
	@Test
	public void fromLongSupplier_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(OptionalLong.empty(), collector.fromLongSupplier(() -> {
			throw new NumberFormatException();
		}).get());
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromLongSupplier_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromLongSupplier(() -> {
			throw new NumberFormatException();
		}).get());
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromDoubleSupplier_complete() {
		var collector = new ExceptionCollector(true);
		DoubleSupplier lambda = mock(DoubleSupplier.class);
		when(lambda.getAsDouble()).thenReturn(2.0);
		assertEquals(OptionalDouble.of(2.0), collector.fromDoubleSupplier(lambda).get());
		verify(lambda, only()).getAsDouble();
		assertTrue(collector.empty());
	}
	@Test
	public void fromDoubleSupplier_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(OptionalDouble.empty(), collector.fromDoubleSupplier(() -> {
			throw new NumberFormatException();
		}).get());
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromDoubleSupplier_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromDoubleSupplier(() -> {
			throw new NumberFormatException();
		}).get());
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromBooleanSupplier_complete() {
		var collector = new ExceptionCollector(true);
		BooleanSupplier lambda = mock(BooleanSupplier.class);
		when(lambda.getAsBoolean()).thenReturn(true);
		assertEquals(OptionalBoolean.of(true), collector.fromBooleanSupplier(lambda).get());
		verify(lambda, only()).getAsBoolean();
		assertTrue(collector.empty());
	}
	@Test
	public void fromBooleanSupplier_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(OptionalBoolean.empty(), collector.fromBooleanSupplier(() -> {
			throw new NumberFormatException();
		}).get());
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromBooleanSupplier_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromBooleanSupplier(() -> {
			throw new NumberFormatException();
		}).get());
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void consumer_complete() {
		var collector = new ExceptionCollector(true);
		@SuppressWarnings("unchecked") Consumer<String> lambda = mock(Consumer.class);
		collector.consumer(lambda).accept("input");
		verify(lambda, only()).accept("input");
		assertTrue(collector.empty());
	}
	@Test
	public void consumer_swallowException() {
		var collector = new ExceptionCollector(true);
		collector.consumer(t -> {
			throw new NumberFormatException();
		}).accept("input");
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void consumer_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.consumer(t -> {
			throw new NumberFormatException();
		}).accept("input"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromIntConsumer_complete() {
		var collector = new ExceptionCollector(true);
		IntConsumer lambda = mock(IntConsumer.class);
		collector.fromIntConsumer(lambda).accept(1);
		verify(lambda, only()).accept(1);
		assertTrue(collector.empty());
	}
	@Test
	public void fromIntConsumer_swallowException() {
		var collector = new ExceptionCollector(true);
		collector.fromIntConsumer(v -> {
			throw new NumberFormatException();
		}).accept(1);
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromIntConsumer_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromIntConsumer(v -> {
			throw new NumberFormatException();
		}).accept(1));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromLongConsumer_complete() {
		var collector = new ExceptionCollector(true);
		LongConsumer lambda = mock(LongConsumer.class);
		collector.fromLongConsumer(lambda).accept(1L);
		verify(lambda, only()).accept(1L);
		assertTrue(collector.empty());
	}
	@Test
	public void fromLongConsumer_swallowException() {
		var collector = new ExceptionCollector(true);
		collector.fromLongConsumer(v -> {
			throw new NumberFormatException();
		}).accept(1L);
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromLongConsumer_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromLongConsumer(v -> {
			throw new NumberFormatException();
		}).accept(1L));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromDoubleConsumer_complete() {
		var collector = new ExceptionCollector(true);
		DoubleConsumer lambda = mock(DoubleConsumer.class);
		collector.fromDoubleConsumer(lambda).accept(1.0);
		verify(lambda, only()).accept(1.0);
		assertTrue(collector.empty());
	}
	@Test
	public void fromDoubleConsumer_swallowException() {
		var collector = new ExceptionCollector(true);
		collector.fromDoubleConsumer(v -> {
			throw new NumberFormatException();
		}).accept(1.0);
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromDoubleConsumer_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromDoubleConsumer(v -> {
			throw new NumberFormatException();
		}).accept(1.0));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromBiConsumer_complete() {
		var collector = new ExceptionCollector(true);
		@SuppressWarnings("unchecked") BiConsumer<String, String> lambda = mock(BiConsumer.class);
		collector.fromBiConsumer(lambda).accept("input1", "input2");
		verify(lambda, only()).accept("input1", "input2");
		assertTrue(collector.empty());
	}
	@Test
	public void fromBiConsumer_swallowException() {
		var collector = new ExceptionCollector(true);
		collector.fromBiConsumer((t, u) -> {
			throw new NumberFormatException();
		}).accept("input1", "input2");
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromBiConsumer_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromBiConsumer((t, u) -> {
			throw new NumberFormatException();
		}).accept("input1", "input2"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromObjIntConsumer_complete() {
		var collector = new ExceptionCollector(true);
		@SuppressWarnings("unchecked") ObjIntConsumer<String> lambda = mock(ObjIntConsumer.class);
		collector.fromObjIntConsumer(lambda).accept("input", 1);
		verify(lambda, only()).accept("input", 1);
		assertTrue(collector.empty());
	}
	@Test
	public void fromObjIntConsumer_swallowException() {
		var collector = new ExceptionCollector(true);
		collector.fromObjIntConsumer((t, v) -> {
			throw new NumberFormatException();
		}).accept("input", 1);
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromObjIntConsumer_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromObjIntConsumer((t, v) -> {
			throw new NumberFormatException();
		}).accept("input", 1));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromObjLongConsumer_complete() {
		var collector = new ExceptionCollector(true);
		@SuppressWarnings("unchecked") ObjLongConsumer<String> lambda = mock(ObjLongConsumer.class);
		collector.fromObjLongConsumer(lambda).accept("input", 1L);
		verify(lambda, only()).accept("input", 1L);
		assertTrue(collector.empty());
	}
	@Test
	public void fromObjLongConsumer_swallowException() {
		var collector = new ExceptionCollector(true);
		collector.fromObjLongConsumer((t, v) -> {
			throw new NumberFormatException();
		}).accept("input", 1L);
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromObjLongConsumer_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromObjLongConsumer((t, v) -> {
			throw new NumberFormatException();
		}).accept("input", 1L));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromObjDoubleConsumer_complete() {
		var collector = new ExceptionCollector(true);
		@SuppressWarnings("unchecked") ObjDoubleConsumer<String> lambda = mock(ObjDoubleConsumer.class);
		collector.fromObjDoubleConsumer(lambda).accept("input", 1.0);
		verify(lambda, only()).accept("input", 1.0);
		assertTrue(collector.empty());
	}
	@Test
	public void fromObjDoubleConsumer_swallowException() {
		var collector = new ExceptionCollector(true);
		collector.fromObjDoubleConsumer((t, v) -> {
			throw new NumberFormatException();
		}).accept("input", 1.0);
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromObjDoubleConsumer_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromObjDoubleConsumer((t, v) -> {
			throw new NumberFormatException();
		}).accept("input", 1.0));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void predicate_complete() {
		var collector = new ExceptionCollector(true);
		@SuppressWarnings("unchecked") Predicate<String> lambda = mock(Predicate.class);
		when(lambda.test("input")).thenReturn(true);
		assertEquals(OptionalBoolean.of(true), collector.predicate(lambda).test("input"));
		verify(lambda, only()).test("input");
		assertTrue(collector.empty());
	}
	@Test
	public void predicate_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(OptionalBoolean.empty(), collector.predicate(t -> {
			throw new NumberFormatException();
		}).test("input"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void predicate_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.predicate(t -> {
			throw new NumberFormatException();
		}).test("input"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromIntPredicate_complete() {
		var collector = new ExceptionCollector(true);
		IntPredicate lambda = mock(IntPredicate.class);
		when(lambda.test(1)).thenReturn(true);
		assertEquals(OptionalBoolean.of(true), collector.fromIntPredicate(lambda).test(1));
		verify(lambda, only()).test(1);
		assertTrue(collector.empty());
	}
	@Test
	public void fromIntPredicate_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(OptionalBoolean.empty(), collector.fromIntPredicate(v -> {
			throw new NumberFormatException();
		}).test(1));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromIntPredicate_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromIntPredicate(v -> {
			throw new NumberFormatException();
		}).test(1));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromLongPredicate_complete() {
		var collector = new ExceptionCollector(true);
		LongPredicate lambda = mock(LongPredicate.class);
		when(lambda.test(1L)).thenReturn(true);
		assertEquals(OptionalBoolean.of(true), collector.fromLongPredicate(lambda).test(1L));
		verify(lambda, only()).test(1L);
		assertTrue(collector.empty());
	}
	@Test
	public void fromLongPredicate_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(OptionalBoolean.empty(), collector.fromLongPredicate(v -> {
			throw new NumberFormatException();
		}).test(1L));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromLongPredicate_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromLongPredicate(v -> {
			throw new NumberFormatException();
		}).test(1L));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromDoublePredicate_complete() {
		var collector = new ExceptionCollector(true);
		DoublePredicate lambda = mock(DoublePredicate.class);
		when(lambda.test(1.0)).thenReturn(true);
		assertEquals(OptionalBoolean.of(true), collector.fromDoublePredicate(lambda).test(1.0));
		verify(lambda, only()).test(1.0);
		assertTrue(collector.empty());
	}
	@Test
	public void fromDoublePredicate_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(OptionalBoolean.empty(), collector.fromDoublePredicate(v -> {
			throw new NumberFormatException();
		}).test(1.0));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromDoublePredicate_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromDoublePredicate(v -> {
			throw new NumberFormatException();
		}).test(1.0));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromBiPredicate_complete() {
		var collector = new ExceptionCollector(true);
		@SuppressWarnings("unchecked") BiPredicate<String, String> lambda = mock(BiPredicate.class);
		when(lambda.test("input1", "input2")).thenReturn(true);
		assertEquals(OptionalBoolean.of(true), collector.fromBiPredicate(lambda).test("input1", "input2"));
		verify(lambda, only()).test("input1", "input2");
		assertTrue(collector.empty());
	}
	@Test
	public void fromBiPredicate_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(OptionalBoolean.empty(), collector.fromBiPredicate((t, u) -> {
			throw new NumberFormatException();
		}).test("input1", "input2"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromBiPredicate_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromBiPredicate((t, u) -> {
			throw new NumberFormatException();
		}).test("input1", "input2"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void function_complete() {
		var collector = new ExceptionCollector(true);
		@SuppressWarnings("unchecked") Function<String, String> lambda = mock(Function.class);
		when(lambda.apply("input")).thenReturn("value");
		assertEquals(Optional.of("value"), collector.function(lambda).apply("input"));
		verify(lambda, only()).apply("input");
		assertTrue(collector.empty());
	}
	@Test
	public void function_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(Optional.empty(), collector.function(t -> {
			throw new NumberFormatException();
		}).apply("input"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void function_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.function(t -> {
			throw new NumberFormatException();
		}).apply("input"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromToIntFunction_complete() {
		var collector = new ExceptionCollector(true);
		@SuppressWarnings("unchecked") ToIntFunction<String> lambda = mock(ToIntFunction.class);
		when(lambda.applyAsInt("input")).thenReturn(2);
		assertEquals(OptionalInt.of(2), collector.fromToIntFunction(lambda).apply("input"));
		verify(lambda, only()).applyAsInt("input");
		assertTrue(collector.empty());
	}
	@Test
	public void fromToIntFunction_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(OptionalInt.empty(), collector.fromToIntFunction(v -> {
			throw new NumberFormatException();
		}).apply("input"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromToIntFunction_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromToIntFunction(v -> {
			throw new NumberFormatException();
		}).apply("input"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromIntFunction_complete() {
		var collector = new ExceptionCollector(true);
		@SuppressWarnings("unchecked") IntFunction<String> lambda = mock(IntFunction.class);
		when(lambda.apply(1)).thenReturn("value");
		assertEquals(Optional.of("value"), collector.fromIntFunction(lambda).apply(1));
		verify(lambda, only()).apply(1);
		assertTrue(collector.empty());
	}
	@Test
	public void fromIntFunction_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(Optional.empty(), collector.fromIntFunction(v -> {
			throw new NumberFormatException();
		}).apply(1));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromIntFunction_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromIntFunction(v -> {
			throw new NumberFormatException();
		}).apply(1));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromIntToLongFunction_complete() {
		var collector = new ExceptionCollector(true);
		IntToLongFunction lambda = mock(IntToLongFunction.class);
		when(lambda.applyAsLong(1)).thenReturn(2L);
		assertEquals(OptionalLong.of(2L), collector.fromIntToLongFunction(lambda).apply(1));
		verify(lambda, only()).applyAsLong(1);
		assertTrue(collector.empty());
	}
	@Test
	public void fromIntToLongFunction_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(OptionalLong.empty(), collector.fromIntToLongFunction(v -> {
			throw new NumberFormatException();
		}).apply(1));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromIntToLongFunction_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromIntToLongFunction(v -> {
			throw new NumberFormatException();
		}).apply(1));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromIntToDoubleFunction_complete() {
		var collector = new ExceptionCollector(true);
		IntToDoubleFunction lambda = mock(IntToDoubleFunction.class);
		when(lambda.applyAsDouble(1)).thenReturn(2.0);
		assertEquals(OptionalDouble.of(2.0), collector.fromIntToDoubleFunction(lambda).apply(1));
		verify(lambda, only()).applyAsDouble(1);
		assertTrue(collector.empty());
	}
	@Test
	public void fromIntToDoubleFunction_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(OptionalDouble.empty(), collector.fromIntToDoubleFunction(v -> {
			throw new NumberFormatException();
		}).apply(1));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromIntToDoubleFunction_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromIntToDoubleFunction(v -> {
			throw new NumberFormatException();
		}).apply(1));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromToLongFunction_complete() {
		var collector = new ExceptionCollector(true);
		@SuppressWarnings("unchecked") ToLongFunction<String> lambda = mock(ToLongFunction.class);
		when(lambda.applyAsLong("input")).thenReturn(2L);
		assertEquals(OptionalLong.of(2L), collector.fromToLongFunction(lambda).apply("input"));
		verify(lambda, only()).applyAsLong("input");
		assertTrue(collector.empty());
	}
	@Test
	public void fromToLongFunction_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(OptionalLong.empty(), collector.fromToLongFunction(v -> {
			throw new NumberFormatException();
		}).apply("input"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromToLongFunction_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromToLongFunction(v -> {
			throw new NumberFormatException();
		}).apply("input"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromLongFunction_complete() {
		var collector = new ExceptionCollector(true);
		@SuppressWarnings("unchecked") LongFunction<String> lambda = mock(LongFunction.class);
		when(lambda.apply(1L)).thenReturn("value");
		assertEquals(Optional.of("value"), collector.fromLongFunction(lambda).apply(1L));
		verify(lambda, only()).apply(1L);
		assertTrue(collector.empty());
	}
	@Test
	public void fromLongFunction_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(Optional.empty(), collector.fromLongFunction(v -> {
			throw new NumberFormatException();
		}).apply(1L));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromLongFunction_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromLongFunction(v -> {
			throw new NumberFormatException();
		}).apply(1L));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromLongToIntFunction_complete() {
		var collector = new ExceptionCollector(true);
		LongToIntFunction lambda = mock(LongToIntFunction.class);
		when(lambda.applyAsInt(1L)).thenReturn(2);
		assertEquals(OptionalInt.of(2), collector.fromLongToIntFunction(lambda).apply(1L));
		verify(lambda, only()).applyAsInt(1L);
		assertTrue(collector.empty());
	}
	@Test
	public void fromLongToIntFunction_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(OptionalInt.empty(), collector.fromLongToIntFunction(v -> {
			throw new NumberFormatException();
		}).apply(1L));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromLongToIntFunction_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromLongToIntFunction(v -> {
			throw new NumberFormatException();
		}).apply(1L));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromLongToDoubleFunction_complete() {
		var collector = new ExceptionCollector(true);
		LongToDoubleFunction lambda = mock(LongToDoubleFunction.class);
		when(lambda.applyAsDouble(1L)).thenReturn(2.0);
		assertEquals(OptionalDouble.of(2.0), collector.fromLongToDoubleFunction(lambda).apply(1L));
		verify(lambda, only()).applyAsDouble(1L);
		assertTrue(collector.empty());
	}
	@Test
	public void fromLongToDoubleFunction_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(OptionalDouble.empty(), collector.fromLongToDoubleFunction(v -> {
			throw new NumberFormatException();
		}).apply(1L));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromLongToDoubleFunction_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromLongToDoubleFunction(v -> {
			throw new NumberFormatException();
		}).apply(1L));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromToDoubleFunction_complete() {
		var collector = new ExceptionCollector(true);
		@SuppressWarnings("unchecked") ToDoubleFunction<String> lambda = mock(ToDoubleFunction.class);
		when(lambda.applyAsDouble("input")).thenReturn(2.0);
		assertEquals(OptionalDouble.of(2.0), collector.fromToDoubleFunction(lambda).apply("input"));
		verify(lambda, only()).applyAsDouble("input");
		assertTrue(collector.empty());
	}
	@Test
	public void fromToDoubleFunction_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(OptionalDouble.empty(), collector.fromToDoubleFunction(v -> {
			throw new NumberFormatException();
		}).apply("input"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromToDoubleFunction_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromToDoubleFunction(v -> {
			throw new NumberFormatException();
		}).apply("input"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromDoubleFunction_complete() {
		var collector = new ExceptionCollector(true);
		@SuppressWarnings("unchecked") DoubleFunction<String> lambda = mock(DoubleFunction.class);
		when(lambda.apply(1.0)).thenReturn("value");
		assertEquals(Optional.of("value"), collector.fromDoubleFunction(lambda).apply(1.0));
		verify(lambda, only()).apply(1.0);
		assertTrue(collector.empty());
	}
	@Test
	public void fromDoubleFunction_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(Optional.empty(), collector.fromDoubleFunction(v -> {
			throw new NumberFormatException();
		}).apply(1.0));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromDoubleFunction_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromDoubleFunction(v -> {
			throw new NumberFormatException();
		}).apply(1.0));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromDoubleToIntFunction_complete() {
		var collector = new ExceptionCollector(true);
		DoubleToIntFunction lambda = mock(DoubleToIntFunction.class);
		when(lambda.applyAsInt(1.0)).thenReturn(2);
		assertEquals(OptionalInt.of(2), collector.fromDoubleToIntFunction(lambda).apply(1.0));
		verify(lambda, only()).applyAsInt(1.0);
		assertTrue(collector.empty());
	}
	@Test
	public void fromDoubleToIntFunction_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(OptionalInt.empty(), collector.fromDoubleToIntFunction(v -> {
			throw new NumberFormatException();
		}).apply(1.0));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromDoubleToIntFunction_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromDoubleToIntFunction(v -> {
			throw new NumberFormatException();
		}).apply(1.0));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromDoubleToLongFunction_complete() {
		var collector = new ExceptionCollector(true);
		DoubleToLongFunction lambda = mock(DoubleToLongFunction.class);
		when(lambda.applyAsLong(1.0)).thenReturn(2L);
		assertEquals(OptionalLong.of(2L), collector.fromDoubleToLongFunction(lambda).apply(1.0));
		verify(lambda, only()).applyAsLong(1.0);
		assertTrue(collector.empty());
	}
	@Test
	public void fromDoubleToLongFunction_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(OptionalLong.empty(), collector.fromDoubleToLongFunction(v -> {
			throw new NumberFormatException();
		}).apply(1.0));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromDoubleToLongFunction_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromDoubleToLongFunction(v -> {
			throw new NumberFormatException();
		}).apply(1.0));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromUnaryOperator_complete() {
		var collector = new ExceptionCollector(true);
		@SuppressWarnings("unchecked") UnaryOperator<String> lambda = mock(UnaryOperator.class);
		when(lambda.apply("input")).thenReturn("value");
		assertEquals(Optional.of("value"), collector.fromUnaryOperator(lambda).apply("input"));
		verify(lambda, only()).apply("input");
		assertTrue(collector.empty());
	}
	@Test
	public void fromUnaryOperator_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(Optional.empty(), collector.fromUnaryOperator(o -> {
			throw new NumberFormatException();
		}).apply("input"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromUnaryOperator_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromUnaryOperator(o -> {
			throw new NumberFormatException();
		}).apply("input"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromIntUnaryOperator_complete() {
		var collector = new ExceptionCollector(true);
		IntUnaryOperator lambda = mock(IntUnaryOperator.class);
		when(lambda.applyAsInt(1)).thenReturn(2);
		assertEquals(OptionalInt.of(2), collector.fromIntUnaryOperator(lambda).apply(1));
		verify(lambda, only()).applyAsInt(1);
		assertTrue(collector.empty());
	}
	@Test
	public void fromIntUnaryOperator_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(OptionalInt.empty(), collector.fromIntUnaryOperator(o -> {
			throw new NumberFormatException();
		}).apply(1));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromIntUnaryOperator_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromIntUnaryOperator(o -> {
			throw new NumberFormatException();
		}).apply(1));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromLongUnaryOperator_complete() {
		var collector = new ExceptionCollector(true);
		LongUnaryOperator lambda = mock(LongUnaryOperator.class);
		when(lambda.applyAsLong(1L)).thenReturn(2L);
		assertEquals(OptionalLong.of(2L), collector.fromLongUnaryOperator(lambda).apply(1L));
		verify(lambda, only()).applyAsLong(1L);
		assertTrue(collector.empty());
	}
	@Test
	public void fromLongUnaryOperator_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(OptionalLong.empty(), collector.fromLongUnaryOperator(o -> {
			throw new NumberFormatException();
		}).apply(1L));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromLongUnaryOperator_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromLongUnaryOperator(o -> {
			throw new NumberFormatException();
		}).apply(1L));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromDoubleUnaryOperator_complete() {
		var collector = new ExceptionCollector(true);
		DoubleUnaryOperator lambda = mock(DoubleUnaryOperator.class);
		when(lambda.applyAsDouble(1.0)).thenReturn(2.0);
		assertEquals(OptionalDouble.of(2.0), collector.fromDoubleUnaryOperator(lambda).apply(1.0));
		verify(lambda, only()).applyAsDouble(1.0);
		assertTrue(collector.empty());
	}
	@Test
	public void fromDoubleUnaryOperator_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(OptionalDouble.empty(), collector.fromDoubleUnaryOperator(o -> {
			throw new NumberFormatException();
		}).apply(1.0));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromDoubleUnaryOperator_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromDoubleUnaryOperator(o -> {
			throw new NumberFormatException();
		}).apply(1.0));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromBiFunction_complete() {
		var collector = new ExceptionCollector(true);
		@SuppressWarnings("unchecked") BiFunction<String, String, String> lambda = mock(BiFunction.class);
		when(lambda.apply("input1", "input2")).thenReturn("value");
		assertEquals(Optional.of("value"), collector.fromBiFunction(lambda).apply("input1", "input2"));
		verify(lambda, only()).apply("input1", "input2");
		assertTrue(collector.empty());
	}
	@Test
	public void fromBiFunction_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(Optional.empty(), collector.fromBiFunction((t, u) -> {
			throw new NumberFormatException();
		}).apply("input1", "input2"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromBiFunction_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromBiFunction((t, u) -> {
			throw new NumberFormatException();
		}).apply("input1", "input2"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromToIntBiFunction_complete() {
		var collector = new ExceptionCollector(true);
		@SuppressWarnings("unchecked") ToIntBiFunction<String, String> lambda = mock(ToIntBiFunction.class);
		when(lambda.applyAsInt("input1", "input2")).thenReturn(2);
		assertEquals(OptionalInt.of(2), collector.fromToIntBiFunction(lambda).apply("input1", "input2"));
		verify(lambda, only()).applyAsInt("input1", "input2");
		assertTrue(collector.empty());
	}
	@Test
	public void fromToIntBiFunction_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(OptionalInt.empty(), collector.fromToIntBiFunction((t, u) -> {
			throw new NumberFormatException();
		}).apply("input1", "input2"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromToIntBiFunction_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromToIntBiFunction((t, u) -> {
			throw new NumberFormatException();
		}).apply("input1", "input2"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromToLongBiFunction_complete() {
		var collector = new ExceptionCollector(true);
		@SuppressWarnings("unchecked") ToLongBiFunction<String, String> lambda = mock(ToLongBiFunction.class);
		when(lambda.applyAsLong("input1", "input2")).thenReturn(2L);
		assertEquals(OptionalLong.of(2L), collector.fromToLongBiFunction(lambda).apply("input1", "input2"));
		verify(lambda, only()).applyAsLong("input1", "input2");
		assertTrue(collector.empty());
	}
	@Test
	public void fromToLongBiFunction_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(OptionalLong.empty(), collector.fromToLongBiFunction((t, u) -> {
			throw new NumberFormatException();
		}).apply("input1", "input2"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromToLongBiFunction_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromToLongBiFunction((t, u) -> {
			throw new NumberFormatException();
		}).apply("input1", "input2"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromToDoubleBiFunction_complete() {
		var collector = new ExceptionCollector(true);
		@SuppressWarnings("unchecked") ToDoubleBiFunction<String, String> lambda = mock(ToDoubleBiFunction.class);
		when(lambda.applyAsDouble("input1", "input2")).thenReturn(2.0);
		assertEquals(OptionalDouble.of(2.0), collector.fromToDoubleBiFunction(lambda).apply("input1", "input2"));
		verify(lambda, only()).applyAsDouble("input1", "input2");
		assertTrue(collector.empty());
	}
	@Test
	public void fromToDoubleBiFunction_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(OptionalDouble.empty(), collector.fromToDoubleBiFunction((t, u) -> {
			throw new NumberFormatException();
		}).apply("input1", "input2"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromToDoubleBiFunction_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromToDoubleBiFunction((t, u) -> {
			throw new NumberFormatException();
		}).apply("input1", "input2"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromBinaryOperator_complete() {
		var collector = new ExceptionCollector(true);
		@SuppressWarnings("unchecked") BinaryOperator<String> lambda = mock(BinaryOperator.class);
		when(lambda.apply("input1", "input2")).thenReturn("value");
		assertEquals(Optional.of("value"), collector.fromBinaryOperator(lambda).apply("input1", "input2"));
		verify(lambda, only()).apply("input1", "input2");
		assertTrue(collector.empty());
	}
	@Test
	public void fromBinaryOperator_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(Optional.empty(), collector.fromBinaryOperator((l, r) -> {
			throw new NumberFormatException();
		}).apply("input1", "input2"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromBinaryOperator_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromBinaryOperator((l, r) -> {
			throw new NumberFormatException();
		}).apply("input1", "input2"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromIntBinaryOperator_complete() {
		var collector = new ExceptionCollector(true);
		IntBinaryOperator lambda = mock(IntBinaryOperator.class);
		when(lambda.applyAsInt(11, 12)).thenReturn(2);
		assertEquals(OptionalInt.of(2), collector.fromIntBinaryOperator(lambda).apply(11, 12));
		verify(lambda, only()).applyAsInt(11, 12);
		assertTrue(collector.empty());
	}
	@Test
	public void fromIntBinaryOperator_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(OptionalInt.empty(), collector.fromIntBinaryOperator((l, r) -> {
			throw new NumberFormatException();
		}).apply(11, 12));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromIntBinaryOperator_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromIntBinaryOperator((l, r) -> {
			throw new NumberFormatException();
		}).apply(11, 12));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromLongBinaryOperator_complete() {
		var collector = new ExceptionCollector(true);
		LongBinaryOperator lambda = mock(LongBinaryOperator.class);
		when(lambda.applyAsLong(11L, 12L)).thenReturn(2L);
		assertEquals(OptionalLong.of(2L), collector.fromLongBinaryOperator(lambda).apply(11L, 12L));
		verify(lambda, only()).applyAsLong(11L, 12L);
		assertTrue(collector.empty());
	}
	@Test
	public void fromLongBinaryOperator_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(OptionalLong.empty(), collector.fromLongBinaryOperator((l, r) -> {
			throw new NumberFormatException();
		}).apply(11L, 12L));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromLongBinaryOperator_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromLongBinaryOperator((l, r) -> {
			throw new NumberFormatException();
		}).apply(11L, 12L));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromDoubleBinaryOperator_complete() {
		var collector = new ExceptionCollector(true);
		DoubleBinaryOperator lambda = mock(DoubleBinaryOperator.class);
		when(lambda.applyAsDouble(1.1, 1.2)).thenReturn(2.0);
		assertEquals(OptionalDouble.of(2.0), collector.fromDoubleBinaryOperator(lambda).apply(1.1, 1.2));
		verify(lambda, only()).applyAsDouble(1.1, 1.2);
		assertTrue(collector.empty());
	}
	@Test
	public void fromDoubleBinaryOperator_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(OptionalDouble.empty(), collector.fromDoubleBinaryOperator((l, r) -> {
			throw new NumberFormatException();
		}).apply(1.1, 1.2));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void fromDoubleBinaryOperator_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.fromDoubleBinaryOperator((l, r) -> {
			throw new NumberFormatException();
		}).apply(1.1, 1.2));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void comparator_complete() {
		var collector = new ExceptionCollector(true);
		@SuppressWarnings("unchecked") Comparator<String> lambda = mock(Comparator.class);
		when(lambda.compare("input1", "input2")).thenReturn(2);
		assertEquals(OptionalInt.of(2), collector.comparator(lambda).compare("input1", "input2"));
		verify(lambda, only()).compare("input1", "input2");
		assertTrue(collector.empty());
	}
	@Test
	public void comparator_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(OptionalInt.empty(), collector.comparator((l, r) -> {
			throw new NumberFormatException();
		}).compare("input1", "input2"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void comparator_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.comparator((l, r) -> {
			throw new NumberFormatException();
		}).compare("input1", "input2"));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void closeable_complete() {
		var collector = new ExceptionCollector(true);
		CloseableScope lambda = mock(CloseableScope.class);
		collector.closeable(lambda).close();
		verify(lambda, only()).close();
		assertTrue(collector.empty());
	}
	@Test
	public void closeable_swallowException() {
		var collector = new ExceptionCollector(true);
		collector.closeable(() -> {
			throw new NumberFormatException();
		}).close();
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void closeable_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.closeable(() -> {
			throw new NumberFormatException();
		}).close());
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void run_complete() {
		var collector = new ExceptionCollector(true);
		Runnable lambda = mock(Runnable.class);
		collector.run(lambda);
		verify(lambda, only()).run();
		assertTrue(collector.empty());
	}
	@Test
	public void run_swallowException() {
		var collector = new ExceptionCollector(true);
		collector.run(() -> {
			throw new NumberFormatException();
		});
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void run_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.run(() -> {
			throw new NumberFormatException();
		}));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void get_complete() {
		var collector = new ExceptionCollector(true);
		@SuppressWarnings("unchecked") Supplier<String> lambda = mock(Supplier.class);
		when(lambda.get()).thenReturn("value");
		assertEquals(Optional.of("value"), collector.get(lambda));
		verify(lambda, only()).get();
		assertTrue(collector.empty());
	}
	@Test
	public void get_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(Optional.empty(), collector.get(() -> {
			throw new NumberFormatException();
		}));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void get_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.get(() -> {
			throw new NumberFormatException();
		}));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void getAsInt_complete() {
		var collector = new ExceptionCollector(true);
		IntSupplier lambda = mock(IntSupplier.class);
		when(lambda.getAsInt()).thenReturn(2);
		assertEquals(OptionalInt.of(2), collector.getAsInt(lambda));
		verify(lambda, only()).getAsInt();
		assertTrue(collector.empty());
	}
	@Test
	public void getAsInt_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(OptionalInt.empty(), collector.getAsInt(() -> {
			throw new NumberFormatException();
		}));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void getAsInt_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.getAsInt(() -> {
			throw new NumberFormatException();
		}));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void getAsLong_complete() {
		var collector = new ExceptionCollector(true);
		LongSupplier lambda = mock(LongSupplier.class);
		when(lambda.getAsLong()).thenReturn(2L);
		assertEquals(OptionalLong.of(2L), collector.getAsLong(lambda));
		verify(lambda, only()).getAsLong();
		assertTrue(collector.empty());
	}
	@Test
	public void getAsLong_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(OptionalLong.empty(), collector.getAsLong(() -> {
			throw new NumberFormatException();
		}));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void getAsLong_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.getAsLong(() -> {
			throw new NumberFormatException();
		}));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void getAsDouble_complete() {
		var collector = new ExceptionCollector(true);
		DoubleSupplier lambda = mock(DoubleSupplier.class);
		when(lambda.getAsDouble()).thenReturn(2.0);
		assertEquals(OptionalDouble.of(2.0), collector.getAsDouble(lambda));
		verify(lambda, only()).getAsDouble();
		assertTrue(collector.empty());
	}
	@Test
	public void getAsDouble_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(OptionalDouble.empty(), collector.getAsDouble(() -> {
			throw new NumberFormatException();
		}));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void getAsDouble_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.getAsDouble(() -> {
			throw new NumberFormatException();
		}));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void getAsBoolean_complete() {
		var collector = new ExceptionCollector(true);
		BooleanSupplier lambda = mock(BooleanSupplier.class);
		when(lambda.getAsBoolean()).thenReturn(true);
		assertEquals(OptionalBoolean.of(true), collector.getAsBoolean(lambda));
		verify(lambda, only()).getAsBoolean();
		assertTrue(collector.empty());
	}
	@Test
	public void getAsBoolean_swallowException() {
		var collector = new ExceptionCollector(true);
		assertEquals(OptionalBoolean.empty(), collector.getAsBoolean(() -> {
			throw new NumberFormatException();
		}));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
	@Test
	public void getAsBoolean_passException() {
		var collector = new ExceptionCollector(false);
		assertThrows(NumberFormatException.class, () -> collector.getAsBoolean(() -> {
			throw new NumberFormatException();
		}));
		assertThat(collector.single(), instanceOf(NumberFormatException.class));
	}
}
