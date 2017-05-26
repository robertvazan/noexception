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
}
