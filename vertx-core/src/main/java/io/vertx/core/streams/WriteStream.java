/*
 * Copyright (c) 2011-2013 The original author or authors
 * ------------------------------------------------------
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution.
 *
 *     The Eclipse Public License is available at
 *     http://www.eclipse.org/legal/epl-v10.html
 *
 *     The Apache License v2.0 is available at
 *     http://www.opensource.org/licenses/apache2.0.php
 *
 * You may elect to redistribute this code under either of these licenses.
 */

package io.vertx.core.streams;

import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.gen.Fluent;
import io.vertx.core.gen.VertxGen;

/**
 *
 * Represents a stream of data that can be written to<p>
 * Any class that implements this interface can be used by a {@link Pump} to pump data from a {@code ReadStream}
 * to it.<p>
 * This interface exposes a fluent api and the type T represents the type of the object that implements
 * the interface to allow method chaining
 *
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
@VertxGen
public interface WriteStream<T> {

  /**
   * Write some data to the stream. The data is put on an internal writeBuffer queue, and the writeBuffer actually happens
   * asynchronously. To avoid running out of memory by putting too much on the writeBuffer queue,
   * check the {@link #writeQueueFull} method before writing. This is done automatically if using a {@link Pump}.
   */
  @Fluent
  T writeBuffer(Buffer data);

  /**
   * Set the maximum size of the writeBuffer queue to {@code maxSize}. You will still be able to writeBuffer to the stream even
   * if there is more than {@code maxSize} bytes in the writeBuffer queue. This is used as an indicator by classes such as
   * {@code Pump} to provide flow control.
   */
  @Fluent
  T setWriteQueueMaxSize(int maxSize);

  /**
   * This will return {@code true} if there are more bytes in the writeBuffer queue than the value set using {@link
   * #setWriteQueueMaxSize}
   */
  boolean writeQueueFull();

  /**
   * Set a drain handler on the stream. If the writeBuffer queue is full, then the handler will be called when the writeBuffer
   * queue has been reduced to maxSize / 2. See {@link Pump} for an example of this being used.
   */
  @Fluent
  T drainHandler(Handler<Void> handler);

  /**
   * Set an exception handler.
   */
  @Fluent
  T exceptionHandler(Handler<Throwable> handler);
}
