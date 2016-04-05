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
package one.util.huntbugs.detect;

import com.strobel.assembler.metadata.MethodReference;
import com.strobel.decompiler.ast.AstCode;
import com.strobel.decompiler.ast.Expression;
import com.strobel.decompiler.ast.Node;

import one.util.huntbugs.registry.MethodContext;
import one.util.huntbugs.registry.anno.AstNodeVisitor;
import one.util.huntbugs.registry.anno.WarningDefinition;
import one.util.huntbugs.util.Nodes;
import one.util.huntbugs.warning.WarningAnnotation;

/**
 * @author lan
 *
 */
@WarningDefinition(category="Performance", name="NumberConstructor", baseRank = 40)
public class NumberConstructor {
    @AstNodeVisitor
    public void visit(Node node, MethodContext ctx) {
        if(Nodes.isOp(node, AstCode.InitObject) && node.getChildren().size() == 1) {
            MethodReference ctor = (MethodReference) ((Expression)node).getOperand();
            if(ctor.getDeclaringType().getPackageName().equals("java.lang")) {
                String simpleName = ctor.getDeclaringType().getSimpleName();
                if(simpleName.equals("Integer") || simpleName.equals("Long") || simpleName.equals("Short") || simpleName.equals("Byte")
                        || simpleName.equals("Boolean") || simpleName.equals("Character")) {
                    Object val = Nodes.getConstant(node.getChildren().get(0));
                    if(val instanceof Number) {
                        long value = ((Number)val).longValue();
                        int priority;
                        if(value >= -128 && value < 127)
                            priority = 5;
                        else
                            priority = simpleName.equals("Integer") ? -10 : -30;
                        ctx.report("NumberConstructor", priority, node, WarningAnnotation.forNumber((Number) val));
                    } else {
                        ctx.report("NumberConstructor", 0, node);
                    }
                }
            }
        }
    }

}
