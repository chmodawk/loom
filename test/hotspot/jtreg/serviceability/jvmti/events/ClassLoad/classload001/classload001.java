/*
 * Copyright (c) 2003, 2018, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

import java.io.*;


/*
 * @test
 *
 * @summary converted from VM Testbase nsk/jvmti/ClassLoad/classload001.
 * VM Testbase keywords: [quick, jpda, jvmti, onload_only_logic, noras]
 * VM Testbase readme:
 * DESCRIPTION
 *     The test exercises the JVMTI event Class Load.
 *     It verifies that the event will be sent for the auxiliary class
 *     'TestedClass' and not sent for array of type 'TestedClass' and for
 *     primitive classes and arrays of primitive types in accordance with
 *     the ClassLoad spec:
 *        "Array class creation does not generate a class load event. The
 *         creation of a primitive class (for example, java.lang.Integer.TYPE)
 *         does not generate a class load event."
 * COMMENTS
 *     Fixed the 5031200 bug.
 *
 * @library /test/lib
 * @run main/othervm/native -agentlib:classload classload001
 */


/**
 * The test exercises the JVMTI event Class Load.<br>
 * It verifies that the event will be sent for the auxiliary class
 * <code>TestedClass</code> and array of type <code>TestedClass</code>
 * and vise versa for primitive classes and arrays of primitive types
 * in accordance with the ClassLoad spec:<br>
 * <code>Arrays of non-primitive types have class load events. Arrays of
 * primitive types do not have class load events. Primitive classes (for
 * example, java.lang.Integer.TYPE) do not have class load events.</code>
 */
public class classload001 {
    static {
        try {
            System.loadLibrary("classload001");
        } catch (UnsatisfiedLinkError ule) {
            System.err.println("Could not load \"classload001\" library");
            System.err.println("java.library.path:"
                + System.getProperty("java.library.path"));
            throw ule;
        }
    }

    native int check();

    public static void main(String args[]) {
        int result = new classload001().check();
        if (result !=0 ) {
            throw new RuntimeException("Check returned " + result);
        }
    }

    public static int run(String args[], PrintStream out) {
        return new classload001().runIt(args, out);
    }

    private int runIt(String args[], PrintStream out) {
        return check();
    }

    // classes & arrays used to verify an assertion in the agent
    Class boolCls = Boolean.TYPE;
    Class byteCls = Byte.TYPE;
    Class charCls = Character.TYPE;
    Class doubleCls = Double.TYPE;
    Class floatCls = Float.TYPE;
    Class intCls = Integer.TYPE;
    Class longCls = Long.TYPE;
    Class shortCls = Short.TYPE;

    Class boolClArr[] = {Boolean.TYPE, Boolean.TYPE};
    Class byteClArr[] = {Byte.TYPE, Byte.TYPE};
    Class charClArr[] = {Character.TYPE};
    Class doubleClArr[] = {Double.TYPE};
    Class floatClArr[] = {Float.TYPE, Float.TYPE};
    Class intClArr[] = {Integer.TYPE};
    Class longClArr[] = {Long.TYPE};
    Class shortClArr[] = {Short.TYPE, Short.TYPE};

    boolean boolArr[] = {false, true};
    byte byteArr[] = {Byte.MAX_VALUE};
    char charArr[] = {'a'};
    double doubleArr[] = {Double.MIN_VALUE};
    float floatArr[] = {Float.MAX_VALUE};
    int intArr[] = {Integer.MIN_VALUE};
    long longArr[] = {Long.MAX_VALUE};
    short shortArr[] = {Short.MIN_VALUE};

    TestedClass testedCls[] = {new TestedClass()};

    class TestedClass {}
}
