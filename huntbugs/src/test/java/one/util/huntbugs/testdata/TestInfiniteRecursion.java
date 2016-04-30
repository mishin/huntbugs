/*
 * Copyright 2015, 2016 Tagir Valeev
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package one.util.huntbugs.testdata;

import java.util.List;

import one.util.huntbugs.registry.anno.AssertNoWarning;
import one.util.huntbugs.registry.anno.AssertWarning;

/**
 * @author lan
 *
 */
public class TestInfiniteRecursion {
    @AssertWarning(type="InfiniteRecursion")
    public TestInfiniteRecursion(int x) {
        System.out.print(new TestInfiniteRecursion(1));
    }
    
    @AssertWarning(type="InfiniteRecursion")
    public TestInfiniteRecursion(int x, int y) {
        if(x > 2)
            return;
        new TestInfiniteRecursion(x, y);
    }
    
    @AssertWarning(type="InfiniteRecursion")
    public static void testSimple() {
        testSimple();
    }

    @AssertWarning(type="InfiniteRecursion")
    public static void testSimple(int x) {
        testSimple(x);
    }
    
    @AssertWarning(type="InfiniteRecursion")
    public static void testSimpleMod(int x) {
        x--;
        testSimpleMod(x);
    }

    @AssertWarning(type="InfiniteRecursion")
    public static void testSimpleCheck(int x) {
        if(x > 2)
            return;
        testSimpleCheck(x);
    }
    
    @AssertWarning(type="InfiniteRecursion")
    public static void testSimpleCheck(int x, long y, double z, boolean b) {
        if(!b)
            return;
        testSimpleCheck(x, y, z, b);
    }
    
    @AssertNoWarning(type="InfiniteRecursion")
    public static void testSimpleModOk(int x) {
        if(x-- > 0)
            return;
        testSimpleModOk(x);
    }
    
    @AssertWarning(type="InfiniteRecursion")
    public int test() {
        return test();
    }
    
    int f;
    
    private boolean updateF() {
        return --f > 0;
    }
    
    @AssertNoWarning(type="InfiniteRecursion")
    public boolean has() {
        if(updateF())
            return false;
        return has();
    }
    
    @AssertNoWarning(type="*")
    public void printRecursively(List<?> list) {
        for(Object obj : list) {
            if(obj instanceof List) {
                printRecursively((List<?>)obj);
            } else
                System.out.println(obj);
        }
    }
    
    public interface Iface {
        @AssertWarning(type="InfiniteRecursion")
        default public int test(int x) {
            return test(x+1);
        }
    }
}