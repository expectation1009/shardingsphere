/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.test.integration.engine.junit;

import lombok.extern.slf4j.Slf4j;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Integration test watcher.
 */
@Slf4j
public final class ITWatcher extends TestWatcher {
    
    @Override
    protected void failed(final Throwable ex, final Description description) {
        log.error("Error case: {}, message: {}", description.getMethodName(), stackTrace(ex));
        super.failed(ex, description);
    }
    
    private String stackTrace(final Throwable t) {
        if (t == null) {
            return "";
        }
        //CHECKSTYLE:OFF
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             PrintStream ps = new PrintStream(out)) {
            t.printStackTrace(ps);
            ps.flush();
            return new String(out.toByteArray());
        } catch (Exception ignored) {
            // ignored
            //CHECKSTYLE:ON
        }
        return "";
    }
}
