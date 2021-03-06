/*
 * Copyright 2016 HuntBugs contributors
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

import one.util.huntbugs.registry.anno.AssertNoWarning;
import one.util.huntbugs.registry.anno.AssertWarning;

/**
 * @author Tagir Valeev
 *
 */
public class TestNumericPromotion {
    @AssertWarning(value="IntegerMultiplicationPromotedToLong", minScore = 65)
    public long testMultiplication(int num) {
        return num * 365 * 86400 * 1000;
    }

    @AssertWarning(value="IntegerMultiplicationPromotedToLong", minScore = 55, maxScore = 60)
    public long testMultiplication2(int num) {
        return num * 365 * 86400;
    }

    @AssertWarning(value="IntegerMultiplicationPromotedToLong", minScore = 45, maxScore = 50)
    public long testMultiplication3(int num) {
        return num * 86400;
    }

    @AssertWarning(value="IntegerMultiplicationPromotedToLong", minScore = 35, maxScore = 40)
    public long testMultiplication4(int num) {
        return num * 365;
    }

    @AssertWarning(value="IntegerMultiplicationPromotedToLong", minScore = 25, maxScore = 30)
    public long testMultiplication5(int num) {
        return num * 60;
    }

    @AssertWarning(value="IntegerMultiplicationPromotedToLong", minScore = 15, maxScore = 20)
    public long testMultiplication6(int num) {
        return num * 2;
    }

    @AssertNoWarning("IntegerMultiplicationPromotedToLong")
    public long testMultiplicationTwoNum(int num, int num2) {
        return num * num2;
    }

    @AssertWarning("IntegerDivisionPromotedToFloat")
    public double divide(int x, int y) {
        return x / y;
    }

    @AssertNoWarning("IntegerDivisionPromotedToFloat")
    public double percent(int val, int total) {
        return val * 100 * 10 / total / 10.0;
    }
    
    @AssertWarning(value="IntegerDivisionPromotedToFloat", maxScore = 50, minScore = 40)
    public double byTen(int val) {
        return val / 10;
    }
    
    @AssertNoWarning("*")
    public String format(int length) {
        return String.valueOf((length / 100) / 10.0);
    }
    
    @AssertNoWarning("IntegerDivisionPromotedToFloat")
    public double check(int val) {
        int res = val / 3;
        System.out.println(res);
        return res * 3.0;
    }
    
    @AssertWarning("IntegerDivisionPromotedToFloat")
    public double divideByTwo(double x, double y) {
        double res = (int)(x - y)/2;
        return res;
    }

    @AssertWarning("IntegerPromotionInCeilOrRound")
    public int divideAndRound(int x, int y) {
        return Math.round(x / y);
    }
    
    @AssertWarning("IntegerPromotionInCeilOrRound")
    public long divideAndCeil(long x, long y) {
        return (long) Math.ceil(x / y);
    }
    
    @AssertWarning("IntegerDivisionPromotedToFloat")
    public float divideFloat(int x, long y) {
        return x / y;
    }
    
    @AssertNoWarning("*")
    public float divideFloatOk(int x, int y) {
        return (float)x / y;
    }
    
    @AssertWarning("IntegerDivisionPromotedToFloat")
    public void divideWithNeg(int x) {
        int res = x/2;
        System.out.println((double)res);
        System.out.println((double)(-res));
    }

    @AssertNoWarning("*")
    public void divideWithNegOk(int x) {
        int res = x/2;
        System.out.println((double)res);
        System.out.println(-res);
    }
}
